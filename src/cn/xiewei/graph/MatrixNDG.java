package cn.xiewei.graph;

/**
 * 邻接矩阵,存储无向图
 * 
 * 无向图的邻接矩阵是对称的
 * 
 * @author XW
 * @create_date 2019年9月5日
 */
public class MatrixNDG {

    int size;//图顶点个数
    char[] vertexs;//图顶点名称
    static int[][] matrix;//图关系矩阵

    public MatrixNDG(char[] vertexs,char[][] edges){
        size=vertexs.length;
        matrix=new int[size][size];//设定图关系矩阵大小
        this.vertexs=vertexs;

        for(char[] c:edges){//设置矩阵值
            int p1 = getPosition(c[0]);//根据顶点名称确定对应矩阵下标
            int p2 = getPosition(c[1]);

            matrix[p1][p2] = 1;//无向图，在两个对称位置存储
            matrix[p2][p1] = 1;
        }

    }

    //图的遍历输出
    public static void printResult(char [] vexs,int[][] edgs) {
        System.out.println("顶点的数组：");
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
        
        System.out.println("打印矩阵，矩阵中存储边的权值和方向，无向图矩阵对称：");
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

    //根据顶点名称获取对应的矩阵下标
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
        // 自定义"图"(输入矩阵队列)
        // 采用已有的"图"
        long start=System.nanoTime();

        for(int i=0;i<10000;i++){
            pG = new MatrixNDG(vexs, edges);
        }
        printResult(vexs,matrix);   // 打印图 
    }
}