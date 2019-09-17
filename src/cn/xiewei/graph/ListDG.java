package cn.xiewei.graph;

import java.util.LinkedList;

/**
 * 使用邻接表存储有向图
 * 
 * @author XW
 * @create_date 2019年9月5日
 */
public class ListDG {
    LinkedList<Character>[] vertexLists;
    int size;

    /**
     * 
     * @param vertexs 图节点
     * @param edges  图的边，关系
     */
    @SuppressWarnings("unchecked")
    public ListDG(char[] vertexs,char[][] edges){
        size=vertexs.length;
        this.vertexLists=new LinkedList[size];

        /*1.将图的所有节点都设置到，列表中*/
        for(int i=0;i<size;i++){
            this.vertexLists[i]=new LinkedList<Character>();
            vertexLists[i].add(vertexs[i]);
        }

        /*2.遍历图，关系数组，为节点设置关联关系*/
        for(char[] c:edges){
            int p=getPosition(c[0]);//获取节点位置
            this.vertexLists[p].add(c[1]);//在节点之后，新增一个节点。
         }

    }

    private int getPosition(char ch) {
        for(int i=0; i<size; i++)
            if(vertexLists[i].get(0)==ch)
                return i;
        return -1;
    }

    public void print(){
       for(int i=0;i<size;i++){
           LinkedList<Character> temp=vertexLists[i];
           for(int j=0;j<temp.size();j++){
               System.out.print(temp.get(j)+" ");
           }
           System.out.println();
       }
    }

    public static void main(String[] args){
        char[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G','H','I','J','K'};
        char[][] edges = new char[][]{
            {'A', 'C'}, 
            {'A', 'D'}, 
            {'A', 'F'}, 
            {'B', 'C'}, 
            {'C', 'D'}, 
            {'E', 'G'}, 
            {'D', 'G'},
            {'I','J'},
            {'J','G'},};

        ListDG pG = new ListDG(vexs, edges);   
        //LinkedList<Character>类似数组，数组的每个元素都是一个LinkedList类型
        LinkedList<Character>[] linkedList= pG.vertexLists;

    }

}
