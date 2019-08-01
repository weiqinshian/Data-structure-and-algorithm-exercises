package cn.xiewei.stack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.xiewei.stack.linked.StackSLinked;

/**
 * 行编辑器
 * 
 * @author XW
 * @create_date 2019年8月1日
 */
public class LineEditDemo {
        public static void main(String[] args) throws IOException {
            Stack stack = new StackSLinked();          
            try {
                /**
                 * 两笔测试数据
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


