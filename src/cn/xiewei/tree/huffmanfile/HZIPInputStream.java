package cn.xiewei.tree.huffmanfile;


/** 
 * ������ѹ���İ�װ�� 
 */  
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;  
  
public class HZIPInputStream extends InputStream{  
    private BitInputStream bin;//λ������  
    private HuffmanTree codeTree;//�����HuffmanTree����  
    /** 
     * ��װInputStream����,ʵ����HuffmanTree������BitInputStream����,��������������� 
     * @param in 
     * @throws IOException 
     */  
    public HZIPInputStream(InputStream in) throws IOException{  
        //����������  
        DataInputStream din=new DataInputStream(in);  
          
        codeTree=new HuffmanTree();  
        codeTree.readEncodingTable(din);  
          
        bin=new BitInputStream(in);  
    }  
    /** 
     * ��ȡ�ļ��ķ��� 
     */  
    public int read() throws IOException{  
        String bits="";//������������ַ���  
        int bit;//λ  
        int decode;//�������ַ�  
        while(true){  
            bit=bin.readBit();  
            if(bit == -1)  
                throw new IOException("Unexpected EOF");//�������Դ����  
              
            bits+=bit;  
            decode=codeTree.getChar(bits);//��ȡ�����Ӧ���ַ�  
            if(decode==HuffmanTree.INCOMPLETE_CODE)//����������Ҷ�ӽ��  
                continue;  
            else if(decode==HuffmanTree.ERROR)//�������  
                throw new IOException("Unexpected error");  
            else if(decode==HuffmanTree.END)//�������  
                return -1;  
            else   
                return decode;  
        }  
    }  
    /** 
     * �ر������� 
     */  
    public void close() throws IOException{  
        bin.close();  
    }  
}
