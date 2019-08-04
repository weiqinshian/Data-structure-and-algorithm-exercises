package cn.xiewei.stack.app;

/**
 * 运算符优先权
 */
public enum Precede {
    /**
     * 优先权高
     */
    LARGER,
    /**
     * 优先权低
     */
    LESS;

    /**
     * 优先级表
     *      +   -   *   /
     *  +   >   >   <   <
     *  -   >   >   <   <
     *  *   >   >   >   >
     *  /   >   >   >   >
     */
    private static Precede[][] precedes = new Precede[4][4];

    static {
        // 根据优先级表初始化precedes数组
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
     * 判断2个运算符的优先级
     */
    public static Precede judgePrecede(char operand1, char operand2) {
        int left = getIndex(operand1);
        int right = getIndex(operand2);
        return precedes[left][right];
    }

    /**
     * 获取运算符对应的数组索引
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