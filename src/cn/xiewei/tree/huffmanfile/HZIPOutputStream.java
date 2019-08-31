package cn.xiewei.tree.huffmanfile;



/** 
 * ����ѹ���İ�װ�� 
 */  
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;  
  
public class HZIPOutputStream extends OutputStream{  
    private ByteArrayOutputStream byteOut=new ByteArrayOutputStream();//ʵ������һ���ֽ��������������  
    private DataOutputStream dout;//�������������  
    /** 
     * ʵ����һ��DataOutputStream����Ĺ��췽�� 
     * @param out ��������� 
     * @throws IOException 
     */  
    public HZIPOutputStream(OutputStream out) throws IOException{  
        dout=new DataOutputStream(out);  
    }  
    /** 
     * д�����Ƶ�ʵķ��� 
     */  
    public void write(int ch) throws IOException{  
        byteOut.write(ch);  
    }  
    /** 
     * �ر����ķ��� 
     */  
    public void close() throws IOException{  
        byte[] theInput=byteOut.toByteArray();//���ֽ����������ת������ת�����ֽ������������  
        ByteArrayInputStream byteIn=new ByteArrayInputStream(theInput);//���ֽ������װ���ֽ���������  
          
        CharCounter countObj=new CharCounter(byteIn);//ʵ�����ַ�ͳ�ƶ���ͳ���ֽ�������ַ����ֵĴ���  
        byteIn.close();//�ر��ֽ�������  
          
        HuffmanTree codeTree=new HuffmanTree(countObj);//ͨ��CharCounter����ʵ����һ��HuffmanTree����  
        codeTree.writeEncodingTable(dout);//������д�������������  
          
        BitOutputStream bout=new BitOutputStream(dout);//����λ�����  
          
        //��������ת�����λд��  
        int len=theInput.length;  
        for(int i=0;i<len;i++)  
            bout.writeBits(codeTree.getCode(theInput[i]&0xff));  
        bout.writeBits(codeTree.getCode(BitUtils.EOF));//�ļ������ı�ʾ��  
          
        //�ر���  
        bout.close();  
        byteOut.close();  
    }  
}  
