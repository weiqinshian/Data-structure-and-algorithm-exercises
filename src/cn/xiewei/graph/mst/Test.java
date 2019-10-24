package cn.xiewei.graph.mst;
/**
 * 最小生成树
 * 
 * @author XW
 * @create_date 2019年10月24日
 */
public class Test {

    public static void main(String[] args) {
        Graph theGrapt =new Graph();
        char[] vexs = { 'A', 'B', 'C', 'D', 'E' };
        theGrapt.addVertex(vexs);
        char[][] edges = new char[][] { { 'A', 'C' }, { 'A', 'D' }, { 'A', 'B' }, { 'A', 'E' }, { 'B', 'D' },
            { 'B', 'C' }, { 'B', 'E' }, { 'C', 'D' }, { 'C', 'E' },{ 'D', 'E' } };
         theGrapt.addEdge(edges);
         theGrapt.mst();

    }

}
