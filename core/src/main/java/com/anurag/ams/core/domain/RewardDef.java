package com.anurag.ams.core.domain;

import com.anurag.ams.base.common.type.RewardType;
import com.anurag.ams.base.expression.Expression;

import java.util.Date;

/**
 */
public class RewardDef {
    /**
     * Unique name for every achievement
     */
    private String name;

    /**
     * Description of the reward
     */
    private String description;


    private RewardType type;

    /**
     * Condition determines if this reward can be unlocked for the given Player
     */
    private Expression condition;

    /**
     * The date/timestamp on which this reward was defined
     */
    private Date defined;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Expression getCondition() {
        return condition;
    }

    public void setCondition(Expression condition) {
        this.condition = condition;
    }

    public Date getDefined() {
        return defined;
    }

    public void setDefined(Date defined) {
        this.defined = defined;
    }

    public RewardType getType() {
        return type;
    }

    public void setType(RewardType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "RewardDef{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", condition=" + condition +
                ", defined=" + defined +
                '}';
    }
}
