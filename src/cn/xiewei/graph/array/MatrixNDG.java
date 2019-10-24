package cn.xiewei.graph.array;

import java.util.LinkedList;
import java.util.Queue;
/**
 * �ڽӾ���,�洢����ͼ
 * ����ͼ���ڽӾ����ǶԳƵ�
 * @author XW
 * @create_date 2019��9��5��
 */
public class MatrixNDG {

    int size;// ͼ�������
    static char[] vertexs;// ͼ��������
    static int[][] array;// ͼ��ϵ����
    private static boolean[] ifvisited; // ��Žڵ��Ƿ񱻷��ʹ�

    public MatrixNDG(char[] vertexs, char[][] edges) {
        size = vertexs.length;
        array = new int[size][size];// �趨ͼ��ϵ�����С
        this.vertexs = vertexs;
        ifvisited = new boolean[size]; // �Ƿ񱻷��ʹ�

        for (int i = 0; i < size; i++) // ��ʼ����
        {
            ifvisited[i] = false;
        }
        for (char[] c : edges) {// ���þ���ֵ
            int p1 = getPosition(c[0]);// ���ݶ�������ȷ����Ӧ�����±�
            int p2 = getPosition(c[1]);

            array[p1][p2] = 1;// ����ͼ���������Գ�λ�ô洢
            array[p2][p1] = 1;
        }

    }

    // ͼ�ı������
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

        System.out.println("��ӡ���󣬾����д洢�ߵ�Ȩֵ�ͷ�������ͼ����Գƣ�");
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

    // ���ݶ������ƻ�ȡ��Ӧ�ľ����±�
    private int getPosition(char ch) {
        for (int i = 0; i < vertexs.length; i++)
            if (vertexs[i] == ch)
                return i;
        return -1;
    }

    // ������ȱ���
    private static void dfs(int i) {
        ifvisited[i] = true;
        System.out.print("--->" + vertexs[i]); // visited(i); // ��ӵ������ʹ��Ľڵ����
        for (int j = 0; j < vertexs.length; j++) {
            if (!ifvisited[j] && array[i][j] == 1) {
                dfs(j); // �´�ѭ����vet[j]��ʼѭ��
            }
        }
    }

    // ������ȱ���
    private static void bfs() {
        Queue<Integer> queue = new LinkedList<Integer>();

        for (int i = 0; i < vertexs.length; i++) {
            if (!ifvisited[i])// �ж��Ƿ񱻷��ʹ�������ֵ�Ƿ�Ϊ1
            {
                ifvisited[i] = true;
                System.out.print("--->" + vertexs[i]);  // ��ӵ������ʹ��Ľڵ����
                queue.add(i);//���˶������������
                
                while (!queue.isEmpty()) {
                  int  index=queue.remove();
                   for (int j = 0; j < vertexs.length; j++) {
                       if (!ifvisited[j] && array[index][j] == 1) {
                           ifvisited[j] = true;
                           System.out.print("--->" + vertexs[j]);  // ��ӵ������ʹ��Ľڵ����
                           queue.add(j);//���˶������������
                       }
                   }
                    
                }
            }
        }
        
   
    }

    public static void main(String[] args) {
        char[] vexs = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K' };
        char[][] edges = new char[][] { { 'A', 'C' }, { 'A', 'D' }, { 'A', 'F' }, { 'B', 'C' }, { 'C', 'D' },
                { 'E', 'G' }, { 'D', 'G' }, { 'I', 'J' }, { 'J', 'G' }, };
        MatrixNDG pG = null;
        // �Զ���"ͼ"(����������)
        // �������е�"ͼ"
        long start = System.nanoTime();

        for (int i = 0; i < 10000; i++) {
            pG = new MatrixNDG(vexs, edges);
        }
        printResult(vexs, array); // ��ӡͼ
        System.out.println("������ȱ�����ʼ��");
        for (int i = 0; i < vertexs.length; i++) {
            if (!ifvisited[i])// �ж��Ƿ񱻷��ʹ�������ֵ�Ƿ�Ϊ1
            {
                dfs(i);
            }
        }
        for (int i = 0; i < vertexs.length; i++) // ��ʼ������״̬
        {
            ifvisited[i] = false;
        }
        System.out.println();
        System.out.println("������ȱ�����ʼ��");
        bfs();
    }
}