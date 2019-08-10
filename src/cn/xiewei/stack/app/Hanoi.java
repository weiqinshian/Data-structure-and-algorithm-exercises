package cn.xiewei.stack.app;

import java.util.Scanner;
/**
 * 汉诺塔问题
 * 
 * @author XW
 * @create_date 2019年8月10日
 */
public class Hanoi {

    public static void main(String[] args) {
        System.out.println("请输入A塔上盘子的个数");
        System.out.println("请输入一个整数后按回车键：");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        hanoi('A', 'B', 'C', n);
        scanner.close();
    }
    public static void move(char x, char y, int n) {
        System.out.println("把编号为[" + n + "]盘从" + x + " 移动到 " + y);
    }

    public static void hanoi(char A, char B, char C, int n) {
        if (n == 1) {
            move(A, C, 1);
        } else {
            /*
             * 步骤  第一步，移走最上面的盘子，剩下一个最大的 第二步，把最大的盘子移到目标盘  第三步，移回第一步的盘子到目标盘
             */
            hanoi(A, C, B, n - 1);// 借助 【C】 把n-1个盘从【A】移到 【B】
            move(A, C, n);// 把最大的盘子移动到【C】
            hanoi(B, A, C, n - 1);// 借助 【A】 把n-1个盘从【B】移到 【C】
        }
    }
}
