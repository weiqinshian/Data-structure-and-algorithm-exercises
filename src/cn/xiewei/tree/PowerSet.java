package cn.xiewei.tree;
import java.util.ArrayList;
import java.util.List;
/**
 * 求集合的所有子集
 * 
 * @author XW
 * @create_date 2019年8月30日
 */
public class PowerSet {

    public static void powerSet(int i,List<Integer> A,List<Integer> B){
        if(i>=A.size())
            System.out.print(B.toString()+",");
        else{
            int x = A.get(i);
            B.add(x);
            powerSet(i+1,A,B);
            B.remove(new Integer(x));
            powerSet(i+1,A,B);
        }
    }
    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<Integer>();
        A.add(1);
        A.add(2);
        A.add(3);
        ArrayList<Integer> B = new ArrayList<Integer>();
        powerSet(0,A,B);
    }
}
