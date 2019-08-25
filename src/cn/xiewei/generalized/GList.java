package cn.xiewei.generalized;

/**
 * 广义表操作： 1、广义表的构造 ： 1.1 构造一个空的广义表 1.2 根据现有的广义表，构造一个新的广义表 1.3 根据广义表字符串构造一个广义表
 * 2、广义表的深度 3、广义表的长度 4、按照深度优先顺序打印广义表 5、求广义表表头 6、求广义表表尾
 *
 */
public class GList {

    // 广义表,包括广义表的建立,求深度,复制; 采用的是将广义表划分为"头"和"尾"的模式。
    // 这些操作以"(((a,b),(c,d)),(e,(f,g),h),z)"形式的字符串为基础.一对括号代表一个表.

    // 节点类型
    public static int ATOM = 0;
    public static int LIST = 1;

    public int tag;// 用于区分节点

    public Object atom;// 原子类型
    public GList hp, tp;// 指向表头和表尾

    // 建立广义表"(((a,b),(c,d)),(e,(f,g),h),z)"这样形式的字符串为内通建立广义表
    // 同样采用递归方式。结束条件是空表和原子。
    // 递归建立表头和表尾
    public static GList createGList(GList L, String s) {
        System.out.println(s);
        GList p = null;
        GList q = null;

        if (s.equals("()"))
            L = null;// 如果是空表
        else {
            L = new GList();
            if (s.length() == 1) {
                L.tag = ATOM;
                L.atom = s.charAt(0);
            } // 创建单原子广义表
            else {
                L.tag = LIST;
                p = L;
                String sub = s.substring(1, s.length() - 1);

                do {// 小尾中脱出头，循环建立同一层次的结点
                    Temp temp = new Temp(sub);
                    String hsub = sever(temp);
                    sub = temp.string;

                    p.hp = createGList(p.hp, hsub);
                    q = p;// hsub是头建立头

                    if (!sub.isEmpty()) {// 如果有尾
                        p = new GList();
                        p.tag = LIST;
                        q.tp = p;
                    }
                } while (!sub.isEmpty());
                q.tp = null;
            }
        }

        return L;
    }

    // 该函数处理(((a,b),(c,d)),(e,(f,g),h),z)后，hstr = ((a,b),(c,d)) str =
    // (e,(f,g),h),z.
    // 等于把表头和表尾分开
    public static String sever(Temp t) {
        String str = t.string;
        int n = str.length();
        int i = 0;
        int k = 0;
        char ch;
        String hstr = null;

        do {
            ch = str.charAt(i);
            i++;

            if (ch == '(')
                k++;
            else if (ch == ')')
                k--;
        } while (i < n && (ch != ',' || k != 0));

        if (i < n) {
            hstr = str.substring(0, i - 1);
            str = str.substring(i);
        } else {
            hstr = str;
            str = "";
        }

        t.string = str;
        return hstr;
    }

    // 求广义表的深度
    public static int GetDeepth(GList L) {
        if (L == null)
            return 1;// 空表
        if (L.tag == ATOM)
            return 0;// 原子
        int max = 0;
        GList p = L;
        for (; p != null; p = p.tp) {// 求同一层的光仪表元素的最大深度
            int tem = GetDeepth(p.hp);
            if (tem > max)
                max = tem;
        }

        return max + 1;
    }

    // 复制广义表
    public static GList Copy(GList M, GList L) {// 复制广义表,把L复制到M
        if (L == null)
            M = null;// 空表
        else {
            M = new GList();
            M.tag = L.tag;
            if (M.tag == ATOM)
                M.atom = L.atom;
            else {
                M.hp = Copy(M.hp, L.hp);// 复制头
                M.tp = Copy(M.tp, L.tp);// 复制尾
            }
        }
        return M;
    }

    public static void main(String[] args) {
        GList L = null;
        String s = "(((a,b),(c,d)),(e,(f,g),h),z)";

        // 建表
        L = GList.createGList(L, s);
        // 求表深度
        int len = GList.GetDeepth(L);
        System.out.println("表深度:" + len);
        // 表复制
        GList M = null;
        M = GList.Copy(M, L);
    }
}

// 为了应对值传递，只能传递引用拷贝，无法传递“地址”的问题
class Temp {
    String string = "";

    public Temp(String s) {
        string = s;
    }

}
