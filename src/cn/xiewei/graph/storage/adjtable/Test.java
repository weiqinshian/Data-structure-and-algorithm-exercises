package cn.xiewei.graph.storage.adjtable;

/**
 * ���ԣ��ڽӱ�
 * 
 * @author XW
 * @create_date 2019��9��16��
 */
public class Test {
    public static void main(String[] args) {
        Graph graph = new Graph();
        System.out.println("��ͼ���ڽӱ�Ϊ��");
        outputGraph(graph);
    }

    /**
     * ���ͼ���ڽӱ�ķ�����
     * @param graph Ҫ�����ͼ
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