package cn.xiewei.graph.top;
/**
 * ͼ����������
 * @author XW
 * @create_date 2019��10��24��
 */
public class Test {
    public static void main(String[] args) {
        Graph theGrapt =new Graph();
        char[] vexs = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'  };
        theGrapt.addVertex(vexs);
        char[][] edges = new char[][] { { 'A', 'E' }, { 'A', 'D' }, { 'D', 'G' }, { 'E', 'G' }, { 'B', 'E' },
            { 'G', 'H' }, { 'C', 'F' }, { 'F', 'H' } };
         theGrapt.addEdge(edges);
         theGrapt.topo();

    }
}
