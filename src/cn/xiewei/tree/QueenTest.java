package cn.xiewei.tree;
/**
 * n�ʺ�������
 * @author XW
 * @create_date 2019��8��27��
 */
public class QueenTest {
    /**�±�i��ʾ�ڼ��У�position[i]��ʾ��i�лʺ��λ��,ע��˴�0�в���*/
    public int[] position;
    /**�ʺ����Ŀ*/
    public int queenNum;
    /**�����Ŀ*/
    public int methodNum;
     QueenTest(int queenNum) {
        this.queenNum = queenNum;
        this.position = new int[queenNum+1];//ע�⣬�������Ǵӵ�1�п�ʼ���𣬵�0�в���
        backtrack(1);//�ӵ�һ���ʺ�ʼ�ݹ�
    }
    
    /**
     * һ��һ�е�ȷ�����еĻʺ�λ��
     * @param line
     */
    public void backtrack(int line)
    {
        if( line > queenNum) //�����ǰ�д��ڻʺ���Ŀ����ʾ�ҵ�����
        {
            methodNum++;//sumΪ���еĿ��еĽ�
            //���δ�ӡ���ν�ʺ��λ��
            for(int m = 1; m <= queenNum; m++){
               //System.out.println(x[m]);//��һ����������ݹ鵽Ҷ�ڵ��ʱ��һ�����н�
               //����ֻ��Ϊ�˺ÿ���д�������
               for(int k =1; k <= queenNum;k++){
                   if(k == position[m]){
                     System.out.print(position[m]+" "); 
                   }else {
                     System.out.print("* ");//��*��ʾû�б��õ���λ�� 
                }
               }
                System.out.println();
            }
            System.out.println();
        }
        else{
            for(int i = 1;i <= queenNum;i++)
            {
                position[line] = i;//��t���ϻʺ��λ��ֻ����1-queenNum             
                if(place(line)) {//�˴���place����������������������˵���������жϣ����������������һ���ݹ�,��������һ���ʺ�
                    backtrack(line+1);
                }
            }
        }
    }
    
    /**
     * �жϵ�line�лʺ���Է��õ�λ��
     * @param line line��ʾ��line�У�position[line]line��ʾ��line���ϻʺ��λ��
     * @return boolean false��ʾ�˴����ܷ��ûʺ�
     */
    public boolean place(int line) {
        for (int j = 1; j < line; j++)
            // �����ǰ����ĵ�K���ϵĻʺ���õ�λ�ú������ʺ�һ���Խ���(abs(x[k]- x[j])==abs(k-j)��һ��ֱ����(x[j] == x[k])
            if (Math.abs(position[line] - position[j]) == Math.abs(line - j) || (position[j] == position[line])){                                                                
                return false;
            }
        return true;
    }
    public static void main(String[] args) {
        QueenTest queenTest = new QueenTest(8);
        System.out.println("�ܹ�����Ϊ��"+ queenTest.methodNum);
    }
}
