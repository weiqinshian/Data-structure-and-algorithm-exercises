package cn.xiewei.graph;

/**
 * �ڽӾ���,�洢����ͼ
 * 
 * ����ͼ���ڽӾ����ǶԳƵ�
 * 
 * @author XW
 * @create_date 2019��9��5��
 */
public class MatrixNDG {

    int size;//ͼ�������
    char[] vertexs;//ͼ��������
    static int[][] matrix;//ͼ��ϵ����

    public MatrixNDG(char[] vertexs,char[][] edges){
        size=vertexs.length;
        matrix=new int[size][size];//�趨ͼ��ϵ�����С
        this.vertexs=vertexs;

        for(char[] c:edges){//���þ���ֵ
            int p1 = getPosition(c[0]);//���ݶ�������ȷ����Ӧ�����±�
            int p2 = getPosition(c[1]);

            matrix[p1][p2] = 1;//����ͼ���������Գ�λ�ô洢
            matrix[p2][p1] = 1;
        }

    }

    //ͼ�ı������
    public static void printResult(char [] vexs,int[][] edgs) {
        System.out.println("��������飺");
        System.out.print("[");
        for(int i=0;i<vexs.length;i++) {
            if(i==vexs.length-1) {
                System.out.print(vexs[i]);
                break;
            }
            else
                System.out.print(vexs[i]+",");
        }
        System.out.println
        ("]");
        
        System.out.println("��ӡ���󣬾����д洢�ߵ�Ȩֵ�ͷ�������ͼ����Գƣ�");
        System.out.print("  ");
        for(int i=0;i<vexs.length;i++) {
            if(i==vexs.length-1) {
                System.out.print(vexs[i]);
                break;
            }
            else
                System.out.print(vexs[i]+",");
        }
        System.out.println();
        for(int i=0;i<vexs.length;i++) {
            System.out.print(vexs[i]+"|");
            for(int j=0;j<vexs.length;j++) {
                if(j==vexs.length-1) {
                    System.out.print(edgs[i][j]);
                    break;
                }
                else
                    System.out.print(edgs[i][j]+",");
            }
            System.out.println();
        }
    }

    //���ݶ������ƻ�ȡ��Ӧ�ľ����±�
    private int getPosition(char ch) {
        for(int i=0; i<vertexs.length; i++)
            if(vertexs[i]==ch)
                return i;
        return -1;
    }

    public static void main(String[] args) {
        char[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G','H','I','J','K'};
        char[][] edges = new char[][]{
            {'A', 'C'}, 
            {'A', 'D'}, 
            {'A', 'F'}, 
            {'B', 'C'}, 
            {'C', 'D'}, 
            {'E', 'G'}, 
            {'D', 'G'},
            {'I','J'},
            {'J','G'},};
        MatrixNDG pG = null;
        // �Զ���"ͼ"(����������)
        // �������е�"ͼ"
        long start=System.nanoTime();

        for(int i=0;i<10000;i++){
            pG = new MatrixNDG(vexs, edges);
        }
        printResult(vexs,matrix);   // ��ӡͼ 
    }
}