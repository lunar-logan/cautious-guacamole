package com.anurag.ams.core.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Active record representing a team
 */
public class Team implements Serializable {
    private static final long serialVersionUID = -4216526134489770085L;
    private String name;
    private List<Player> players;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", players=" + players +
                '}';
    }
}