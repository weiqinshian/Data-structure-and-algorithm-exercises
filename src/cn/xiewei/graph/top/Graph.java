package cn.xiewei.graph.top;

public class Graph {
    private final int MAX_VERTS = 20;
    private Vertex[] vertexList;// 顶点数组
    private int[][] arrays;// 邻接矩阵
    private char[] sortedArray;// 顶点数组
    private int nVerts;// 当前顶点数
    public Graph() {
        vertexList = new Vertex[MAX_VERTS];
        sortedArray= new char[MAX_VERTS];
        arrays = new int[MAX_VERTS][MAX_VERTS];

    }

    public void addVertex(char[] vertexs) {// 添加边
        for (int i = 0; i < vertexs.length; i++) {
            vertexList[i] = new Vertex(vertexs[i]);
        }
        nVerts = vertexs.length;
    }

    public void addEdge(char[][] edges) {// 添加边
        // 和邻接矩阵无向图差别仅仅在这里
        for (char[] c : edges) {
            int p1 = getPosition(c[0]);
            int p2 = getPosition(c[1]);
            arrays[p1][p2] = 1;
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
        int vertsNum = nVerts;
        while (nVerts > 0) {
            int currentVertex = noSuccessors();
            if (currentVertex == -1) {
                System.out.println("Error:graph has cycles");
                return;
            }
            sortedArray[nVerts - 1] = vertexList[currentVertex].label;
            deleteVertex(currentVertex);//在邻接矩阵中删除该节点
        }
        System.out.print("Topologically sorted order:");
        for (int j = 0; j < vertsNum; j++) {
            System.out.print(sortedArray[j]);
            System.out.print(" ");
        }
    }

    // 查找图中没有后继顶点的节点，返回，行号
    public int noSuccessors() {
        boolean isEdge;
        for (int row = 0; row < nVerts; row++) {
            isEdge = false;
            for (int col = 0; col < nVerts; col++) {
                if (arrays[row][col] > 0) {
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
                moveRowUp(row, nVerts);//移动行
            }

            for (int col = delVert; col < nVerts - 1; col++) {
                moveColLeft(col, nVerts - 1);//移动列
            }
        }
        nVerts--;
    }

    private void moveRowUp(int row, int length) {
        for (int col = 0; col < length; col++) {
            arrays[row][col] = arrays[row + 1][col];
        }
    }

    private void moveColLeft(int col, int length) {
        for (int row = 0; row < length; row++) {
            arrays[row][col] = arrays[row][col + 1];
        }
    }

}
