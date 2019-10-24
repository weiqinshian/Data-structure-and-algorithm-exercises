package cn.xiewei.graph.mst;

import cn.xiewei.stack.Stack;
import cn.xiewei.stack.array.StackArray;

/**
 * ��С������
 * 
 * @author XW
 * @create_date 2019��10��24��
 */
public class Graph {
    private final int MAX_VERTS = 20;
    private Vertex[] vertexList;// ��������
    private int[][] adjMat;// �ڽӾ���
    private Stack theStack;
    private int nVerts;// ��ǰ������

    public Graph() {
        vertexList = new Vertex[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        theStack=new StackArray();
        
    }
    public void addVertex(char[] vertexs) {// ��ӱ�
        for (int i = 0; i < vertexs.length; i++) {
            vertexList[i]=new Vertex(vertexs[i]);
        }        
        this.nVerts=vertexs.length;
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

    public void mst() {
        vertexList[0].wasVisited = true;
        theStack.push(0);
        while (!theStack.isEmpty()) {
            int currentVertex =(Integer) theStack.peek();
            int v = getAddjUnvisitedVertex(currentVertex);
            if (v == -1)
                theStack.pop();
            else {
                vertexList[v].wasVisited = true;
                theStack.push(v);
                displayVertex(currentVertex);
                displayVertex(v);
                System.out.print("--->");

            }
        }
        for(int j=0;j<nVerts;j++)
            vertexList[j].wasVisited=false;

    }

    public int getAddjUnvisitedVertex(int v) {
        for (int j = 0; j < nVerts; j++) {
            if (adjMat[v][j] == 1 && vertexList[j].wasVisited == false)
                return j;
        }
        return -1;
    }

}
