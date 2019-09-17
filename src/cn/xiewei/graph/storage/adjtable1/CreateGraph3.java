package cn.xiewei.graph.storage.adjtable1;


import java.util.Scanner;

public class CreateGraph3 {

    /**
     * �����û������string���͵Ķ��㷵�ظö���
     * @param graph ͼ
     * @param str ��������
     * @return����һ������
     */
    public Vertex1 getVertex(Graph1 graph,String str){
        for(int i=0;i<graph.verNum;i++){
            if(graph.vertexArray[i].verName.equals(str)){
                return graph.vertexArray[i];
            }
        }
        return null;
    }

    /**
     * �����û���������ݳ�ʼ��һ��ͼ�����ڽӱ����ʽ����!
     * @param graph ���ɵ�ͼ
     */
    public void initialGraph(Graph1 graph){
        @SuppressWarnings("resource")
        Scanner scan=new Scanner(System.in);
        System.out.println("�����붥�����ͱ�����");
        graph.verNum=scan.nextInt();
        graph.edgeNum=scan.nextInt();

        System.out.println("���������붨�����ƣ�");
        for(int i=0;i<graph.verNum;i++){
            Vertex1 vertex=new Vertex1();
            String name=scan.next();
            vertex.verName=name;
            vertex.nextNode=null;
            graph.vertexArray[i]=vertex;
        }

        System.out.println("����������ͼ�ı�ߣ�");
        for(int i=0;i<graph.edgeNum;i++){
            String preV=scan.next();
            String folV=scan.next();

            Vertex1 v1=getVertex(graph,preV);
            if(v1==null)
                System.out.println("����ߴ���ͼ��û�еĶ��㣡");

//���������ͼ�����ĺ��ģ��������
            Vertex1 v2=new Vertex1();
            v2.verName=folV;
            v2.nextNode=v1.nextNode;
            v1.nextNode=v2;

//����������ע�͵Ĵ�����ϱ��ǹ�������ͼ�ģ��������ǹ�������ͼ�ģ�
//          Vertex1 reV2=getVertex(graph,folV);
//          if(reV2==null)
//              System.out.println("����ߴ���ͼ��û�еĶ��㣡");
//          Vertex1 reV1=new Vertex1();
//          reV1.verName=preV;
//          reV1.nextNode=reV2.nextNode;
//          reV2.nextNode=reV1;
        }
    }

    /**
     * ����ͼ���ڽӱ�
     * @param graph �������ͼ
     */
    public void outputGraph(Graph1 graph){
        System.out.println("���ͼ���ڽ�����Ϊ��");
        for(int i=0;i<graph.verNum;i++){
            Vertex1 vertex=graph.vertexArray[i];
            System.out.print(vertex.verName);

            Vertex1 current=vertex.nextNode;
            while(current!=null){
                System.out.print("-->"+current.verName);
                current=current.nextNode;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Graph1 graph=new Graph1();
        CreateGraph3 createGraph=new CreateGraph3();
        createGraph.initialGraph(graph);
        createGraph.outputGraph(graph);
    }
}