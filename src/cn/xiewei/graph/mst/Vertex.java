package cn.xiewei.graph.mst;
/**
 * ������
 * 
 * @author XW
 * @create_date 2019��10��24��
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
