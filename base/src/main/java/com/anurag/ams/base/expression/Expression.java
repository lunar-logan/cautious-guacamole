package com.anurag.ams.base.expression;

import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA on 10/29/18
 *
 * @author Anurag Gautam
 */
public abstract class Expression implements Node {
    protected final List<Node> children;

    public Expression(List<Node> children) {
        this.children = children;
    }

    @Override
    public List<Node> getChildren() {
        return children;
    }

    public static Expression simple(String key, Operator op, Long val) {
        return new SimpleExpression(key, op, val);
    }

    public static ANDExpression and(Expression... expressions) {
        return new ANDExpression(Arrays.asList(expressions));
    }

    public static ORExpression or(Expression... expressions) {
        return new ORExpression(Arrays.asList(expressions));
    }

    public static Expression eq(String key, Long val) {
        return simple(key, Operator.EQ, val);
    }

    public static Expression le(String key, Long val) {
        return simple(key, Operator.LE, val);
    }

    public static Expression leq(String key, Long val) {
        return simple(key, Operator.LEQ, val);
    }

    public static Expression ge(String key, Long val) {
        return simple(key, Operator.GE, val);
    }

    public static Expression geq(String key, Long val) {
        return simple(key, Operator.GEQ, val);
    }
}
