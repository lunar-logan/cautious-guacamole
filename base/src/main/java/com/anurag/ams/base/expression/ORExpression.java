package com.anurag.ams.base.expression;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA on 10/29/18
 *
 * @author Anurag Gautam
 */
public class ORExpression extends Expression {
    public ORExpression(List<Node> children) {
        super(children);
    }

    @Override
    public boolean eval(Map<String, ?> context) {
        boolean res = false;
        if (getChildren() != null) {
            for (Node child : getChildren()) {
                res = (res || child.eval(context));
            }
        }
        return res;
    }
}
