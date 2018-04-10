package pattern.design_pattern.behavioral_patterns;

import org.junit.Test;

/**
 * 解释器模式
 *
 * 参考：http://www.runoob.com/design-pattern/interpreter-pattern.html
 * Android源码设计模式解析与实战 189页
 */
public class InterpreterPattern {

    interface Expression {
        boolean interpret(String context);
    }

    class TerminalExpression implements Expression {

        private String data;

        public TerminalExpression(String data) {
            this.data = data;
        }

        @Override
        public boolean interpret(String context) {
            if (context.contains(data)) {
                return true;
            }
            return false;
        }
    }

    class OrExpression implements Expression {

        Expression expr1 = null;
        Expression expr2 = null;

        public OrExpression(Expression expr1, Expression expr2) {
            this.expr1 = expr1;
            this.expr2 = expr2;
        }

        @Override
        public boolean interpret(String context) {
            return expr1.interpret(context) || expr2.interpret(context);
        }
    }

    class AndExpression implements Expression {

        Expression expr1 = null;
        Expression expr2 = null;

        public AndExpression(Expression expr1, Expression expr2) {
            this.expr1 = expr1;
            this.expr2 = expr2;
        }

        @Override
        public boolean interpret(String context) {
            return expr1.interpret(context) && expr2.interpret(context);
        }
    }

    Expression getMaleExpression() {
        Expression robert = new TerminalExpression("Robert");
        Expression john = new TerminalExpression("John");
        return new OrExpression(robert, john);
    }

    Expression getMarriedWomanExpression() {
        Expression julie = new TerminalExpression("Julie");
        Expression married = new TerminalExpression("Married");
        return new AndExpression(julie, married);
    }

    @Test
    public void testPattern() {
        Expression isMale = getMaleExpression();
        Expression isMarriedWoman = getMarriedWomanExpression();

        System.out.println("John is male? " + isMale.interpret("John"));
        System.out.println("Julie is a married women? " + isMarriedWoman.interpret("Married Julie"));
    }

}
