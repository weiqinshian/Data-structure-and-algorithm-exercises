package cn.xiewei.graph.storage.adjtable2;
/**
 * 顺序队列
 * 
 * @author XW
 * @create_date 2019年9月17日
 */
public class AQueue<T> {
    /**
     * 队首所在数组元素的下标
     */
    private int front;
    /**
     * 队尾所在数组元素的下标
     */
    private int rear;
    /**
     * 队中元素的个数
     */
    private int count;
    /**
     * 存放队列元素的数组
     */
    private Object[] queueArray;
    /**
     * 数组的规模
     */
    private int size;
    /**
     * 构造方法
     */
    public AQueue(){
        this.size=10;
        this.queueArray=new Object[size];
        front=0;
        rear=0; 
        count=0;
    }
    /**
     * 构造方法
     * @param size 数组大小
     */
    public AQueue(int size){
        this.size=size;
        this.queueArray=new Object[size];
        front=0;
        rear=0; 
        count=0;
    }
    /**
     * 判断是否已满
     * @return
     */
    public boolean isFull(){
        return count==size;
    }
    /**
     * 判断是否为空
     * @return
     */
    public boolean isEmpty(){
        return count==0;
    }
    /**
     * 在队尾插入data为item的元素
     * @param item 数据域
     * @return 成功返回true
     */
    public boolean insert(T item){
        if(isFull()){
            System.out.println("full queue");
            return false;
        }
        queueArray[rear]=item;
        rear=(rear+1)%size;//修改队尾指针
        count++;
        return true;
    }
    /**
     * 删除队首元素
     * @return 返回队首元素值
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
     * 得到队首元素值
     * @return
     */
    public T getFront(){
        if(isEmpty()){
            throw new RuntimeException("empty queue");
        }
        return (T)queueArray[front];
    }
    /**
     * 清空队列
     */
    public void clear(){
        front=rear=count=0;
    }
    //测试
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
