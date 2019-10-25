package cn.xiewei.graph.top;

import cn.xiewei.stack.Stack;
import cn.xiewei.stack.array.StackArray;

public class Graph {
    private final int MAX_VERTS = 20;
    private Vertex[] vertexList;// ��������
    private int[][] adjMat;// �ڽӾ���
    private Stack theStack;

    private char[] sortedArray;// ��������
    private int nVerts;// ��ǰ������

    public Graph() {
        vertexList = new Vertex[MAX_VERTS];
        sortedArray= new char[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        theStack = new StackArray();

    }

    public void addVertex(char[] vertexs) {// ��ӱ�
        for (int i = 0; i < vertexs.length; i++) {
            vertexList[i] = new Vertex(vertexs[i]);
        }
        nVerts = vertexs.length;
    }

    public void addEdge(char[][] edges) {// ��ӱ�
        // ���ڽӾ�������ͼ������������
        for (char[] c : edges) {
            int p1 = getPosition(c[0]);
            int p2 = getPosition(c[1]);
            adjMat[p1][p2] = 1;
        }
    }

    private int getPosition(char ch) {
        for (int i = 0; i < vertexList.length; i++)
            if (vertexList[i].label == ch)
                return i;
        return -1;
    }

    public void displayVertex(int v) {
        System.out.print(vertexList[v].label);
    }

    public void topo() {
        int originVerts = nVerts;
        while (nVerts > 0) {
            int currentVertex = noSuccessors();
            if (currentVertex == -1) {
                System.out.println("Error:graph has cycles");
                return;
            }
            sortedArray[nVerts - 1] = vertexList[currentVertex].label;
            deleteVertex(currentVertex);//���ڽӾ�����ɾ���ýڵ�
        }
        System.out.print("Topologically sorted order:");
        for (int j = 0; j < originVerts; j++) {
            System.out.print(sortedArray[j]);
            System.out.println();
        }
    }

    // ����ͼ��û�к�̶���Ľڵ㣬���أ��к�
    public int noSuccessors() {
        boolean isEdge;
        for (int row = 0; row < nVerts; row++) {
            isEdge = false;
            for (int col = 0; col < nVerts; col++) {
                if (adjMat[row][col] > 0) {
                    isEdge = true;
                    break;
                }
            }
            if (!isEdge)
                return row;
        }
        return -1;
    }

    public void deleteVertex(int delVert) {
        if (delVert != nVerts - 1) {
            for (int j = delVert; j < nVerts - 1; j++) {
                vertexList[j] = vertexList[j + 1];
            }
            for (int row = delVert; row < nVerts - 1; row++) {
                moveRowUp(row, nVerts);
            }

            for (int col = delVert; col < nVerts - 1; col++) {
                moveColLeft(col, nVerts - 1);
            }

           
        }
        nVerts--;
    }

    private void moveRowUp(int row, int length) {
        for (int col = 0; col < length; col++) {
            adjMat[row][col] = adjMat[row + 1][col];
        }
    }

    private void moveColLeft(int col, int length) {
        for (int row = 0; row < length; row++) {
            adjMat[row][col] = adjMat[row][col + 1];
        }
    }

}
