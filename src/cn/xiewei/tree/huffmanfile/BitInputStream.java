package cn.xiewei.tree.huffmanfile;


/** 
 * InputStream�İ�װ��,�ṩ��λ���� 
 */  
import java.io.IOException;
import java.io.InputStream;  
  
public class BitInputStream {  
    private InputStream in;//����������  
    private int buffer;//byte������  
    private int bufferPos;//��ʾ���������ж���δ��ʹ�õĿռ�  
    /** 
     * ��װInputStream�Ĺ��췽������ʼ��byte�������Ĵ�С 
     * @param is InputStream���� 
     */  
    public BitInputStream(InputStream is){  
        in=is;  
        bufferPos=BitUtils.BITS_PER_BYTES;//��ʼ����������ʣ��ռ�  
    }  
    /** 
     * ��ȡһλ�ķ�����ÿ8�ζ�����е��þͻ�ӻ����������ж���һ��byte 
     * @return 1λ���ݣ�1����0 
     * @throws IOException  
     */  
    public int readBit() throws IOException{  
        //�����������δ��ʹ��  
        if(bufferPos==BitUtils.BITS_PER_BYTES){  
            //��������ȡһλ  
            buffer=in.read();  
            //�����ļ���ĩβ��  
            if(buffer==-1)  
                return -1;  
            //��ջ�����  
            bufferPos=0;  
        }  
        //���Ż�����  
        return getBit(buffer,bufferPos++);  
    }  
    /** 
     * �ر������� 
     * @throws IOException  
     */  
    public void close() throws IOException{  
        in.close();  
    }  
    /** 
     * ��ȡһ��byte��ÿһλ�ķ��� 
     * @param pack  
     * @param pos  
     * @return  
     */  
    private static int getBit(int pack,int pos){  
        //��һ���ֽڽ���8�ΰ�λ�����㣬�õ�����ֽڵ�8λ  
        return (pack&(1<<pos))!=0?1:0;  
    }  
}  
