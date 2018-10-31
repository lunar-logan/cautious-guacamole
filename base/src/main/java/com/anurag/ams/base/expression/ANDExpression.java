package com.anurag.ams.base.expression;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA on 10/29/18
 *
 * @author Anurag Gautam
 */
public class ANDExpression extends Expression {
    public ANDExpression(List<Node> children) {
        super(children);
    }

    @Override
    public boolean eval(Map<String, ?> context) {
        boolean res = true;
        if (children != null) {
            for (Node child : children) {
                res = (res && child.eval(context));
            }
        }
        return res;
    }
}
