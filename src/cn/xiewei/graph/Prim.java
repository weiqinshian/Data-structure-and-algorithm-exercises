package cn.xiewei.graph;

import java.util.ArrayList;
import java.util.List;

public class Prim {
    static int MAX = Integer.MAX_VALUE;
 
    public static void main(String[] args) {
        int[][] map = new int[][] {
                { 0, 10, MAX, MAX, MAX, 11, MAX, MAX, MAX },
                { 10, 0, 18, MAX, MAX, MAX, 16, MAX, 12 },
                { MAX, MAX, 0, 22, MAX, MAX, MAX, MAX, 8 },
                { MAX, MAX, 22, 0, 20, MAX, MAX, 16, 21 },
                { MAX, MAX, MAX, 20, 0, 26, MAX, 7, MAX },
                { 11, MAX, MAX, MAX, 26, 0, 17, MAX, MAX },
                { MAX, 16, MAX, MAX, MAX, 17, 0, 19, MAX },
                { MAX, MAX, MAX, 16, 7, MAX, 19, 0, MAX },
                { MAX, 12, 8, 21, MAX, MAX, MAX, MAX, 0 } };
        prim(map, map.length);
    }
     public static void prim(int[][] graph, int n){
            
            char[] c = new char[]{'A','B','C','D','E','F','G','E','F'};        
            int[] lowcost = new int[n];  //���¼��ϵ���СȨ 
            int[] mid= new int[n];//��ȡǰ�����
            List<Character> list=new ArrayList<Character>();//�����洢�������˳��
            int i, j, min, minid , sum = 0;
            //��ʼ����������
            for(i=1;i<n;i++)
            {
                lowcost[i]=graph[0][i];
                mid[i]=0;
            }
            list.add(c[0]);
            //һ����Ҫ����n-1����
            for(i=1;i<n;i++)
            {
                 min=MAX;
                 minid=0;
                 //ÿ���ҵ����뼯������ĵ�
                 for(j=1;j<n;j++)
                 {
                     if(lowcost[j]!=0&&lowcost[j]<min)
                     {
                         min=lowcost[j];
                         minid=j;
                     }
                 }
                 
                 if(minid==0) return;
                 list.add(c[minid]);
                 lowcost[minid]=0;
                 sum+=min;
                 System.out.println(c[mid[minid]] + "��" + c[minid] + " Ȩֵ��" + min);
                 //����õ�󣬸��������㵽���ϵľ���
                 for(j=1;j<n;j++)
                 {
                     if(lowcost[j]!=0&&lowcost[j]>graph[minid][j])
                     {
                         lowcost[j]=graph[minid][j];
                         mid[j]=minid;
                     }
                 }
            }
            System.out.println("sum:" + sum);
            
        }
        
}
