package cn.xiewei.graph.storage.adjtable2;

public class Vertex {
    /**
     * ͼ�Ķ�������
     */
    protected int verName;
    /**
     * �������ͷָ��
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
