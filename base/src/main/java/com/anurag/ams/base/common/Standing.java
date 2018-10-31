package com.anurag.ams.base.common;

/**
 * Created by IntelliJ IDEA on 10/28/18
 *
 * @author Anurag Gautam
 */
public class Standing<T> {
    private T participant;
    private Integer points;

    public T getParticipant() {
        return participant;
    }

    public void setParticipant(T participant) {
        this.participant = participant;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Standing{" +
                "participant=" + participant +
                ", points=" + points +
                '}';
    }
}
