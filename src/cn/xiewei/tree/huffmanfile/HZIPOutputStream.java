package cn.xiewei.tree.huffmanfile;



/** 
 * 包含压缩的包装类 
 */  
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;  
  
public class HZIPOutputStream extends OutputStream{  
    private ByteArrayOutputStream byteOut=new ByteArrayOutputStream();//实例化的一个字节数组输出流对象  
    private DataOutputStream dout;//数据输出流对象  
    /** 
     * 实例化一个DataOutputStream对象的构造方法 
     * @param out 输出流对象 
     * @throws IOException 
     */  
    public HZIPOutputStream(OutputStream out) throws IOException{  
        dout=new DataOutputStream(out);  
    }  
    /** 
     * 写入编码频率的方法 
     */  
    public void write(int ch) throws IOException{  
        byteOut.write(ch);  
    }  
    /** 
     * 关闭流的方法 
     */  
    public void close() throws IOException{  
        byte[] theInput=byteOut.toByteArray();//将字节数组输出流转换数据转换成字节数组进行输入  
        ByteArrayInputStream byteIn=new ByteArrayInputStream(theInput);//将字节数组封装到字节输入流中  
          
        CharCounter countObj=new CharCounter(byteIn);//实例化字符统计对象并统计字节数组的字符出现的次数  
        byteIn.close();//关闭字节输入流  
          
        HuffmanTree codeTree=new HuffmanTree(countObj);//通过CharCounter对象实例化一个HuffmanTree对象  
        codeTree.writeEncodingTable(dout);//将编码写入数据输出流中  
          
        BitOutputStream bout=new BitOutputStream(dout);//创建位输出流  
          
        //将按编码转换后的位写入  
        int len=theInput.length;  
        for(int i=0;i<len;i++)  
            bout.writeBits(codeTree.getCode(theInput[i]&0xff));  
        bout.writeBits(codeTree.getCode(BitUtils.EOF));//文件结束的标示符  
          
        //关闭流  
        bout.close();  
        byteOut.close();  
    }  
}  
