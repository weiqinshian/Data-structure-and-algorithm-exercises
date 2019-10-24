package cn.xiewei.graph.array;

import java.util.LinkedList;
import java.util.Queue;
/**
 * 邻接矩阵,存储无向图
 * 无向图的邻接矩阵是对称的
 * @author XW
 * @create_date 2019年9月5日
 */
public class MatrixNDG {

    int size;// 图顶点个数
    static char[] vertexs;// 图顶点名称
    static int[][] array;// 图关系矩阵
    private static boolean[] ifvisited; // 存放节点是否被访问过

    public MatrixNDG(char[] vertexs, char[][] edges) {
        size = vertexs.length;
        array = new int[size][size];// 设定图关系矩阵大小
        this.vertexs = vertexs;
        ifvisited = new boolean[size]; // 是否被访问过

        for (int i = 0; i < size; i++) // 初始化边
        {
            ifvisited[i] = false;
        }
        for (char[] c : edges) {// 设置矩阵值
            int p1 = getPosition(c[0]);// 根据顶点名称确定对应矩阵下标
            int p2 = getPosition(c[1]);

            array[p1][p2] = 1;// 无向图，在两个对称位置存储
            array[p2][p1] = 1;
        }

    }

    // 图的遍历输出
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

        System.out.println("打印矩阵，矩阵中存储边的权值和方向，无向图矩阵对称：");
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

    // 根据顶点名称获取对应的矩阵下标
    private int getPosition(char ch) {
        for (int i = 0; i < vertexs.length; i++)
            if (vertexs[i] == ch)
                return i;
        return -1;
    }

    // 深度优先遍历
    private static void dfs(int i) {
        ifvisited[i] = true;
        System.out.print("--->" + vertexs[i]); // visited(i); // 添加到被访问过的节点队列
        for (int j = 0; j < vertexs.length; j++) {
            if (!ifvisited[j] && array[i][j] == 1) {
                dfs(j); // 下次循环从vet[j]开始循环
            }
        }
    }

    // 广度优先遍历
    private static void bfs() {
        Queue<Integer> queue = new LinkedList<Integer>();

        for (int i = 0; i < vertexs.length; i++) {
            if (!ifvisited[i])// 判断是否被访问过，且其值是否为1
            {
                ifvisited[i] = true;
                System.out.print("--->" + vertexs[i]);  // 添加到被访问过的节点队列
                queue.add(i);//将此顶点坐标入队列
                
                while (!queue.isEmpty()) {
                  int  index=queue.remove();
                   for (int j = 0; j < vertexs.length; j++) {
                       if (!ifvisited[j] && array[index][j] == 1) {
                           ifvisited[j] = true;
                           System.out.print("--->" + vertexs[j]);  // 添加到被访问过的节点队列
                           queue.add(j);//将此顶点坐标入队列
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
        // 自定义"图"(输入矩阵队列)
        // 采用已有的"图"
        long start = System.nanoTime();

        for (int i = 0; i < 10000; i++) {
            pG = new MatrixNDG(vexs, edges);
        }
        printResult(vexs, array); // 打印图
        System.out.println("深度优先遍历开始：");
        for (int i = 0; i < vertexs.length; i++) {
            if (!ifvisited[i])// 判断是否被访问过，且其值是否为1
            {
                dfs(i);
            }
        }
        for (int i = 0; i < vertexs.length; i++) // 初始化访问状态
        {
            ifvisited[i] = false;
        }
        System.out.println();
        System.out.println("广度优先遍历开始：");
        bfs();
    }
}