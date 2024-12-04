package code09_PrefixTree_SortStability;

//题目：实现一个支持以下操作的数据结构：
//1.void addWord(String word): 添加一个单词到数据结构中。
//2.boolean search(String word): 如果数据结构中存在这个单词则返回 true，否则返回 false。

//可以用用哈希表做，也可以用字典树做
//1.哈希只能返回 是否存在这个单词
//2.字典树可以返回 是否存在这个单词，并且可以返回所有以这个单词开头的单词

public class Code02_prefixTree {

    public static class Node1 {
        public int pass;//经过多少次
        public int end;//多少次以这个结尾
        public Node1[] nexts;

        public Node1() {
            pass = 0;
            end = 0;
            nexts = new Node1[26];//26个字母，0-25，a-z，
        }
    }


    public static class  Trie1 {
        private Node1 root;

        public Trie1() {
            root = new Node1();
        }


        public void insert(String word) {
            if (word == null){
                return;
            }

            char[] str = word.toCharArray();
            Node1 node =root;
            node.pass++;
            int path = 0;
            for (int i = 0; i < str.length; i++) {
                path = str[i] - 'a';
                if (node.nexts[path] == null) { //下一个节点的指定node 为null
                    node.nexts[path] = new Node1();
                }
                node = node.nexts[path];
                node.pass++;
            }
            node.end++;

        }


        public int search(String word) {
            if (word == null){
                return 0;
            }
            char[] chs = word.toCharArray();
            Node1 node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.end;
        }


        //所有加入的字符串中，有几个是以pre这个字符串作为前缀的
        public int prefixNumber(String pre) {
            if (pre ==null){
                return 0;
            }
            char[] chs = pre.toCharArray();
            Node1 node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }

            return node.pass;//与search，就这里返回的不一样
        }

        //删除，存在一个内存泄漏的问题
        public void delete(String word){
            if (search(word)!=0){
                char[] chs = word.toCharArray();
                Node1 node = root;
                node.pass--;
                int path = 0;
                for (int i = 0; i < chs.length; i++) {
                    path = chs[i] - 'a';
                    // ode.nexts[path]表示下一个节点，如果这个节点的pass为0，就删除该节点
                    //这一步就是还没往下走，就删除，如果往下走，就pass--，比如说一条链只有 ....bcdf，这几个字母的pass都为1，现在要删除bcdf，那么b的pass就为0，就可以删除了
                    if (--node.nexts[path].pass == 0){
                        node.nexts[path] = null;
                        return;
                    }
                    node = node.nexts[path];
                }
                node.end--;
            }
        }




    }



}
