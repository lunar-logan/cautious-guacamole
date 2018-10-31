package com.anurag.ams.core.domain.common;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static com.anurag.ams.base.expression.Expression.*;

/**
 * Created by IntelliJ IDEA on 10/29/18
 *
 * @author Anurag Gautam
 */
public class ExpressionTest {
    @Test
    public void simpleTest1() {
        Map<String, Object> ctx = new HashMap<>();
        ctx.put("a", 10L);
        Assert.assertTrue(eq("a", 10L).eval(ctx));
    }

    @Test
    public void testSimpleOr() {
        Map<String, Object> ctx = new HashMap<>();
        ctx.put("a", 10L);
        ctx.put("b", 100L);
        Assert.assertTrue(
                or(
                        eq("a", 1L),
                        eq("b", 100L)
                ).eval(ctx)
        );
    }

    @Test
    public void testSimpleAnd1() {
        Map<String, Object> ctx = new HashMap<>();
        ctx.put("a", 10L);
        ctx.put("b", 100L);
        Assert.assertFalse(
                and(
                        eq("a", 1L),
                        eq("b", 100L)
                ).eval(ctx)
        );
    }

    @Test
    public void testSimpleAnd2() {
        Map<String, Object> ctx = new HashMap<>();
        ctx.put("a", 10L);
        ctx.put("b", 100L);
        Assert.assertTrue(
                and(
                        eq("a", 10L),
                        eq("b", 100L)
                ).eval(ctx)
        );
    }

    @Test
    public void testSimpleNesting1() {
        Map<String, Object> ctx = new HashMap<>();
        ctx.put("a", 10L);
        ctx.put("b", 100L);
        Assert.assertTrue(
                and(
                        eq("a", 10L),
                        or(
                                eq("a", 100L),
                                ge("b", 99L)
                        )
                ).eval(ctx)
        );
    }

    @Test
    public void testSimpleNesting2() {
        Map<String, Object> ctx = new HashMap<>();
        ctx.put("a", 10L);
        ctx.put("b", 100L);
        Assert.assertTrue(
                and(
                        eq("a", 10L),
                        or(
                                eq("a", 100L),
                                and(
                                        geq("b", 100L)
                                )
                        )
                ).eval(ctx)
        );
    }

    @Test
    public void testDeepNesting() {
        Map<String, Object> ctx = new HashMap<>();
        ctx.put("a", 10L);
        ctx.put("b", 100L);
        Assert.assertTrue(
                and(
                        or(
                                and(
                                        or(
                                                and(
                                                        or(
                                                                and(
                                                                        or(
                                                                                le("b", 1000L)
                                                                        )
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                ).eval(ctx)
        );
    }
}