package cn.xiewei.stack.app;

/**
 * ���������Ȩ
 */
public enum Precede {
    /**
     * ����Ȩ��
     */
    LARGER,
    /**
     * ����Ȩ��
     */
    LESS;

    /**
     * ���ȼ���
     *      +   -   *   /
     *  +   >   >   <   <
     *  -   >   >   <   <
     *  *   >   >   >   >
     *  /   >   >   >   >
     */
    private static Precede[][] precedes = new Precede[4][4];

    static {
        // �������ȼ����ʼ��precedes����
        for (int i = 0; i < precedes.length; i++) {
            for (int j = 0; j < precedes[i].length; j++) {
                if ((i == 0 || i == 1) && j > 1) {
                    precedes[i][j] = LESS;
                } else {
                    precedes[i][j] = LARGER;
                }
            }
        }
    }

    /**
     * �ж�2������������ȼ�
     */
    public static Precede judgePrecede(char operand1, char operand2) {
        int left = getIndex(operand1);
        int right = getIndex(operand2);
        return precedes[left][right];
    }

    /**
     * ��ȡ�������Ӧ����������
     */
    private static int getIndex(char operand) {
        switch (operand) {
        case '+':
            return 0;
        case '-':
            return 1;
        case '*':
            return 2;
        case '/':
            return 3;
        default:
            throw new IllegalArgumentException();
        }
    }
}