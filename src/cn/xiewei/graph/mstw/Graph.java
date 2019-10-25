package cn.xiewei.graph.mstw;

import cn.xiewei.stack.Stack;

/**
 * 带权最小生成树
 * 
 * @author XW
 * @create_date 2019年10月25日
 */
public class Graph {
    private final int MAX_VERTS = 20;
    private final int INFINITY = 65535;
    private Vertex[] vertexList;// 顶点数组
    private int[][] adjMat;// 邻接矩阵

    private Stack theStack;
    private char[] sortedArray;// 顶点数组

    private int nVerts;// 当前顶点数

    private int currentVert;

    private PriorityQ thePQ;

    private int nTree;

    public Graph() {
        vertexList = new Vertex[MAX_VERTS];
        sortedArray = new char[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        thePQ = new PriorityQ();
        for (int j = 0; j < MAX_VERTS; j++) {
            for (int k = 0; k < MAX_VERTS; k++) {
                adjMat[j][k] = INFINITY;
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
            adjMat[p1][p2] = Integer.parseInt(c[2]);
            adjMat[p2][p1] = Integer.parseInt(c[2]);
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
                int distance = adjMat[currentVert][j];
                if (distance == INFINITY) {
                    continue;
                }
                pubInPQ(j, distance);
            }
            if (this.thePQ.size() == 0) {
                System.out.println("Graph not connected");
                return;
            }
            Edge theEdge = thePQ.removeMin();
            int sourceVert = theEdge.srcVert;
            currentVert = theEdge.destVert;
            System.out.print(vertexList[sourceVert].label);
            System.out.print(vertexList[currentVert].label);
            System.out.print(" ");
        }
    }

    public void pubInPQ(int newVert, int newDist) {
        int queueIndex = thePQ.find(newVert);
        if (queueIndex != -1) {
            Edge temEdge = thePQ.peekN(queueIndex);
            int oldDist = temEdge.distance;
            if (oldDist > newDist) {
                thePQ.removeM(queueIndex);// 删除成功
                Edge theEdge = new Edge(currentVert, newVert, newDist);
                thePQ.insert(theEdge);
            }
        } else {
            Edge theEdge = new Edge(currentVert, newVert, newDist);
            thePQ.insert(theEdge);
        }
    }
}
