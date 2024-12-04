package code17_bitmap1;


/// 边的描述
public class BitmapEdge {

    public int weight;
    public BitmapNode from;
    public BitmapNode to;
    public BitmapEdge(int weight, BitmapNode from, BitmapNode to)
    {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }

}
