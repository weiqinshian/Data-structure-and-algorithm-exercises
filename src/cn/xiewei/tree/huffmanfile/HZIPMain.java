package cn.xiewei.tree.huffmanfile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class HZIPMain {
    /** 
     * ѹ���ļ��ķ������˷�����Ҫ������ȷ�ľ���·���� 
     * @param inFile ��Ҫ��ѹ�����ļ� 
     * @param outFile ѹ��֮�������ļ� 
     * @throws IOException IO�쳣 
     */  
    public static void compress(String inFile,String outFile) throws IOException{  
        String compressFile=null;//����ѹ���ļ�  
        String extension=inFile.substring(inFile.length()-4);//��ȡԴ�ļ��ĺ�׺��  
        File file=new File(outFile);  
        //����ļ��Ѿ�����  
        if(file.exists()){  
            System.out.println("�ļ��Ѿ�����");  
        }else{
            //�Զ������׺��  
            if(!outFile.endsWith(".hfm")){  
                compressFile=outFile+extension+".hfm";    
            }  
            else{  
                compressFile=outFile+extension;  
            }  
            //�����ļ�����Ļ�����  
            InputStream in=new BufferedInputStream(new FileInputStream(inFile));  
            //�����ļ�����Ļ�����  
            OutputStream out=new BufferedOutputStream(new FileOutputStream(compressFile));  
            int ch;  
            //����������ѹ����������  
            HZIPOutputStream hzout=new HZIPOutputStream(out);  
            while((ch=in.read())!=-1){  
                hzout.write(ch);  
            }  
            //�ر���  
            in.close();  
            hzout.close();  
        }  
    }  
    /** 
     * ��ѹ�ļ��ķ������˷�����Ҫ������ȷ�ľ���·���� 
     * @param compressedFile  ��Ҫ����ѹ���ļ� 
     * @param outFile ��ѹ֮�������ļ� 
     * @throws IOException IO�쳣 
     */  
    public static void uncompress(String compressedFile,String outFile) throws IOException{  
        String extension;//�ļ��ĺ�׺��  
        extension =compressedFile.substring(compressedFile.length()-4);  
        //�õ�ѹ��ǰ���ļ��ĺ�׺��  
        String suffix=compressedFile.substring(compressedFile.length()-8,compressedFile.length()-4);  
        //����ļ����Ϸ���ִ�н�ѹ����  
        if(!extension.equals(".hfm")){  
            System.out.println("�ļ���ʽ������߲�����Ч��ѹ���ļ�");  
            return;  
        }  
        File file=new File(outFile);  
        //����Ѿ�����ͬ���ļ�  
        if(file.exists()){  
            System.out.println("���ļ��Ѿ����ڣ�������������ѹ����ļ�");  
        }  
        else{  
            outFile+=(suffix+".uc");//����ļ��ĸ�ʽͳһΪuc��ʽ  
            //�����ļ�����Ļ�����  
            InputStream fin=new BufferedInputStream(new FileInputStream(compressedFile));  
            //�������ݶ�����  
            DataInputStream in=new DataInputStream(fin);  
            //����������ѹ��������  
            HZIPInputStream hzin=new HZIPInputStream(in);//�����ļ�����Ļ�����
            OutputStream fout=new BufferedOutputStream(new FileOutputStream(outFile));  
            int ch;  
            //��ѹ������ļ�  
            while((ch=hzin.read())!=-1){  
                fout.write(ch);  
            }  
            //�ر���  
            hzin.close();  
            fout.close();     
        }  
      
    }  
    public static void main(String args[]) throws IOException{  
        System.out.println("��ʼѹ��");   
        long start=System.currentTimeMillis();  
        compress("d://tmp/ori.txt","d://tmp/des"); 
        System.out.println("ѹ������,��ʱ:"+(System.currentTimeMillis()-start)); 
        System.out.println("��ʼ��ѹ");  
        start=System.currentTimeMillis();  
        uncompress("d://tmp/des.txt.hfm","d://tmp/ori1");  
        System.out.println("��ѹ����,��ʱ:"+(System.currentTimeMillis()-start));
    }  


}
