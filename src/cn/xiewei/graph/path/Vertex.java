package cn.xiewei.graph.path;
/**
 * ������
 * 
 * @author XW
 * @create_date 2019��10��24��
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
