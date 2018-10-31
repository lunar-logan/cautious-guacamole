package com.anurag.ams.core.domain;

import com.anurag.ams.base.common.Standing;
import com.anurag.ams.base.common.type.MatchStatus;

import java.io.Serializable;
import java.time.Duration;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA on 10/27/18
 *
 * @author Anurag Gautam
 */
public class Match implements Serializable {
    private static final long serialVersionUID = -843991083612254328L;

    protected String id;
    protected Date started;
    protected Duration duration;
    protected List<Team> participants;
    protected MatchStatus status;
    protected List<Standing<Team>> standings;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getStarted() {
        return started;
    }

    public void setStarted(Date started) {
        this.started = started;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public List<Team> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Team> participants) {
        this.participants = participants;
    }

    public MatchStatus getStatus() {
        return status;
    }

    public void setStatus(MatchStatus status) {
        this.status = status;
    }

    public List<Standing<Team>> getStandings() {
        return standings;
    }

    public void setStandings(List<Standing<Team>> standings) {
        this.standings = standings;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id='" + id + '\'' +
                ", started=" + started +
                ", duration=" + duration +
                ", participants=" + participants +
                ", status=" + status +
                ", standings=" + standings +
                '}';
    }
}
