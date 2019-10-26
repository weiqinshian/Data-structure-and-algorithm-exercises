package cn.xiewei.graph.path;

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

    private int nVerts;// ��ǰ������
    private int nTree;
    private int currentVert;
    private DisPar[] aPath;
    private int startToCurrent;

    // private Stack theStack;
    // private char[] sortedArray;// ��������

    public Graph() {
        vertexList = new Vertex[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        nTree = 0;
        aPath = new DisPar[MAX_VERTS];

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
//            adjMat[p2][p1] = Integer.parseInt(c[2]);
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

    public void path() {
        int startTree = 0;
        vertexList[startTree].isInTree = true;
        nTree = 1;
        for (int j = 0; j < nVerts; j++) {
            int temDist = adjMat[startTree][j];
            aPath[j] = new DisPar(startTree, temDist);
        }
        while (nTree < nVerts) {
            int indexMin = getMin();
            int minDist = aPath[indexMin].distance;
            if (minDist == INFINITY) {
                System.out.println("Thre are unreachable vertices");
                break;
            } else {
                currentVert = indexMin;
                startToCurrent = aPath[indexMin].distance;
            }
            vertexList[currentVert].isInTree = true;
            nTree++;
            adjust_aPath();
        }
        displayPaths();
        nTree = 0;
        for (int j = 0; j < nVerts; j++) {
            vertexList[j].isInTree = false;
        }
    }

    public void adjust_aPath() {
        int column = 1;
        while (column < nVerts) {
            if (vertexList[column].isInTree) {
                column++;
                continue;
            }
            int currentToFringe = adjMat[currentVert][column];
            int startToFringe = startToCurrent + currentToFringe;
            int sPathDist = aPath[column].distance;
            if (startToFringe < sPathDist) {
                aPath[column].parentVert = currentVert;
                aPath[column].distance = startToFringe;
            }
            column++;
        }

    }

    public int getMin() {
        int minDist = INFINITY;
        int indexMin = 0;
        for (int j = 1; j < nVerts; j++) {
            if (!vertexList[j].isInTree && aPath[j].distance < minDist) {
                minDist = aPath[j].distance;
                indexMin = j;
            }
        }
        return indexMin;
    }

    public void displayPaths() {
        for (int j = 0; j < nVerts; j++) {
            System.out.print(vertexList[j].label + "=");
            if (aPath[j].distance == INFINITY) {
                System.out.print("inf");
            } else {
                System.out.print(aPath[j].distance);
            }
            String parent = vertexList[aPath[j].parentVert].label;
            System.out.println("(" + parent + ")");
        }
        System.out.println();
    }

}
