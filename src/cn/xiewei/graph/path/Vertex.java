package cn.xiewei.graph.path;
/**
 * 顶点类
 * 
 * @author XW
 * @create_date 2019年10月24日
 */
public class Vertex {
    public String label;
    public boolean isInTree;
    public Vertex(String lab)
    {
        label =lab;
        isInTree=false;
    }

}
