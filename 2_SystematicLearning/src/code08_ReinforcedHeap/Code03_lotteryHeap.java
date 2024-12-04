package code08_ReinforcedHeap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 手动改写堆题目
 * 给定一个整型数组，int[] arr,和一个布尔类型数组，boolean[]op
 * 两个数组一定等长，假设长度为N，arr[i]表示客户编号，op[i]表示客户操作
 * arr=[3，3，2，1,2，2，5........
 * op =[T，T，T，T,F，T，F........
 * 依次表示：3用户购买了一件商品，3用户购买了一件商品，1用户购买了一件商品，2用户购买了一件商品，1用户退货了一件商品....
 * 一对arr[i]和op[i]==T 就代表这个用户购买了一件商品，一对arr[i]和op[i]==F就代表这个用户退货了一件商品
 *
 *
 * 现在你作为电商平台负责人，你想在每一个事件到来的时候，都给购买次数最多的前K个用户颁奖
 * 所以认为，在每一个事件到来的时候，你都需要知道购买次数最多的前K个用户，并且知道他们购买了多少件商品，需要一个得奖名单（得奖区）
 *
 * 得奖系统的规则：
 * 1.如果某个用户购买商品数为0，但是又发生了退货事件，则认为该事件无效，得奖名单和上一个事件发生后一致，例如例子中的5用户
 * 2.某用户发生购买商品事件，购买商品数+1，发生退货事件，购买商品数-1
 * 3.每次都是最多K个用户得奖，K也为传入的参数，如果根据全部规则，得奖人数确实不够K个，那就不够的情况输出结果
 * 4.得奖系统分为得奖区和候选区，任何用户只要购买数 > 0, 一定在这两个区域中的一个
 * 5.购买数最大的前K个用户进入得奖区，在最初时候如果得奖区没有到达K个用户，那么先来的用户直接进入得奖区
 * 6.如果购买数不足以进入得奖区的用户，进入候选区
 * 7.如果候选区购买数最多的用户，已经进入得奖区，该用户就会替换得奖区中购买数最少的用户（大于才能替换），
 *   如果得奖区中购买数最少的用户有多个，就替换最早进入得奖区中的用户
 *   如果侯奖区中购买数最多的用户有多个，机会会给最早进入候选区的用户
 * 8.候选区和得奖区是两套时间，
 *   因用户只会在其中一个区域，所以只会有一个区域的时间，另一个没有
 *   从得奖区出来进入候选区的用户，得奖区时间删除，
 *   进入候选区的事件就是当前事件的事件（可以理解为arr[i]和op[i]的i）
 *   从候选区出来进入得奖区的用户，候选区事件删除，
 *   进入得奖区的事件就是当前事件的时间（）可以理解为arr[i]和op[i]的i）
 * 9.如果某用户购买数==0，不管在哪个区域都离开，区域时间删除
 *   离开是指彻底离开，哪个区域也不会找到该用户
 *   如果下次该用户又发生购买行为，产生>0的购买数，
 *   会再次根据之前规则回到某个区域中，进入区域的时间重计
 *
 *
 * 要求：请遍历arr数组和op数组，遍历每一步输出一个得奖名单
 * public List<List<Integer>> topK(int[] arr,boolean[]op,int K)
 *
 *
 *
 * 思路：
 * 1.使用两个加强堆实现
 * 2.用户购买或者退货，都会影响得奖区和候选区的购买数，所以需要一个包装类来记录用户购买数，时间，id
 * 3.无论该用户在哪个堆，先直接修改用户的数据，然后进行resign()
 * 4.修改结束后，再执行 daddyMove() ，调整两个区的平衡，使之满足提议
 *
 * */

public class Code03_lotteryHeap {


    public List<List<Integer>> topK(int[] arr,boolean[]op,int K){
        List<List<Integer>> ans = new ArrayList<>();
        WhosYourDaddy whosYourDaddy = new WhosYourDaddy(K);
        for (int i = 0; i < arr.length; i++) {
            whosYourDaddy.operate(i,arr[i],op[i]);
            ans.add(whosYourDaddy.getDaddies());
        }
        return ans;
    }




    public class WhosYourDaddy{
        private HashMap<Integer,Customer> customers;
        //侯奖区 大根堆
        private Code02_HeapGreater<Customer> candHeap;
        //得奖区 小根堆
        private Code02_HeapGreater<Customer> daddyHeap;
        private final int daddyLimit;


        public WhosYourDaddy(int limit){
            daddyLimit = limit;
            customers = new HashMap<>();
            //首先数量先排序，再按时间从早到晚排序
            candHeap = new Code02_HeapGreater<>((o1,o2)->o2.buy-o1.buy!=0?o2.buy-o1.buy:o1.enterTime-o2.enterTime);
            daddyHeap = new Code02_HeapGreater<>((o1,o2)->o1.buy-o2.buy!=0?o1.buy-o2.buy:o1.enterTime-o2.enterTime);

        }


        //操作
        public void operate(int time ,int id,boolean buyOrRefund){
            //卖但是该用户没有该产品
            if (!buyOrRefund && !customers.containsKey(id)){
                return;
            }
            //用户第一次购买
            if (!customers.containsKey(id)){
                customers.put(id,new Customer(id,0,0));
            }

            //查用户购买了多少产品
            Customer c= customers.get(id);
            //如果是买 就++，如果是卖 就--
            if (buyOrRefund){
                c.buy++;
            }else {
                c.buy--;
            }

            //如果购买数为0，就离开
            if (c.buy==0){
                customers.remove(id);
            }

            //如果不在两个堆中，就加入堆中
            if(!candHeap.contains(c)&&!daddyHeap.contains(c)){
                //如果得奖区没有满，就直接加入
                //如果得奖区满了，就加入候选区
                if (daddyHeap.size()<daddyLimit){
                    c.enterTime=time;
                    daddyHeap.push(c);
                }else {
                    c.enterTime=time;
                    candHeap.push(c);
                }
            } else if (candHeap.contains(c)){
                //如果候选区有，就判断是否购买数为0，是就删除，不是就重新加入
                if (c.buy ==0){
                    candHeap.remove(c);
                }else {
                    candHeap.resign(c);
                }
            }else {
                //如果得奖区有，就判断是否购买数为0，是就删除，不是就重新加入
                if(c.buy ==0){
                    daddyHeap.remove(c);
                }else {
                    daddyHeap.resign(c);
                }
            }

            //候选区和得奖区的动态平衡
            daddyMove(time);

        }


        private void daddyMove(int time){
            //候选区空，不用管
            if (candHeap.isEmpty()){
                return;
            }

            //得奖区没满，就直接加入
            if (daddyHeap.size()<daddyLimit){
                Customer c = candHeap.pop();
                c.enterTime = time;
                daddyHeap.push(c);
            }else {
                //得奖区满了，就判断候选区最大购买数是否>得奖区最小购买数，是就替换得奖区替换候选区，不是就直接结束
                if (candHeap.peek().buy>daddyHeap.peek().buy){
                    Customer oldDaddy = daddyHeap.pop();
                    Customer newDaddy = daddyHeap.pop();
                    oldDaddy.enterTime=time;
                    newDaddy.enterTime=time;
                    daddyHeap.push(newDaddy);
                    candHeap.push(oldDaddy);
                }
            }

        }



        public List<Integer> getDaddies(){
            List<Customer> customers = daddyHeap.getAllElements();
            List<Integer> ans = new ArrayList<>();
            for (Customer c : customers) {
                ans.add(c.id);
            }
            return ans;
        }
    }


    // 用于存储客户信息
    public class Customer {
        int id;
        int buy;
        int enterTime;
        public Customer(int id,int buy,int time){
            this.id = id;
            this.buy = buy;
            this.enterTime = time;
        }
    }



}


