package cn.xiewei.stack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.xiewei.stack.linked.StackSLinked;

/**
 * �б༭��
 * 
 * @author XW
 * @create_date 2019��8��1��
 */
public class LineEditDemo {
        public static void main(String[] args) throws IOException {
            Stack stack = new StackSLinked();          
            try {
                /**
                 * ���ʲ�������
                 * outcha@putchar(*s=#++)
                 * whli##ilr#e(s#*s)
                 */
                char ch = (char) System.in.read();//
                while (ch != '\n') {
                    if (ch == '#') {
                        stack.pop();
                    } else if (ch == '@') {
                       stack.clearStack();
                    } else {
                        stack.push(ch);
                    }
                    ch = (char) System.in.read();
                }
                List<Character> list=new ArrayList<>();
                while(stack.getSize()!=0){
                    list.add((Character)stack.pop());
                }
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(list.get(list.size()-1-i));
                }

            } catch (IOException e) {
                System.out.println(e.fillInStackTrace());
                throw e;
            }


        }
   

}


