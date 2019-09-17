package cn.xiewei.graph.storage.adjtable2;

public class Vertex {
    /**
     * 图的顶点名称
     */
    protected int verName;
    /**
     * 边链表的头指针
     */
    protected Edge next;
    public int getVerName() {
        return verName;
    }
    public void setVerName(int verName) {
        this.verName = verName;
    }
    public Edge getNext() {
        return next;
    }
    public void setNext(Edge next) {
        this.next = next;
    }
  
    
    
    
}
