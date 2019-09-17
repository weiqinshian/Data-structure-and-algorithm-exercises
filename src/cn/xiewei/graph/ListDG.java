package cn.xiewei.graph;

import java.util.LinkedList;

/**
 * ʹ���ڽӱ�洢����ͼ
 * 
 * @author XW
 * @create_date 2019��9��5��
 */
public class ListDG {
    LinkedList<Character>[] vertexLists;
    int size;

    /**
     * 
     * @param vertexs ͼ�ڵ�
     * @param edges  ͼ�ıߣ���ϵ
     */
    @SuppressWarnings("unchecked")
    public ListDG(char[] vertexs,char[][] edges){
        size=vertexs.length;
        this.vertexLists=new LinkedList[size];

        /*1.��ͼ�����нڵ㶼���õ����б���*/
        for(int i=0;i<size;i++){
            this.vertexLists[i]=new LinkedList<Character>();
            vertexLists[i].add(vertexs[i]);
        }

        /*2.����ͼ����ϵ���飬Ϊ�ڵ����ù�����ϵ*/
        for(char[] c:edges){
            int p=getPosition(c[0]);//��ȡ�ڵ�λ��
            this.vertexLists[p].add(c[1]);//�ڽڵ�֮������һ���ڵ㡣
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
        //LinkedList<Character>�������飬�����ÿ��Ԫ�ض���һ��LinkedList����
        LinkedList<Character>[] linkedList= pG.vertexLists;

    }

}
