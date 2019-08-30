package cn.xiewei.tree;
/**
 * n皇后问题解决
 * @author XW
 * @create_date 2019年8月27日
 */
public class QueenTest {
    /**下标i表示第几行，position[i]表示第i行皇后的位置,注意此处0行不用*/
    public int[] position;
    /**皇后的数目*/
    public int queenNum;
    /**解的数目*/
    public int methodNum;
     QueenTest(int queenNum) {
        this.queenNum = queenNum;
        this.position = new int[queenNum+1];//注意，这里我们从第1行开始算起，第0行不用
        backtrack(1);//从第一个皇后开始递归
    }
    
    /**
     * 一行一行的确定该行的皇后位置
     * @param line
     */
    public void backtrack(int line)
    {
        if( line > queenNum) //如果当前行大于皇后数目，表示找到解了
        {
            methodNum++;//sum为所有的可行的解
            //依次打印本次解皇后的位置
            for(int m = 1; m <= queenNum; m++){
               //System.out.println(x[m]);//这一行用输出当递归到叶节点的时候，一个可行解
               //这里只是为了好看才写成下面的
               for(int k =1; k <= queenNum;k++){
                   if(k == position[m]){
                     System.out.print(position[m]+" "); 
                   }else {
                     System.out.print("* ");//用*表示没有被用到的位置 
                }
               }
                System.out.println();
            }
            System.out.println();
        }
        else{
            for(int i = 1;i <= queenNum;i++)
            {
                position[line] = i;//第t行上皇后的位置只能是1-queenNum             
                if(place(line)) {//此处的place函数用来进行我们上面所说的条件的判断，如果成立，进入下一级递归,即放置下一个皇后
                    backtrack(line+1);
                }
            }
        }
    }
    
    /**
     * 判断第line行皇后可以放置的位置
     * @param line line表示第line行，position[line]line表示第line行上皇后的位置
     * @return boolean false表示此处不能放置皇后
     */
    public boolean place(int line) {
        for (int j = 1; j < line; j++)
            // 如果当前传入的第K行上的皇后放置的位置和其它皇后一个对角线(abs(x[k]- x[j])==abs(k-j)或一个直线上(x[j] == x[k])
            if (Math.abs(position[line] - position[j]) == Math.abs(line - j) || (position[j] == position[line])){                                                                
                return false;
            }
        return true;
    }
    public static void main(String[] args) {
        QueenTest queenTest = new QueenTest(8);
        System.out.println("总共解数为："+ queenTest.methodNum);
    }
}
