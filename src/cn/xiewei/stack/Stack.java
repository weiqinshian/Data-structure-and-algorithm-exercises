package cn.xiewei.stack;

public interface Stack {
    //���ض�ջ�Ĵ�С
    public int getSize();
    
    //�ж϶�ջ�Ƿ�Ϊ��
    public boolean isEmpty();
    
    //���ջ
    public void clearStack();
    
    //����Ԫ��e��ջ
    public void push(Object e);
    
    //ջ��Ԫ�س�ջ
    public Object pop() throws StackEmptyException;
    
    //ȡջ��Ԫ��
    public Object peek() throws StackEmptyException;
}
