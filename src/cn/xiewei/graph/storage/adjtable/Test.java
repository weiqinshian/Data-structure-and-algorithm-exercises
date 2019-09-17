package cn.xiewei.graph.storage.adjtable;

/**
 * 测试：邻接表
 * 
 * @author XW
 * @create_date 2019年9月16日
 */
public class Test {
    public static void main(String[] args) {
        Graph graph = new Graph();
        System.out.println("该图的邻接表为：");
        outputGraph(graph);
    }

    /**
     * 输出图的邻接表的方法。
     * @param graph 要输出的图
     */
    public static void outputGraph(Graph graph){
        for (int i=0;i<graph.verNum;i++){
            Vertex vertex = graph.verArray[i];
            System.out.print(vertex.verName);

            Edge current = vertex.edgeLink;
            while (current != null){
                System.out.print("--"+current.weight+"-->"+current.tailName);
                current = current.broEdge;
            }
            System.out.println();
        }
    }
}