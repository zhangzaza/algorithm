package code33_indexTree_AC;


import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Code03_AC {

    //前缀数的节点
    public static class Node{
        //如果一个node，end为空，不是结尾
        //如果end不为空，表示这个点是某个字符串的结尾，end的值就是这个字符串
        public String end;
        //只有在上面的end变量不为空的时候，endUse才有意义
        //表示 这个字符串之前有没有加入过答案
        public boolean endUse;
        public Node fail;
        public Node[] nexts;
        public Node(){
            end = null;
            endUse = false;
            fail = null;
            nexts = new Node[26];
        }
    }



    public static class ACAutoMata{
        private Node root;

        public ACAutoMata(){
            root = new Node();
        }

        public void insert(String s){
            char[] str = s.toCharArray();
            Node cur=root;
            int index=0;
            for(int i=0;i<str.length;i++){
                index = str[i]-'a';
                if(cur.nexts[index]==null){
                    cur.nexts[index]=new Node();
                }
                cur = cur.nexts[index];
            }
            cur.end =s;
        }
        
        
        public void build(){
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            Node cur=null;
            Node cfail=null;
            while (!queue.isEmpty()){
                //某个父亲，cur
                cur = queue.poll();
                for (int i = 0; i < 26; i++) {//所有的路
                    //cur -> 父亲 i号儿子，必须把i号儿子的fail指针设置好！
                    if(cur.nexts[i]!=null){//如果真的有i号儿子
                        cur.nexts[i].fail=root;
                        cfail=cur.fail;
                        while (cfail!=null){
                            if (cfail.nexts[i]!=null){
                                cur.nexts[i].fail=cfail.nexts[i];
                                break;
                            }
                            cfail=cfail.fail;
                        }
                        queue.add(cur.nexts[i]);
                    }
                }
            }
        }


        //大文章 ： content
        public List<String> containWords(String content){
            char[] str = content.toCharArray();
            Node cur=root;
            Node follow =null;
            int index =0;
            List<String> ans = new LinkedList<>();
            for(int i=0;i<str.length;i++){
                index=str[i]-'a';//路
                //如果当前字符在这条路上没配出来，就随着fail方向走向下路径
                while (cur.nexts[index]==null && cur!=root){
                    cur=cur.fail;
                }
                // 1.现在来到的路径，是可以继续匹配的
                // 2.现在来到的节点，就是前缀树的根节点
                cur=cur.nexts[index]!=null?cur.nexts[index]:root;
                follow=cur;
                while (follow!=root){
                    if (follow.endUse){
                        break;
                    }

                    //不同的需求，在这一段时间修改
                    if (follow.end!=null){
                        ans.add(follow.end);
                        follow.endUse=true;
                    }
                    //不同的需求，在这一段时间修改

                    follow=follow.fail;
                }
            }
            return ans;
        }
    }


    public static void main(String[] args) {

        ACAutoMata acAutoMata = new ACAutoMata();
        acAutoMata.insert("dhe");
        acAutoMata.insert("he");
        acAutoMata.insert("absdg");
        //设置fail指针
        acAutoMata.build();

        List<String> contains = acAutoMata.containWords("adfadgbadkgjhealdkjfavaldjfadf");
        for (String contain : contains) {
            System.out.println(contain);
        }


    }





}
