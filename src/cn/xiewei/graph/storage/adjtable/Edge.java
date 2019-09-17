package cn.xiewei.graph.storage.adjtable;



/**
 * 图的边类
 */
public class Edge {
    /**
     * 边的尾部节点名称
     */
    String tailName;
    /**
     * 边的权值
     */
    int weight;
    /**
     * 头节点的其他边
     */
    Edge broEdge;
}
