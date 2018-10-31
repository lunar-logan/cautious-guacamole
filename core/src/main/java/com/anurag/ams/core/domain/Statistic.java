package com.anurag.ams.core.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA on 10/29/18
 *
 * @author Anurag Gautam
 */
public class Statistic implements Serializable {
    private static final long serialVersionUID = -3513907043132447649L;

    /**
     * ID of the player for which this statistic was recorded
     *
     * @implSpec acts as the primary key of this collection
     */
    private String playerId;

    /**
     * List of match specific stats
     */
    private List<MatchStatistic> matchStats;

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public List<MatchStatistic> getMatchStats() {
        return matchStats;
    }

    public void setMatchStats(List<MatchStatistic> matchStats) {
        this.matchStats = matchStats;
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "playerId='" + playerId + '\'' +
                ", matchStats=" + matchStats +
                '}';
    }
}
