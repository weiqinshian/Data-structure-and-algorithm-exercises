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
    public static final boolean UNDIRECTED_GRAPH = false;//����ͼ��־
    public static final boolean DIRECTED_GRAPH = true;//����ͼ��־
 
    public static final boolean ADJACENCY_MATRIX = true;//�ڽӾ���ʵ��
    public static final boolean ADJACENCY_LIST = false;//�ڽӱ�ʵ��
 
    public static final int MAX_VALUE = Integer.MAX_VALUE;
    private boolean graphType;
    private boolean method;
    private int vertexSize;
    private int matrixMaxVertex;
 
    //�洢���ж�����Ϣ��һά����
    private Object[] vertexesArray;
    //�洢ͼ�ж���֮�������ϵ�Ķ�ά����,���ߵĹ�ϵ
    private int[][] edgesMatrix;
 
    // ��¼��i���ڵ��Ƿ񱻷��ʹ�
    private boolean[] visited;
 
    /**
     * @param graphType ͼ�����ͣ�����ͼ/����ͼ
     * @param method    ͼ��ʵ�ַ�ʽ���ڽӾ���/�ڽӱ�
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
 
            //��������г�ʼ���������û�б߹�����ֵΪInteger���͵����ֵ
            for (int row = 0; row < edgesMatrix.length; row++) {
                for (int column = 0; column < edgesMatrix.length; column++) {
                    edgesMatrix[row][column] = MAX_VALUE;
                }
            }
        }
    }
 
    /**
     * �����������DFS��depth-first search�����ݹ�
     */
    public void DFS() {
        //�����Ǵӵ�һ����ӵĶ��㿪ʼ����
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
            throw new NullPointerException("û�����ֵ: " + obj);
        }
 
        for (int i = 0; i < vertexSize; i++) {
            visited[i] = false;
        }
 
        //����Ҫ����������ܷ�����if else�ĺ��棡
        traverse(index);
 
        //graphTypeΪtrueΪ����ͼ
        if (graphType) {
            for (int i = 0; i < vertexSize; i++) {
                if (!visited[i])
                    traverse(i);
            }
        }
 
    }
 
    // ������Ⱦ����ɿ�ʼ�������������û���˾ͻ��ݵ���һ������
    private void traverse(int i) {
        visited[i] = true;
        System.out.print(vertexesArray[i] + " ");
 
        //�����ǵݹ飬���j=-1,�÷�����Ȼ�����У�����ݵ���һ�����㣡����
        for (int j = firstAdjVex(i); j >= 0; j = nextAdjVex(i, j)) {
            if (!visited[j]) {
                traverse(j);
            }
        }
 
    }
 
    /**
     * ������ȱ����㷨 Breadth-first search���ǵݹ飩
     */
    public void BFS() {
        // LinkedListʵ����Queue�ӿ� FIFO
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < vertexSize; i++) {
            visited[i] = false;
        }
 
        //���ѭ����Ϊ��ȷ��ÿ�����㶼��������
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
    // ��ȷǵݹ����
    public void DFS2() {
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < vertexSize; i++) {
            visited[i] = false;
        }
        for (int i = 0; i < vertexSize; i++) {
            if (!visited[i]) {
                stack.add(i);
                // ���õ�i��Ԫ���Ѿ���ջ
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
 
    // ���һ��
    public int lastAdjVex(int i) {
        for (int j = vertexSize - 1; j >= 0; j--) {
            if (edgesMatrix[i][j] == 1)
                return j;
        }
        return -1;
    }
 
    // ��һ��
    public int lastAdjVex(int i, int k) {
        for (int j = k - 1; j >= 0; j--) {
            if (edgesMatrix[i][j] == 1)
                return j;
        }
        return -1;
    }
 
    public boolean addVertex(Object val) {
        /**
         * Java�У�assert�ؼ��֣���ʾ����
         * ���<boolean���ʽ>Ϊtrue����������ִ�С�
                                ���Ϊfalse��������׳�AssertionError������ִֹ�С�
         */
        assert (val != null);
        vertexesArray[vertexSize] = val;
        vertexSize++;
        return true;
    }
 
 
    public boolean addEdge(int vnum1, int vnum2) {
        /**
         * Java�У�assert�ؼ��֣���ʾ����
         * ���<boolean���ʽ>Ϊtrue����������ִ�С�
                                ���Ϊfalse��������׳�AssertionError������ִֹ�С�
         */
        assert (vnum1 >= 0 && vnum2 >= 0 && vnum1 != vnum2);
         //����ͼ
        if (graphType) {
            edgesMatrix[vnum1][vnum2] = 1;
 
        } else {
            edgesMatrix[vnum1][vnum2] = 1;
            edgesMatrix[vnum2][vnum1] = 1;
        }
        return true;
    }
    

 
}
