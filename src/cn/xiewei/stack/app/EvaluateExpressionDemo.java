package cn.xiewei.stack.app;

import cn.xiewei.stack.Stack;
import cn.xiewei.stack.linked.StackSLinked;

/**
 * ���ʽ��ֵ
 * 
 * ��������� 
 * ���ǵ�ȥ�������ĸ��¸�˰���㷨ȥ���� ��˰֮���ʵľ��ȵľ�����
 * ��ʱ���Ҿ���˼����������������ܹ�ֱ��copy ���ʽ�ö�ã�����Ҫһ�������֣�����˳��ȥ���롣
 * ����������������ʽ��ֵ���㷨���������ܹ�ʵ��������ܡ� 
 * 
 * @author XW
 * @create_date 2019��8��2��
 */
public class EvaluateExpressionDemo {

    public static void main(String[] args) {
        EvaluateExpressionDemo expression = new EvaluateExpressionDemo("1 + 2 ");  
        System.out.println(expression + " = " + expression.evaluate());  
          
        expression = new EvaluateExpressionDemo("4 + 2 * 3 - 10 / 5");  
        System.out.println(expression + " = " + expression.evaluate());  
          
        expression = new EvaluateExpressionDemo("(1+2) * (4 + 5) - (9 / 7)");  
        System.out.println(expression + " = " + expression.evaluate());  
          
        expression = new EvaluateExpressionDemo("(1 + (3 * (4 - 9)))");  
        System.out.println(expression + " = " + expression.evaluate());  
          
        expression = new EvaluateExpressionDemo("(1 + (3 * (4 - 9))) + (3 * (2 + 3))");  
        System.out.println(expression + " = " + expression.evaluate());  

    }

    
    /**
     * ���ʽ
     */
    private String expression;
    /**
     * ����ı��ʽ
     */
    private String initExpression;
    /**
     * �����ջ
     */
    private Stack optr = new StackSLinked();
    /**
     * ������ջ
     */
    private Stack opnd = new StackSLinked();
    /**
     * ������һ���Ƿ�Ӧ�������� 
     */
    private boolean numberNext;

    public EvaluateExpressionDemo(String expression) {
        this.expression = expression;
        this.initExpression = expression;
        numberNext = true;
    }

    /**
     * ��ֵ
     */
    public Integer evaluate() {
        delBlank();// ɾ�����ʽ�еĿհ��ַ�
        handleParentheses();//��������. �������ڵ��ַ�����Ϊ�ӱ��ʽ����

        while (true) {
            if ("".equals(expression)) {
                break;
            }
            
            if (expression.matches("^-?\\d+.*$") && numberNext) {
                opnd.push(getInteger());//��ȡ���ʽ���ֲ��֣����� ������ջ��
                continue;
            } else {
                Character operand = expression.charAt(0);//��ȡ���ʽ�������
                numberNext = true;
                expression = expression.substring(1);
                Character pop = (Character)optr.pop();

                if (pop == null) {
                    optr.push(operand);//��ȡ���ʽ������֣����ջ��Ϊnull���� �����ջ��
                    continue;
                } else {
                    Precede precede = Precede.judgePrecede(pop, operand);//���ջ����Ϊnull��Ҫ�Ƚϣ��´���������� �����ջ�����������ȼ���
                    switch (precede) {
                    //�Ƚϣ�ǰ�������������� ���ȼ���ʱ����ǰ2��������
                    case LARGER: {
                        optr.push(operand);
                        Integer next =(Integer) opnd.pop();
                        Integer last =(Integer) opnd.pop();
                        evaluateNow(last, pop, next);
                        break;
                    }
                    // ���ȼ���ʱ����ǰһ���������ͺ�һ��������
                    case LESS: {
                        optr.push(pop);
                        Integer last = (Integer) opnd.pop();
                        Integer next = getInteger();
                        evaluateNow(last, operand, next);
                        break;
                    }
                    }
                }
            }
        }

        // ������
        Integer result = null;
        if (optr.getSize() == 0 && opnd.getSize() == 1) {
            result =(Integer)  opnd.pop();
        } else if (optr.getSize() == 1 && opnd.getSize() == 2) {
            Integer next =(Integer)  opnd.pop();
            Integer last =(Integer)  opnd.pop();
            evaluateNow(last,(Character) optr.pop(), next);
            result =(Integer)  opnd.pop();
        } else {
            throw new RuntimeException();
        }
        return result;
    }

    /**
     * ����ʵ�ʵ����㣬���������ջ
     */
    private void evaluateNow(Integer last, Character operand, Integer next) {
        switch (operand) {
        case '+':
            opnd.push(last + next);
            break;
        case '-':
            opnd.push(last - next);
            break;
        case '*':
            opnd.push(last * next);
            break;
        case '/':
            opnd.push(last / next);
            break;
        }
    }

    /**
     * ��ñ��ʽ���ֵ�����
     */
    private Integer getInteger() {
        StringBuilder sb = new StringBuilder();
        int count = 0; // ����λ
        boolean lessZero = false; // �Ƿ��Ǹ���
        
        if (expression.startsWith("-")) {
            sb.append("-");
            count++;
            lessZero = true;
        }
        
        int i = (lessZero ? 1 : 0);
        for (; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c >= '0' && c <= '9') {
                sb.append(c);
                count++;
            } else {
                break;
            }
        }
        expression = expression.substring(count);
        numberNext = false;
        return Integer.valueOf(sb.toString());
    }

    /**
     * ��������. �������ڵ��ַ�����Ϊ�ӱ��ʽ����.
     */
    private void handleParentheses() {
        while (expression.contains("(")) {
            // �����ŵ�����
            int left = 0;
            // �����ŵ�����
            int right = 0;
            // �����ŵ�����
            int count = 0;

            // �������������
            left = expression.indexOf('(');

            // �����Ӧ������������
            for (int i = left; i < expression.length(); i++) {
                char c = expression.charAt(i);
                if (c == ')') {
                    count--;
                    // countΪ0ʱ���Ƕ�Ӧ��������
                    if (count == 0) {
                        right = i;
                        break;
                    }
                } else if (c == '(') {
                    count++;
                } else {
                    continue;
                }
            }
            // ��������֮����һ���ӱ��ʽ, �����ӱ��ʽ��ֵ����������б��ʽ��ֵ���������ݽ��������µı��ʽ
            EvaluateExpressionDemo evaluateExpression = new EvaluateExpressionDemo(expression.substring(left + 1, right));
            expression = expression.substring(0, left) + evaluateExpression.evaluate()
                    + expression.substring(right + 1);
        }
    }

    /**
     * ɾ�����ʽ�еĿհ��ַ�
     */
    private void delBlank() {
        expression = expression.replaceAll("\\s", "");
    }
    
    @Override
    public String toString() {
        return initExpression;
    }
}
