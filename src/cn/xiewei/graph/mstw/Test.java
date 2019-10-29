package cn.xiewei.graph.mstw;
/**
 * 无向图带权最小生成树
 * @author XW
 * @create_date 2019年10月25日
 */
public class Test {
    public static void main(String[] args) {
        Graph theGrapt =new Graph();
        String[] vexs = { "A", "B", "C", "D", "E" , "F"};
        theGrapt.addVertex(vexs);
        String[][] edges = new String[][] {  { "A", "D", "4" }, { "A", "B" , "6" }, { "B", "D" , "7"},
            { "B", "C", "7" }, { "B", "E", "1" }, { "C", "D", "8" }, { "C", "E", "5"  },{ "D", "E" , "12"},{ "C", "F" , "6"},{ "F", "E" , "7"} };
         theGrapt.addEdge(edges);
         theGrapt.mstw();
    }
}
