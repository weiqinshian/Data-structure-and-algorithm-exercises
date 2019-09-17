package cn.xiewei.tree.huffmanfile;



/** 
 * �ַ�ͳ����,��ȡ��������ͨ�����ļ������������ַ��� 
 * 8λ�ֽ���Ϊ��ASCII�ַ� 
 */  
import java.io.IOException;
import java.io.InputStream;  
  
public class CharCounter {  
    //�ֽڵ��±��ʾ�ֽڵ����࣬��Ӧ��ֵ��ʾ���ֵĴ���  
    private int theCounts[]=new int[BitUtils.DIFF_BYTES];//�ֽڵ������ܹ���256��  
    /** 
     * Ĭ�ϵ��޲εĹ��췽�� 
     */  
    public CharCounter(){  
          
    }  
    /** 
     * ��װ�˻�����InputStream,��ȡ���ݲ������ַ���Ƶ��ͳ�� 
     * @param input InputStream���� 
     * @throws IOException 
     */  
    public CharCounter(InputStream input) throws IOException{
        int ch;//�������ֽ�  
        //һֱ�����ļ���ĩβ��ÿһ��byte�����˶��ٴ�  
        while((ch=input.read())!=-1){  
            theCounts[ch]++;  
        }  
    }  
    /** 
     * ��ȡ���ַ�ͳ�������ĳһ���ַ����ֵĴ��� 
     * @param ch �����±� 
     * @return ���±�λ���ַ����ֵĴ��� 
     */  
    public int getCount(int ch){  
        return theCounts[ch&0xff];  
    }  
    /** 
     * ����ĳһ���ַ����ֵĴ��� 
     * @param ch �����±� 
     * @param count �ַ����ִ��� 
     */  
    public void setCount(int ch,int count){  
        theCounts[ch&0xff]=count;  
    }  
}  
