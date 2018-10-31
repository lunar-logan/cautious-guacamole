package com.anurag.ams.core.domain;

import com.anurag.ams.base.common.type.StatisticScope;

import java.io.Serializable;

/**
 * Entity representing a definition of an statistic
 *
 * @author Anurag Gautam
 */
public class StatisticDef implements Serializable {
    private static final long serialVersionUID = 7380077812416010419L;

    /**
     * Unique name of the statistic, behaves as primary key
     */
    // indexed
    private String name;

    /**
     * Minimum allowed value
     */
    private Long min;

    /**
     * Maximum allowed value
     */
    private Long max;

    private StatisticScope scope;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMin() {
        return min;
    }

    public void setMin(Long min) {
        this.min = min;
    }

    public Long getMax() {
        return max;
    }

    public void setMax(Long max) {
        this.max = max;
    }


    public StatisticScope getScope() {
        return scope;
    }

    public void setScope(StatisticScope scope) {
        this.scope = scope;
    }

    @Override
    public String toString() {
        return "StatisticDef{" +
                "name='" + name + '\'' +
                ", min=" + min +
                ", max=" + max +
                '}';
    }
}
