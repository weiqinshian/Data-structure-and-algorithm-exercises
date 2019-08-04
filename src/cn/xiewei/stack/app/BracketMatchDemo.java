package cn.xiewei.stack.app;

import cn.xiewei.stack.Stack;
import cn.xiewei.stack.linked.StackSLinked;

public class BracketMatchDemo {

    public static void main(String[] args) {
        String s = "{[{}]([])}";
        boolean flag = bracketMatch(s);
        if (flag) {
            System.out.println(s + ",括号嵌套的语法格式正常");
        } else {
            System.out.println(s + ",括号嵌套的语法格式不正常");
        }
    }
    public static boolean bracketMatch(String str) {
        Stack s = new StackSLinked();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            switch (c) {
            case '{':
            case '[':
            case '(':
                s.push(Integer.valueOf(c));
                break;
            case '}':
                if (!s.isEmpty() && ((Integer) s.pop()).intValue() == '{')
                    break;
                else
                    return false;
            case ']':
                if (!s.isEmpty() && ((Integer) s.pop()).intValue() == '[')
                    break;
                else
                    return false;
            case ')':
                if (!s.isEmpty() && ((Integer) s.pop()).intValue() == '(')
                    break;
                else
                    return false;
            }
        }
        if (s.isEmpty())
            return true;
        else
            return false;
    }

}
