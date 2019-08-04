package cn.xiewei.stack.app;

import cn.xiewei.stack.Stack;
import cn.xiewei.stack.linked.StackSLinked;

/**
 * 表达式求值
 * 
 * 需求分析： 
 * 还记得去，按照哪个新个税的算法去计算 个税之后工资的窘迫的境地吗？
 * 当时，我就在思考，如果，计算器能够直接copy 表达式该多好，不需要一个个数字，按照顺序去输入。
 * 而，现在这个“表达式求值”算法，几乎就能够实现这个功能。 
 * 
 * @author XW
 * @create_date 2019年8月2日
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
     * 表达式
     */
    private String expression;
    /**
     * 最初的表达式
     */
    private String initExpression;
    /**
     * 运算符栈
     */
    private Stack optr = new StackSLinked();
    /**
     * 操作数栈
     */
    private Stack opnd = new StackSLinked();
    /**
     * 表明下一个是否应该是数字 
     */
    private boolean numberNext;

    public EvaluateExpressionDemo(String expression) {
        this.expression = expression;
        this.initExpression = expression;
        numberNext = true;
    }

    /**
     * 求值
     */
    public Integer evaluate() {
        delBlank();// 删除表达式中的空白字符
        handleParentheses();//处理括号. 将括号内的字符串作为子表达式计算

        while (true) {
            if ("".equals(expression)) {
                break;
            }
            
            if (expression.matches("^-?\\d+.*$") && numberNext) {
                opnd.push(getInteger());//获取表达式数字部分，存入 “数据栈”
                continue;
            } else {
                Character operand = expression.charAt(0);//获取表达式算符部分
                numberNext = true;
                expression = expression.substring(1);
                Character pop = (Character)optr.pop();

                if (pop == null) {
                    optr.push(operand);//获取表达式算符部分，算符栈，为null存入 “算符栈”
                    continue;
                } else {
                    Precede precede = Precede.judgePrecede(pop, operand);//算符栈，不为null，要比较，新存入算符，和 “算符栈”最顶层算符优先级。
                    switch (precede) {
                    //比较，前后两个操作符， 优先级高时运算前2个操作数
                    case LARGER: {
                        optr.push(operand);
                        Integer next =(Integer) opnd.pop();
                        Integer last =(Integer) opnd.pop();
                        evaluateNow(last, pop, next);
                        break;
                    }
                    // 优先级低时运算前一个操作数和后一个操作数
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

        // 运算结果
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
     * 进行实际的运算，并将结果入栈
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
     * 获得表达式部分的整数
     */
    private Integer getInteger() {
        StringBuilder sb = new StringBuilder();
        int count = 0; // 整数位
        boolean lessZero = false; // 是否是负数
        
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
     * 处理括号. 将括号内的字符串作为子表达式计算.
     */
    private void handleParentheses() {
        while (expression.contains("(")) {
            // 左括号的索引
            int left = 0;
            // 右括号的索引
            int right = 0;
            // 左括号的数量
            int count = 0;

            // 求出左括号索引
            left = expression.indexOf('(');

            // 求出对应的右括号索引
            for (int i = left; i < expression.length(); i++) {
                char c = expression.charAt(i);
                if (c == ')') {
                    count--;
                    // count为0时才是对应的右括号
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
            // 左右括号之间是一个子表达式, 计算子表达式的值（算出括号中表达式的值），并根据结果构造出新的表达式
            EvaluateExpressionDemo evaluateExpression = new EvaluateExpressionDemo(expression.substring(left + 1, right));
            expression = expression.substring(0, left) + evaluateExpression.evaluate()
                    + expression.substring(right + 1);
        }
    }

    /**
     * 删除表达式中的空白字符
     */
    private void delBlank() {
        expression = expression.replaceAll("\\s", "");
    }
    
    @Override
    public String toString() {
        return initExpression;
    }
}
