package cn.xiewei.graph.storage.adjtable2;
public class Edge {
    /**
     * 1.�ڽӶ�����š�
     * 2.ͼ�Ķ������ƣ�һ�������������㣬��Ϊ�ڶ������㡣
     */
    protected int tailName;
    /**
     * �ߵ�Ȩֵ��
     */
    protected int weight;
    /**
     * 1.��һ���߽�㡣
     * 2.������Ծ���ָ�룬ָ����һ���ߡ�
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
