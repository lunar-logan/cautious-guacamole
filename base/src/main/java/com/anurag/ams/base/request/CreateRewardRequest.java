package com.anurag.ams.base.request;

import com.anurag.ams.base.common.type.RewardType;
import com.anurag.ams.base.expression.Expression;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ IDEA on 10/29/18
 *
 * @author Anurag Gautam
 */
public class CreateRewardRequest implements Serializable {
    private static final long serialVersionUID = -7809360051198663154L;

    private String name;
    private String description;
    private Expression condition;
    private RewardType type;
    private Date created;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RewardType getType() {
        return type;
    }

    public void setType(RewardType type) {
        this.type = type;
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "CreateRewardRequest{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", condition=" + condition +
                ", type=" + type +
                ", created=" + created +
                '}';
    }
}
