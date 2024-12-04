package code02_basedatastructures;

/*
* 随机函数random的使用
* */
public class Code03_random {


    public static void main(String[] args) {
        System.out.println("开始测试");
        //Math.random -> double [0,1)
        int testTime =100000000;
        int count =0;
        for (int i = 0; i < testTime; i++) {
            if (Math.random() < 0.75) {
                count++;
            }
        }
        System.out.println((double) count / (double)testTime);//无限接近0.75


        //【0，1） -> [0,8)
        count=0;
        for (int i = 0; i < testTime; i++) {
            if (Math.random() * 8 < 5) {
                count++;
            }
        }
        System.out.println((double) count / (double)testTime);//无限接近0.75



        //[0,1) ->[0,8) -> int[0,8) == [0,7]
        int k = 9;
        int [] counts =new int[k];
        for (int i = 0; i < testTime; i++) {
            int index = (int) (Math.random() * k);
            counts[index]++;
        }
        for (int i = 0; i < counts.length; i++) {
            System.out.println(i + " 出现了: " + counts[i]+"次。");
        }
        //0 出现了: 11112007次。
        //1 出现了: 11112481次。
        //2 出现了: 11109228次。
        //3 出现了: 11109815次。
        //4 出现了: 11112588次。
        //5 出现了: 11110117次。
        //6 出现了: 11110173次。
        //7 出现了: 11112284次。
        //8 出现了: 11111307次。


        //x 在[0,1) 的概率变成平方
        count=0;
        double root =0.7;
        for (int i = 0; i < testTime; i++) {
            if (xToXSquare()< root){ //说明两次随机数都小于0.7
                count++;
            }
        }
        System.out.println((double) count / (double)testTime);
        System.out.println(Math.pow(root,2));
        //0.48999038
        //0.48999999999999994

    }


    //返回x 在 【0，1）出现概率的平方
    //两次随机取最大值，说明这两个数都在 0～最大值 这个区间
    public static double xToXSquare() {
        return Math.max(Math.random(),Math.random());
    }

    //如果max变成min
    //两次随机取最小值说明，拿到的值都是 1-x「最小值」 这个区间 得不到的是 1-（1-x）^2
    public static double xToOneSquare() {
        return Math.min(Math.random(),Math.random());
    }



    /*
    * 面试题1:有一个f()函数1-5随机，从1-5随机 到 修改出一个 函数实现1-7随机
    * 解题思路：
    * 1.第一步 ： 先得到f1函数 1，2，3，4，5  如果f函数的到的是1，2，就拿到0，如果f函数是4，5，就拿到1，拿到3就重新来
    * 2.第二步 ： 用f1函数去进行二进制运算 三位 二进制 对应 000，001，010，011，100，101，110，111，只需要拿到里面的6个，拿到其他的两个直接重新计算
    * */
    public static int g(){
        return f3()+1;
    }

    public static int f3(){
        int ans;
        do{
            ans=f2();
        }while (ans ==7);
        return ans;
    }

    public static int f2(){
        int ans = f1() << 2 + f1() << 1 + f1();
        return ans ;
    }

    public static int f1(){
        int ans =0;
        do{
            ans = f();
        }while (ans == 3);
        return ans < 3 ? 0 : 1;
    }

    //黑盒函数「不能修改」
    public  static int f(){
        return (int) (Math.random() * 5 + 1);
    }


    /*
    * 面试题2:从 a～b 随机到 c～d 随机
    * 1.制作函数 实现 0，1 等概率发生次
    * 2.看c～d相差多少，使用二进制需要多少位
    * 3.使用 （0～n）+ k 来代替
    * */



    /*
    * 面试题3:从01不等概率 但是是固定概率 到 01等概率
    * 1.筛选两次 ：01 和 10 等概率
    * 2.01 时候为 0 ， 01 时候为 1
    * */
    public static int g2(){
        int ans;
        do {
            ans =f5();
        }while (ans == f5());
        return ans;
    }

    //以固定的概率返回 0 和 1 ，但是概率是多少我们不知道
    //黑盒函数「不能修改」
    public  static int f5(){
        return Math.random()<0.84 ? 0:1;
    }




}
