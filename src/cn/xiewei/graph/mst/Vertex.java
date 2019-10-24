package cn.xiewei.graph.mst;
/**
 * 顶点类
 * 
 * @author XW
 * @create_date 2019年10月24日
 */
public class Vertex {
    public char label;
    public boolean wasVisited;
    public Vertex(char lab)
    {
        label =lab;
        wasVisited=false;
    }

}
