package cn.xiewei.graph.array;

/**
 * �ڽӾ�������ͼ
 *  �ڽӾ�������ͼ���ڽӾ�������ͼ�ṹһ����ֻ���ڴ洢����ֵʱֻ�洢��Ӧ����ĵ㡣
 * 
 * @author XW
 * @create_date 2019��9��5��
 */

public class MatrixDG {
    int size;
    char[] vertexs;
    static int[][] matrix;
    public MatrixDG(char[] vertexs, char[][] edges) {
        size = vertexs.length;
        matrix = new int[size][size];
        this.vertexs = vertexs;
        // ���ڽӾ�������ͼ������������
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
        System.out.println("��������飺");
        System.out.print("[");
        for (int i = 0; i < vexs.length; i++) {
            if (i == vexs.length - 1) {
                System.out.print(vexs[i]);
                break;
            } else
                System.out.print(vexs[i] + ",");
        }
        System.out.println("]");
    
        System.out.println("��ӡ���󣬾����д洢�ߵ�Ȩֵ�ͷ�������ͼ���󲻶Գƣ�");
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