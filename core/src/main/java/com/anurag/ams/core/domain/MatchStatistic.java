package com.anurag.ams.core.domain;

import java.io.Serializable;
import java.util.Map;

/**
 * Represents statistics recorded for a given match identified by the match ID
 *
 * @author Anurag Gautam
 */
public class MatchStatistic implements Serializable {
    private static final long serialVersionUID = -4688350223639497420L;

    /**
     * ID of the match for which this document was recorded
     *
     * @implSpec this field will be indexed
     */
    private String matchId;

    /**
     * Stores map of <b>stat name</b> versus the value, for a given game
     * Assuming the no. of stats are going to be limited,
     * this is going to be translated to a plain BSON object.
     */
    private Map<String, Long> stats;

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public Map<String, Long> getStats() {
        return stats;
    }

    public void setStats(Map<String, Long> stats) {
        this.stats = stats;
    }

    @Override
    public String toString() {
        return "MatchStatistic{" +
                "matchId='" + matchId + '\'' +
                ", stats=" + stats +
                '}';
    }
}
