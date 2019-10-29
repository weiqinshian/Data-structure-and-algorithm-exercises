package cn.xiewei.graph.mstw;

/**
 * 无向图带权最小生成树
 * 
 * @author XW
 * @create_date 2019年10月25日
 */
public class Graph {
    private final int MAX_VERTS = 20;
    private final int INFINITY = 65535;
    private Vertex[] vertexList;// 顶点数组
    private int[][] arrays;// 邻接矩阵
    private int nVerts;// 当前顶点数
    private int currentVert;
    private PriorityQ queue;
    private int nTree;

    public Graph() {
        vertexList = new Vertex[MAX_VERTS];
        arrays = new int[MAX_VERTS][MAX_VERTS];
        queue = new PriorityQ();
        for (int j = 0; j < MAX_VERTS; j++) {
            for (int k = 0; k < MAX_VERTS; k++) {
                arrays[j][k] = INFINITY;
            }
        }
    }

    public void addVertex(String[] vertexs) {// 添加顶点
        for (int i = 0; i < vertexs.length; i++) {
            vertexList[i] = new Vertex(vertexs[i]);
        }
        nVerts = vertexs.length;
    }

    public void addEdge(String[][] edges) {// 添加边
        // 和邻接矩阵无向图差别仅仅在这里
        for (String[] c : edges) {
            int p1 = getPosition(c[0]);
            int p2 = getPosition(c[1]);
            arrays[p1][p2] = Integer.parseInt(c[2]);
            arrays[p2][p1] = Integer.parseInt(c[2]);
        }
    }

    private int getPosition(String ch) {
        for (int i = 0; i < vertexList.length; i++)
            if (vertexList[i].label == ch)
                return i;
        return -1;
    }

    public void displayVertex(int v) {
        System.out.print(vertexList[v].label);
    }

    public void mstw() {
        currentVert = 0;
        while (nTree < nVerts - 1) {
            vertexList[currentVert].isInTree = true;// 访问过了
            nTree++;
            for (int j = 0; j < nVerts; j++) {
                if (j == currentVert)
                    continue;
                if (vertexList[j].isInTree)
                    continue;
                int distance = arrays[currentVert][j];
                if (distance == INFINITY) {
                    continue;
                }
                pubInPQ(j, distance);//放入优先队列
            }
            if (this.queue.size() == 0) {
                System.out.println("Graph not connected");
                return;
            }
            Edge theEdge = queue.removeMin();
            int sourceVert = theEdge.srcVert;
            currentVert = theEdge.destVert;
            System.out.print(vertexList[sourceVert].label);
            System.out.print(vertexList[currentVert].label);
            System.out.print(" ");
        }
    }

    public void pubInPQ(int newVert, int distance) {
        int queueIndex = queue.find(newVert);
        if (queueIndex != -1) {
            Edge temEdge = queue.peekN(queueIndex);
            int oldDist = temEdge.distance;
            if (oldDist > distance) {
                queue.removeM(queueIndex);// 删除成功
                Edge theEdge = new Edge(currentVert, newVert, distance);
                queue.insert(theEdge);
            }
        } else {
            Edge theEdge = new Edge(currentVert, newVert, distance);
            queue.insert(theEdge);
        }
    }
}
