package com.anurag.ams.base.expression;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA on 10/29/18
 *
 * @author Anurag Gautam
 */
public interface Node {
    List<Node> getChildren();

    boolean eval(Map<String, ?> context);
}
