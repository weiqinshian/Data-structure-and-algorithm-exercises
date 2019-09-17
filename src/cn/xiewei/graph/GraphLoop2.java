package cn.xiewei.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class GraphLoop2 {
    
    public static void main(String[] args) {
        GraphLoop2 g = new GraphLoop2(GraphLoop2.DIRECTED_GRAPH, GraphLoop2.ADJACENCY_MATRIX, 6);
        g.addVertex("1");
        g.addVertex("2");
        g.addVertex("3");
        g.addVertex("4");
        g.addVertex("5");
        g.addVertex("6");
 
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 1);
        g.addEdge(2, 4);
        g.addEdge(3, 5);
        g.addEdge(2, 4);
        g.addEdge(4, 5);
 
        g.DFS();
        System.out.println();
        g.DFS2();
        System.out.println();
        g.DFS("2");
 
        System.out.println();
        g.BFS();


    }
    public static final boolean UNDIRECTED_GRAPH = false;//无向图标志
    public static final boolean DIRECTED_GRAPH = true;//有向图标志
 
    public static final boolean ADJACENCY_MATRIX = true;//邻接矩阵实现
    public static final boolean ADJACENCY_LIST = false;//邻接表实现
 
    public static final int MAX_VALUE = Integer.MAX_VALUE;
    private boolean graphType;
    private boolean method;
    private int vertexSize;
    private int matrixMaxVertex;
 
    //存储所有顶点信息的一维数组
    private Object[] vertexesArray;
    //存储图中顶点之间关联关系的二维数组,及边的关系
    private int[][] edgesMatrix;
 
    // 记录第i个节点是否被访问过
    private boolean[] visited;
 
    /**
     * @param graphType 图的类型：有向图/无向图
     * @param method    图的实现方式：邻接矩阵/邻接表
     */
    public GraphLoop2(boolean graphType, boolean method, int size) {
        this.graphType = graphType;
        this.method = method;
        this.vertexSize = 0;
        this.matrixMaxVertex = size;
 
        if (this.method) {
            visited = new boolean[matrixMaxVertex];
            vertexesArray = new Object[matrixMaxVertex];
            edgesMatrix = new int[matrixMaxVertex][matrixMaxVertex];
 
            //对数组进行初始化，顶点间没有边关联的值为Integer类型的最大值
            for (int row = 0; row < edgesMatrix.length; row++) {
                for (int column = 0; column < edgesMatrix.length; column++) {
                    edgesMatrix[row][column] = MAX_VALUE;
                }
            }
        }
    }
 
    /**
     * 深度优先搜索DFS（depth-first search），递归
     */
    public void DFS() {
        //这里是从第一上添加的顶点开始搜索
        DFS(vertexesArray[0]);
    }
 
    public void DFS(Object obj) {
        int index = -1;
        for (int i = 0; i < vertexSize; i++) {
            if (vertexesArray[i].equals(obj)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new NullPointerException("没有这个值: " + obj);
        }
 
        for (int i = 0; i < vertexSize; i++) {
            visited[i] = false;
        }
 
        //这里要想清楚，不能放下面if else的后面！
        traverse(index);
 
        //graphType为true为有向图
        if (graphType) {
            for (int i = 0; i < vertexSize; i++) {
                if (!visited[i])
                    traverse(i);
            }
        }
 
    }
 
    // 深度优先就是由开始点向最深处遍历，没有了就回溯到上一级顶点
    private void traverse(int i) {
        visited[i] = true;
        System.out.print(vertexesArray[i] + " ");
 
        //由于是递归，如果j=-1,该方法仍然会运行，会回溯到上一级顶点！！！
        for (int j = firstAdjVex(i); j >= 0; j = nextAdjVex(i, j)) {
            if (!visited[j]) {
                traverse(j);
            }
        }
 
    }
 
    /**
     * 广度优先遍历算法 Breadth-first search（非递归）
     */
    public void BFS() {
        // LinkedList实现了Queue接口 FIFO
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < vertexSize; i++) {
            visited[i] = false;
        }
 
        //这个循环是为了确保每个顶点都被遍历到
        for (int i = 0; i < vertexSize; i++) {
            if (!visited[i]) {
                queue.add(i);
                visited[i] = true;
                System.out.print(vertexesArray[i] + " ");
 
                while (!queue.isEmpty()) {
                    int row = queue.remove();
 
                    for (int k = firstAdjVex(row); k >= 0; k = nextAdjVex(row, k)) {
                        if (!visited[k]) {
                            queue.add(k);
                            visited[k] = true;
                            System.out.print(vertexesArray[k] + " ");
                        }
                    }
 
                }
            }
        }
    }
 
    private int firstAdjVex(int row) {
        for (int column = 0; column < vertexSize; column++) {
            if (edgesMatrix[row][column] == 1)
                return column;
        }
        return -1;
    }
 
    private int nextAdjVex(int row, int k) {
        for (int j = k + 1; j < vertexSize; j++) {
            if (edgesMatrix[row][j] == 1)
                return j;
        }
        return -1;
    }
 
    /*********************************************************************/
    // 深度非递归遍历
    public void DFS2() {
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < vertexSize; i++) {
            visited[i] = false;
        }
        for (int i = 0; i < vertexSize; i++) {
            if (!visited[i]) {
                stack.add(i);
                // 设置第i个元素已经进栈
                visited[i] = true;
                while (!stack.isEmpty()) {
                    int j = stack.pop();
                    System.out.print(vertexesArray[j] + " ");
 
                    for (int k = lastAdjVex(j); k >= 0; k = lastAdjVex(j, k)) {
                        if (!visited[k]) {
                            stack.add(k);
                            visited[k] = true;
                        }
                    }
                }
            }
        }
    }
 
    // 最后一个
    public int lastAdjVex(int i) {
        for (int j = vertexSize - 1; j >= 0; j--) {
            if (edgesMatrix[i][j] == 1)
                return j;
        }
        return -1;
    }
 
    // 上一个
    public int lastAdjVex(int i, int k) {
        for (int j = k - 1; j >= 0; j--) {
            if (edgesMatrix[i][j] == 1)
                return j;
        }
        return -1;
    }
 
    public boolean addVertex(Object val) {
        /**
         * Java中，assert关键字，表示断言
         * 如果<boolean表达式>为true，则程序继续执行。
                                如果为false，则程序抛出AssertionError，并终止执行。
         */
        assert (val != null);
        vertexesArray[vertexSize] = val;
        vertexSize++;
        return true;
    }
 
 
    public boolean addEdge(int vnum1, int vnum2) {
        /**
         * Java中，assert关键字，表示断言
         * 如果<boolean表达式>为true，则程序继续执行。
                                如果为false，则程序抛出AssertionError，并终止执行。
         */
        assert (vnum1 >= 0 && vnum2 >= 0 && vnum1 != vnum2);
         //有向图
        if (graphType) {
            edgesMatrix[vnum1][vnum2] = 1;
 
        } else {
            edgesMatrix[vnum1][vnum2] = 1;
            edgesMatrix[vnum2][vnum1] = 1;
        }
        return true;
    }
    

 
}
