package cn.xiewei.graph.array;

/**
 * 邻接矩阵有向图
 *  邻接矩阵有向图和邻接矩阵无向图结构一样，只是在存储矩阵值时只存储对应方向的点。
 * 
 * @author XW
 * @create_date 2019年9月5日
 */

public class MatrixDG {
    int size;
    char[] vertexs;
    static int[][] matrix;
    public MatrixDG(char[] vertexs, char[][] edges) {
        size = vertexs.length;
        matrix = new int[size][size];
        this.vertexs = vertexs;
        // 和邻接矩阵无向图差别仅仅在这里
        for (char[] c : edges) {
            int p1 = getPosition(c[0]);
            int p2 = getPosition(c[1]);

            matrix[p1][p2] = 1;
        }
    }

    private int getPosition(char ch) {
        for (int i = 0; i < vertexs.length; i++)
            if (vertexs[i] == ch)
                return i;
        return -1;
    }

    public static void printResult(char[] vexs, int[][] edgs) {
        System.out.println("顶点的数组：");
        System.out.print("[");
        for (int i = 0; i < vexs.length; i++) {
            if (i == vexs.length - 1) {
                System.out.print(vexs[i]);
                break;
            } else
                System.out.print(vexs[i] + ",");
        }
        System.out.println("]");
    
        System.out.println("打印矩阵，矩阵中存储边的权值和方向，有向图矩阵不对称：");
        System.out.print("  ");
        for (int i = 0; i < vexs.length; i++) {
            if (i == vexs.length - 1) {
                System.out.print(vexs[i]);
                break;
            } else
                System.out.print(vexs[i] + ",");
        }
        System.out.println();
        for (int i = 0; i < vexs.length; i++) {
            System.out.print(vexs[i] + "|");
            for (int j = 0; j < vexs.length; j++) {
                if (j == vexs.length - 1) {
                    System.out.print(edgs[i][j]);
                    break;
                } else
                    System.out.print(edgs[i][j] + ",");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        char[] vexs = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K' };
        char[][] edges = new char[][] { { 'A', 'C' }, { 'A', 'D' }, { 'A', 'F' }, { 'B', 'C' }, { 'C', 'D' },
                { 'E', 'G' }, { 'D', 'G' }, { 'I', 'J' }, { 'J', 'G' }, };
        MatrixDG pG = new MatrixDG(vexs, edges);
        printResult(vexs, matrix);
    }
}