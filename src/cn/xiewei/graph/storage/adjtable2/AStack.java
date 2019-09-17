package cn.xiewei.graph.storage.adjtable2;


/**
 * 顺序栈
 * 
 * @author XW
 * @create_date 2019年9月17日
 */
public class AStack<T> {
    /**
     * 数组的大小
     */
    private int size;
    /**
     * 存放元素的数组
     */
    private Object[] stackArray;
    /**
     * 栈顶元素的下标
     */
    private int top;
    /**
     * 构造函数
     * @param maxSize 数组大小
     */
    public AStack(int maxSize){
        this.size=maxSize;
        this.stackArray=new Object[size];
        this.top=-1;
    }
    /**
     * 判断是否栈满
     * @return
     */
    public boolean isFull(){
        return top==size-1;
    }
    /**
     * 向栈顶压入一个元素
     * @param item 要入栈的元素值
     * @return 成功返回true
     */
    public boolean push(T item){
        if(isFull()){
            System.out.println("full stack");
            return false;
        }
        else{
            stackArray[++top]=item;
            return true;
        }
    }
    /**
     * 判断栈是否为空
     * @return
     */
    public boolean isEmpty(){
        return top==-1;
    }
    /**
     * 从栈顶弹出一个元素
     * @return 返回栈顶元素值
     */
    public T pop(){
        if(isEmpty()){
            throw new RuntimeException("Empty stack");
        }
        return (T)stackArray[top--];
    }
    /**
     * 读取栈顶元素
     * @return 返回栈顶元素值
     */
    public T peek(){
        if(isEmpty()){
            throw new RuntimeException("Empty stack");
        }
        return (T)stackArray[top];
    }
    /**
     * 清空栈
     */
    public void clear(){
        top=-1;
    }
    //测试
    public static void main(String[] args){
        AStack<Character> astack=new AStack<Character>(10);
        astack.push('a');
        astack.push('b');
        astack.push('c');
        astack.push('d');
        System.out.println(astack.peek());
        astack.pop();
        System.out.println(astack.peek());
        astack.clear();
        System.out.println(astack.peek());
    }
}
