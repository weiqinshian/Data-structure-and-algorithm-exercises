package cn.xiewei.generalized;

/**
 * ���������� 1�������Ĺ��� �� 1.1 ����һ���յĹ���� 1.2 �������еĹ��������һ���µĹ���� 1.3 ���ݹ�����ַ�������һ�������
 * 2����������� 3�������ĳ��� 4�������������˳���ӡ����� 5���������ͷ 6���������β
 *
 */
public class GList {

    // �����,���������Ľ���,�����,����; ���õ��ǽ��������Ϊ"ͷ"��"β"��ģʽ��
    // ��Щ������"(((a,b),(c,d)),(e,(f,g),h),z)"��ʽ���ַ���Ϊ����.һ�����Ŵ���һ����.

    // �ڵ�����
    public static int ATOM = 0;
    public static int LIST = 1;

    public int tag;// �������ֽڵ�

    public Object atom;// ԭ������
    public GList hp, tp;// ָ���ͷ�ͱ�β

    // ���������"(((a,b),(c,d)),(e,(f,g),h),z)"������ʽ���ַ���Ϊ��ͨ���������
    // ͬ�����õݹ鷽ʽ�����������ǿձ��ԭ�ӡ�
    // �ݹ齨����ͷ�ͱ�β
    public static GList createGList(GList L, String s) {
        System.out.println(s);
        GList p = null;
        GList q = null;

        if (s.equals("()"))
            L = null;// ����ǿձ�
        else {
            L = new GList();
            if (s.length() == 1) {
                L.tag = ATOM;
                L.atom = s.charAt(0);
            } // ������ԭ�ӹ����
            else {
                L.tag = LIST;
                p = L;
                String sub = s.substring(1, s.length() - 1);

                do {// Сβ���ѳ�ͷ��ѭ������ͬһ��εĽ��
                    Temp temp = new Temp(sub);
                    String hsub = sever(temp);
                    sub = temp.string;

                    p.hp = createGList(p.hp, hsub);
                    q = p;// hsub��ͷ����ͷ

                    if (!sub.isEmpty()) {// �����β
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

    // �ú�������(((a,b),(c,d)),(e,(f,g),h),z)��hstr = ((a,b),(c,d)) str =
    // (e,(f,g),h),z.
    // ���ڰѱ�ͷ�ͱ�β�ֿ�
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

    // ����������
    public static int GetDeepth(GList L) {
        if (L == null)
            return 1;// �ձ�
        if (L.tag == ATOM)
            return 0;// ԭ��
        int max = 0;
        GList p = L;
        for (; p != null; p = p.tp) {// ��ͬһ��Ĺ��Ǳ�Ԫ�ص�������
            int tem = GetDeepth(p.hp);
            if (tem > max)
                max = tem;
        }

        return max + 1;
    }

    // ���ƹ����
    public static GList Copy(GList M, GList L) {// ���ƹ����,��L���Ƶ�M
        if (L == null)
            M = null;// �ձ�
        else {
            M = new GList();
            M.tag = L.tag;
            if (M.tag == ATOM)
                M.atom = L.atom;
            else {
                M.hp = Copy(M.hp, L.hp);// ����ͷ
                M.tp = Copy(M.tp, L.tp);// ����β
            }
        }
        return M;
    }

    public static void main(String[] args) {
        GList L = null;
        String s = "(((a,b),(c,d)),(e,(f,g),h),z)";

        // ����
        L = GList.createGList(L, s);
        // ������
        int len = GList.GetDeepth(L);
        System.out.println("�����:" + len);
        // ����
        GList M = null;
        M = GList.Copy(M, L);
    }
}

// Ϊ��Ӧ��ֵ���ݣ�ֻ�ܴ������ÿ������޷����ݡ���ַ��������
class Temp {
    String string = "";

    public Temp(String s) {
        string = s;
    }

}
