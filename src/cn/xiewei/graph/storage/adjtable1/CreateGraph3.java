package cn.xiewei.graph.storage.adjtable1;


import java.util.Scanner;

public class CreateGraph3 {

    /**
     * 根据用户输入的string类型的顶点返回该顶点
     * @param graph 图
     * @param str 输入数据
     * @return返回一个顶点
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
     * 根据用户输入的数据初始化一个图，以邻接表的形式构建!
     * @param graph 生成的图
     */
    public void initialGraph(Graph1 graph){
        @SuppressWarnings("resource")
        Scanner scan=new Scanner(System.in);
        System.out.println("请输入顶点数和边数：");
        graph.verNum=scan.nextInt();
        graph.edgeNum=scan.nextInt();

        System.out.println("请依次输入定点名称：");
        for(int i=0;i<graph.verNum;i++){
            Vertex1 vertex=new Vertex1();
            String name=scan.next();
            vertex.verName=name;
            vertex.nextNode=null;
            graph.vertexArray[i]=vertex;
        }

        System.out.println("请依次输入图的便边：");
        for(int i=0;i<graph.edgeNum;i++){
            String preV=scan.next();
            String folV=scan.next();

            Vertex1 v1=getVertex(graph,preV);
            if(v1==null)
                System.out.println("输入边存在图中没有的顶点！");

//下面代码是图构建的核心：链表操作
            Vertex1 v2=new Vertex1();
            v2.verName=folV;
            v2.nextNode=v1.nextNode;
            v1.nextNode=v2;

//紧接着下面注释的代码加上便是构建无向图的，不加则是构建有向图的！
//          Vertex1 reV2=getVertex(graph,folV);
//          if(reV2==null)
//              System.out.println("输入边存在图中没有的顶点！");
//          Vertex1 reV1=new Vertex1();
//          reV1.verName=preV;
//          reV1.nextNode=reV2.nextNode;
//          reV2.nextNode=reV1;
        }
    }

    /**
     * 输入图的邻接表
     * @param graph 待输出的图
     */
    public void outputGraph(Graph1 graph){
        System.out.println("输出图的邻接链表为：");
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