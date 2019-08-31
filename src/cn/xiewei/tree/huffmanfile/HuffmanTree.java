package cn.xiewei.tree.huffmanfile;



import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.PriorityQueue;  
  
public class HuffmanTree {  
    private CharCounter theCounts;//�ַ�ͳ�������  
    private HuffNode root;//�����  
    private HuffNode[] theNodes=new HuffNode[BitUtils.DIFF_BYTES+1];//�洢HuffNode������  
    public static final int ERROR=-3;//����  
    public static final int INCOMPLETE_CODE=-2;//����ȫ�Ľ�����  
    public static final int END=BitUtils.DIFF_BYTES;//�ֽڵ����λ  
    /** 
     * ʵ����һ���ַ�ͳ������� 
     */  
    public HuffmanTree(){  
        theCounts=new CharCounter();  
        root=null;  
    }  
    /** 
     * ����ͨ��CharCounter����������һ��huffmanTree���� 
     * @param cc CharCounter���� 
     */  
    public HuffmanTree(CharCounter cc){  
        theCounts=cc;  
        root=null;  
        createTree();//����HuffmanTree  
    }  
    /** 
     * �õ�ҪѰ�ҵ��ַ��������ڵ������,������ַ��������ϣ��򷵻�null��ʾ����,����,ͨ���������������,ֱ���������� 
     * @param ch ��ǰ�����±� 
     * @return �����Ե��ַ��������� 
     */  
    public int[] getCode(int ch){  
        HuffNode current=theNodes[ch];  
          
        if(current==null)  
            return null;  
        String v="";//���ı���  
        HuffNode parent=current.parent;  
          
        while(parent!=null){  
            if(parent.left==current)  
                v="0"+v;//�������  
            else   
                v="1"+v;//�ҽ�����  
            //���±���  
            current=current.parent;  
            parent=current.parent;  
        }  
        int len=v.length();  
        int [] result=new int[len];//����һ���������ͬ��С����  
        for(int i=0;i<len;i++)  
            result[i]=v.charAt(i)=='0'?0:1;  
        return result;  
    }  
    /** 
     * ��ȡ�����Ӧ���ַ� 
     * @param code ���������� 
     * @return �洢�ڽ���е�ֵ(�����㲻��Ҷ�ӽ��,�򷵻ط���INCOMPLETE) 
     */  
    public int getChar(String code){  
        HuffNode leaf=root;//��ȡ�����  
        int len=code.length();  
        //���ձ�����������ұ�����Ҷ�ӽ��  
        for(int i=0;leaf!=null&&i<len;i++)  
            if(code.charAt(i)=='0')  
                leaf=leaf.left;  
            else  
                leaf=leaf.right;  
        //�����Ϊ��  
        if(leaf==null)  
            return ERROR;  
        return leaf.value;  
    }  
    /** 
     * д������ķ��� 
     * @param out д��������� 
     * @throws IOException 
     */  
    public void writeEncodingTable(DataOutputStream out) throws IOException{  
        for(int i=0;i<BitUtils.DIFF_BYTES;i++){  
            if(theCounts.getCount(i)>0){  
                out.writeByte(i);//���ֽ�д�루ͨ�����ļ���  
                out.writeInt(theCounts.getCount(i));//���ֽڳ��ֵĴ���д�루ͨ�����ļ���  
            }  
        }  
        //���д��0��ʾ����Ľ���  
        out.writeByte(0);  
        out.writeInt(0);  
    }  
    /** 
     * ��ȡ�����ķ��� 
     * @param in �������������� 
     * @throws IOException 
     */  
    public void readEncodingTable(DataInputStream in) throws IOException{  
        for(int i=0;i<BitUtils.DIFF_BYTES;i++)  
            theCounts.setCount(i, 0);  
        byte ch;  
        int num;  
        for(;;){  
            ch=in.readByte();//�������ֽ�  
            num=in.readInt();//�ַ����ֵĴ���  
            if(num==0)//�������0��ʾ�����Ľ���  
                break;  
            theCounts.setCount(ch, num);  
        }  
        createTree();//����HuffmanTree  
    }  
    /** 
     * ����������������ķ��� 
     */  
    public void createTree(){  
        //����һ�����ȶ���������HuffNode  
        PriorityQueue<HuffNode> pq=new PriorityQueue<HuffNode>();  
          
        for(int i=0;i<BitUtils.DIFF_BYTES;i++){  
            if(theCounts.getCount(i)>0){//���ĳһ���ֽڳ��ֹ�  
                HuffNode newNode=new HuffNode(i,theCounts.getCount(i),null,null,null);  
                theNodes[i]=newNode;  
                pq.add(newNode);//���½����ӵ�������  
            }  
        }  
          
        theNodes[END] =new HuffNode(END,1,null,null,null);  
        pq.add(theNodes[END]);  
        //��ʣ��Ľ�����һ��ʱ  
        while(pq.size()>1){  
            //ÿ��ȡ����ǰ��С���������  
            HuffNode n1=pq.remove();//remove������poll������Ψһ��֮ͬ������:�˶���Ϊ��ʱ���׳�һ���쳣  
            HuffNode n2=pq.remove();  
            //����С��������������γ��½��  
            HuffNode result=new HuffNode(INCOMPLETE_CODE,n1.weight+n2.weight,n1,n2,null);  
            n1.parent=n2.parent=result;  
            //���½����ӵ����е���  
            pq.add(result);  
        }  
        root=pq.element();//�������Ƕ����е����һ�����  
    }  
}  
