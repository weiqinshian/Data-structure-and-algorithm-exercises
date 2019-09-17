package cn.xiewei.graph.storage.adjtable2;
public class Edge {
    /**
     * 1.邻接顶点序号。
     * 2.图的顶点名称，一条边有两个顶点，此为第二个顶点。
     */
    protected int tailName;
    /**
     * 边的权值。
     */
    protected int weight;
    /**
     * 1.下一个边结点。
     * 2.这个属性就是指针，指向下一条边。
     */
    protected Edge link;

    public Edge getLink() {
        return link;
    }
    public void setLink(Edge link) {
        this.link = link;
    }
    public int getTailName() {
        return tailName;
    }
    public void setTailName(int tailName) {
        this.tailName = tailName;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
   
}
