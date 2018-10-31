package com.anurag.ams.base.expression;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by IntelliJ IDEA on 10/29/18
 *
 * @author Anurag Gautam
 */
public class SimpleExpression extends Expression {
    private final String var;
    private final Operator op;
    private final Long val;

    public SimpleExpression(String var, Operator op, Long val) {
        super(Collections.emptyList());
        this.var = var;
        this.op = op;
        this.val = val;
    }

    @Override
    public boolean eval(Map<String, ?> context) {
        switch (op) {
            case LE:
                return eval0(context, var, v -> v < val);
            case LEQ:
                return eval0(context, var, v -> v <= val);
            case EQ:
                return eval0(context, var, v -> v.equals(val));
            case GEQ:
                return eval0(context, var, v -> v >= val);
            case GE:
                return eval0(context, var, v -> v > val);
        }

        return false;
    }

    private boolean eval0(Map<String, ?> context, String var, Function<Long, Boolean> func) {
        Long v = (Long) context.get(var);
        return v != null && func.apply(v);
    }

}
