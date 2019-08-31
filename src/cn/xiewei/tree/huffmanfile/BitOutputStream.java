package cn.xiewei.tree.huffmanfile;



/** 
 * OutputStream�İ�װ�࣬�ṩ��λ����ķ��� 
 */  
import java.io.IOException;
import java.io.OutputStream;  
  
public class BitOutputStream {  
    private OutputStream out; //���������  
    private int buffer;//����Ļ�����  
    private int bufferPos;//��������ʣ���λ��  
    /** 
     * ��װOutputStream�Ĺ��췽������ʼ����������С 
     * @param os 
     */  
    public BitOutputStream(OutputStream os){  
        bufferPos=0;  
        buffer=0;  
        out=os;  
    }  
    /** 
     * д��һ����λ 
     * @param val ������λ���ݵ����� 
     * @throws IOException 
     */  
    public void writeBits(int []val) throws IOException{  
        int len=val.length;  
        for(int i=0;i<len;i++){  
            writeBit(val[i]);  
        }  
    }  
    /** 
     * д��λ�ķ���(0��1)��ÿ8�ζ�����е��þʹӻ�������д��һ��byte 
     * @param val ��ǰд���λ���� 
     * @throws IOException 
     */  
    public void writeBit(int val) throws IOException{  
        buffer=setBit(buffer,bufferPos++,val);//����������ת����λ����  
        //ÿ����һ��byte��ˢ��һ��  
        if(bufferPos==BitUtils.BITS_PER_BYTES)//������������ˢ�»�����  
            flush();  
    }  
    /** 
     * ˢ�´˻��������� 
     * @throws IOException 
     */  
    public void flush() throws IOException{  
        if(bufferPos==0)//���������û��������ִ��  
            return;  
        //���������е�����д��  
        out.write(buffer);  
        //���û�����  
        bufferPos=0;  
        buffer=0;  
    }  
    /** 
     * �ر����ķ��� 
     * @throws IOException 
     */  
    public void close() throws IOException{  
        flush();  
        out.close();  
    }  
    /** 
     * ����λ����ת���ķ��� 
     * @param pack 
     * @param pos 
     * @param val ��ǰλ 
     * @return 
     */  
    private int setBit(int pack,int pos,int val){  
        if(val==1)  
            //��λ������  
            pack|=(val<<pos);  
        return pack;  
    }  
}  
