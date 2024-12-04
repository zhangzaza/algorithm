package code17_bitmap1;

import java.util.ArrayList;


/// 点结构的描述
public class BitmapNode {

    public int value;
    // 入度
    public int in;
    // 出度
    public int out;
    public ArrayList<BitmapNode> nexts;
    public ArrayList<BitmapEdge> edges;

    public BitmapNode(int value)
    {
        this.value = value;
        in=0;
        out =0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }

}
