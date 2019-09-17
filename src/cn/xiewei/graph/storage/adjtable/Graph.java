package cn.xiewei.graph.storage.adjtable;
import java.util.Scanner;
/**
 * �ڽӱ�洢ͼ
 * ͼ�࣬�ڹ��췽�������ͼ�Ĺ���
 * @author XW
 * @create_date 2019��9��16��
 */
public class Graph {
    /**
     * ͼ�Ľڵ����
     */
    public int verNum;
    /**
     * ͼ�ıߵ�����
     */
    int edgeNum;
    /**
     * ͼ���ڽӱ��д洢�ڵ������
     */
    Vertex[] verArray;

    /**
     * Graph��Ĺ��췽�������ζ�ȡ�ڵ㡢�ߵ���Ϣ�����ͼ�Ĺ�����
     */
    public Graph() {
        Scanner scan = new Scanner(System.in);
        System.out.println("������ڵ�����ͱߵĸ�����");
        verNum = scan.nextInt();
        edgeNum = scan.nextInt();
        verArray = new Vertex[verNum];

        System.out.println("����������ڵ������:");
        for (int i=0;i<verNum;i++){
            Vertex vertex = new Vertex();
            vertex.verName = scan.next();
            vertex.edgeLink = null;
            verArray[i] = vertex;
        }

        System.out.println("�밴��ͷ�ڵ� Ȩֵ β�ڵ� �س�������ʽ��������ߵ���Ϣ");
        for (int i=0;i<edgeNum;i++){
            String preName = scan.next();
            int weight = scan.nextInt();
            String folName = scan.next();

            Vertex preV = getVertex(preName);
            Vertex folV = getVertex(folName);
            if (preV == null || folV == null){
                System.out.println("������������˲����ڵĶ��㣡����������");
                i--;
                continue;
            }

            Edge edge = new Edge();
            edge.tailName = folName;
            edge.weight = weight;

            //���߼��뵽�ڵ��������ȥ
            edge.broEdge = preV.edgeLink;
            preV.edgeLink = edge;

//            �������������Σ��򴴽���������ͼ��������������ͼ
//            edge.tailName = preName;
//            edge.broEdge  = folV.edgeLink;
//            folV.edgeLink = edge;
        }
    }

    /**
     * ���ݽڵ����ƻ�ȡ�ýڵ�
     * @param verName �ڵ������
     * @return �ڵ��null
     */
    public Vertex getVertex(String verName){
        for (int i=0;i<verNum;i++){
            if (verArray[i].verName.equals(verName))
                return verArray[i];
        }
        return null;
    }
}
