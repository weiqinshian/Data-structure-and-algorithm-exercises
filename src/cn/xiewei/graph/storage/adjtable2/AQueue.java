package cn.xiewei.graph.storage.adjtable2;
/**
 * ˳�����
 * 
 * @author XW
 * @create_date 2019��9��17��
 */
public class AQueue<T> {
    /**
     * ������������Ԫ�ص��±�
     */
    private int front;
    /**
     * ��β��������Ԫ�ص��±�
     */
    private int rear;
    /**
     * ����Ԫ�صĸ���
     */
    private int count;
    /**
     * ��Ŷ���Ԫ�ص�����
     */
    private Object[] queueArray;
    /**
     * ����Ĺ�ģ
     */
    private int size;
    /**
     * ���췽��
     */
    public AQueue(){
        this.size=10;
        this.queueArray=new Object[size];
        front=0;
        rear=0; 
        count=0;
    }
    /**
     * ���췽��
     * @param size �����С
     */
    public AQueue(int size){
        this.size=size;
        this.queueArray=new Object[size];
        front=0;
        rear=0; 
        count=0;
    }
    /**
     * �ж��Ƿ�����
     * @return
     */
    public boolean isFull(){
        return count==size;
    }
    /**
     * �ж��Ƿ�Ϊ��
     * @return
     */
    public boolean isEmpty(){
        return count==0;
    }
    /**
     * �ڶ�β����dataΪitem��Ԫ��
     * @param item ������
     * @return �ɹ�����true
     */
    public boolean insert(T item){
        if(isFull()){
            System.out.println("full queue");
            return false;
        }
        queueArray[rear]=item;
        rear=(rear+1)%size;//�޸Ķ�βָ��
        count++;
        return true;
    }
    /**
     * ɾ������Ԫ��
     * @return ���ض���Ԫ��ֵ
     */
    public T delete(){
        if(isEmpty()){
            throw new RuntimeException("empty queue");
        }
        T item=(T)queueArray[front];
        front=(front+1)%size;
        count--;
        return item;
    }
    /**
     * �õ�����Ԫ��ֵ
     * @return
     */
    public T getFront(){
        if(isEmpty()){
            throw new RuntimeException("empty queue");
        }
        return (T)queueArray[front];
    }
    /**
     * ��ն���
     */
    public void clear(){
        front=rear=count=0;
    }
    //����
    public static void main(String[] args){
        AQueue<Integer> aqueue=new AQueue<Integer>();
        aqueue.insert(1);  
        aqueue.insert(2); 
        aqueue.insert(3); 
        aqueue.insert(4); 
        aqueue.delete();
        System.out.println(aqueue.getFront());
        aqueue.delete();
        System.out.println(aqueue.getFront());
        aqueue.delete();
        System.out.println(aqueue.getFront());
        aqueue.delete();
        System.out.println(aqueue.getFront());
    }
}
