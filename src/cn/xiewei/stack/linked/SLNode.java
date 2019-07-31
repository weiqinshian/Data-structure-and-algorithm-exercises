package cn.xiewei.stack.linked;
/**
 * �ڵ��࣬��װÿ���ڵ������
 * @author XW
 * @create_date 2019��7��31��
 */
public class SLNode implements Node {
    private Object element;
    private SLNode next;
    public SLNode() {
        this(null,null);
    }
    public SLNode(Object ele, SLNode next){
        this.element = ele;
        this.next = next;
    }
    public SLNode getNext(){
        return next;
    }
    public void setNext(SLNode next){
        this.next = next;
    }
    /****************Node Interface Method**************/
    public Object getData() {
        return element;
    }
    public void setData(Object obj) {
        element = obj;
    }   
}