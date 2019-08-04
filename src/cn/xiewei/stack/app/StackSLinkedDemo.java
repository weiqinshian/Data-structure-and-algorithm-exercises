package cn.xiewei.stack.app;

import cn.xiewei.stack.Stack;
import cn.xiewei.stack.linked.StackSLinked;

/**
 * ����ת��demo��ʮ����ת�˽��ơ�
 * 
 * @author XW
 * @create_date 2019��7��31��
 */
public class StackSLinkedDemo {
    public static void main(String[] args) {
        baseConversion(2007);
    }
    
    public static void baseConversion(int i) {
        Stack s = new StackSLinked();
        while (i > 0) {
            s.push(i % 8 + "");
            i = i / 8;
        }
        while (!s.isEmpty())
            System.out.print((String) s.pop());
    }
}
