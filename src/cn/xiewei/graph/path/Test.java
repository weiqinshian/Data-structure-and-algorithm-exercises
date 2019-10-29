package cn.xiewei.graph.path;
/**
 * �����Ȩͼ�����·������
 * 
 * @author XW
 * @create_date 2019��10��26��
 */
public class Test {
    public static void main(String[] args) {
        Graph theGrapt =new Graph();
        String[] vexs = { "A", "B", "C", "D", "E" };
        theGrapt.addVertex(vexs);
        String[][] edges = new String[][] {  { "A", "D", "80" }, { "A", "B" , "50" }, { "B", "D" , "90"},
            { "B", "C", "60" }, { "E", "B", "50" }, { "D", "C", "20" }, { "C", "E", "40"  },{ "D", "E" , "70"}};
         theGrapt.addEdge(edges);
         theGrapt.path();
    }
}
