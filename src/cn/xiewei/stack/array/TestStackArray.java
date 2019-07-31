package cn.xiewei.stack.array;
public class TestStackArray {
    public static void main(String[] args) {
        StackArray stackArray=new StackArray();
        stackArray.push("a");
        stackArray.push("b");
        stackArray.push("c");
        stackArray.push("d");
        int length=stackArray.getSize();
        for (int i = 0; i < length; i++) {
            System.out.println(stackArray.pop());
        }
    }
}
