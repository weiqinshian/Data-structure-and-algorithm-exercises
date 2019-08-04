package cn.xiewei.stack.linked;
import cn.xiewei.stack.Stack;
import cn.xiewei.stack.StackEmptyException;
public class StackSLinked implements Stack {
    private SLNode top; //�����׽������
    private int size;   //ջ�Ĵ�С
    
    public StackSLinked() {
        top = null;
        size = 0;
    }

    //���ض�ջ�Ĵ�С
    public int getSize() {
        return size;
    }

    //���ջ
    public void clearStack() {
        while ( this.getSize()!=0) {
            this.pop();
        }
     }
    
    //�ж϶�ջ�Ƿ�Ϊ��
    public boolean isEmpty() {
        return size==0;
    }

    //����Ԫ��e��ջ
    public void push(Object e) {
        SLNode q = new SLNode(e,top);
        top = q;
        size++;
    }

    //ջ��Ԫ�س�ջ
    public Object pop() throws StackEmptyException {
        if (size<1)
           return null;     //            throw new StackEmptyException("���󣬶�ջΪ�ա�");
        Object obj = top.getData();
        top = top.getNext();
        size--;
        return obj;
    }

    //ȡջ��Ԫ��
    public Object peek() throws StackEmptyException {
        if (size<1)
            throw new StackEmptyException("���󣬶�ջΪ�ա�");
        return top.getData();
    }
  }
