package cn.xiewei.array;

public class MatrixDemo1 {
    public static void main(String[] args) {
        // 创建一个二维数组
        int arr[][] = new int[4][6];
        // 初始化二维数组
        arr[1][2] = 1;
        arr[2][1] = 2;
        arr[2][3] = 3;
        // 输出二维数组
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
