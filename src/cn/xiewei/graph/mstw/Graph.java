package cn.xiewei.graph.mstw;

import cn.xiewei.stack.Stack;

/**
 * ��Ȩ��С������
 * 
 * @author XW
 * @create_date 2019��10��25��
 */
public class Graph {
    private final int MAX_VERTS = 20;
    private final int INFINITY = 65535;
    private Vertex[] vertexList;// ��������
    private int[][] adjMat;// �ڽӾ���

    private Stack theStack;
    private char[] sortedArray;// ��������

    private int nVerts;// ��ǰ������

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

    public void addVertex(String[] vertexs) {// ��Ӷ���
        for (int i = 0; i < vertexs.length; i++) {
            vertexList[i] = new Vertex(vertexs[i]);
        }
        nVerts = vertexs.length;
    }

    public void addEdge(String[][] edges) {// ��ӱ�
        // ���ڽӾ�������ͼ������������
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
            vertexList[currentVert].isInTree = true;// ���ʹ���
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
                thePQ.removeM(queueIndex);// ɾ���ɹ�
                Edge theEdge = new Edge(currentVert, newVert, newDist);
                thePQ.insert(theEdge);
            }
        } else {
            Edge theEdge = new Edge(currentVert, newVert, newDist);
            thePQ.insert(theEdge);
        }
    }
}
