package cn.xiewei.tree.huffmanfile;


/** 
 * ����������� 
 */  
public class HuffNode implements Comparable<HuffNode>{  
    public int value;//�������  
    public int weight;//Ȩ��  
    HuffNode left,right;//���Һ��ӽ��  
    HuffNode parent;//���׽��  
    /** 
     * ��ʼ���������ݣ�Ȩ�أ����Һ��ӽ���븸�׽�� 
     * @param v ���� 
     * @param w Ȩ�� 
     * @param lchild ���ӽ�� 
     * @param rchild �Һ��ӽ�� 
     * @param pt    ���׽�� 
     */  
    HuffNode(int v,int w,HuffNode lchild,HuffNode rchild,HuffNode pt){  
        value=v;  
        weight=w;  
        left=lchild;  
        right=rchild;  
        parent=pt;  
    }  
    /** 
     * �Ƚ���������Ȩ�� 
     */  
    public int compareTo(HuffNode rhs) {  
        return weight-rhs.weight;  
    }  
}  
