package com.anurag.ams.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ IDEA on 10/30/18
 *
 * @author Anurag Gautam
 */
public class Reward implements Serializable {
    private static final long serialVersionUID = 4497012005229515367L;

    private String name;

    /**
     * Timestamp on which this reward was unlocked by the user
     */
    private Date unlocked;
    /**
     * Match ID for which this reward was unlocked, could be {@code null} in case the
     * reward was unlocked based on the historical stats
     */
    private String matchId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getUnlocked() {
        return unlocked;
    }

    public void setUnlocked(Date unlocked) {
        this.unlocked = unlocked;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    @Override
    public String toString() {
        return "Reward{" +
                "name='" + name + '\'' +
                ", unlocked=" + unlocked +
                ", matchId='" + matchId + '\'' +
                '}';
    }
}
