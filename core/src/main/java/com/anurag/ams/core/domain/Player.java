package com.anurag.ams.core.domain;

import com.anurag.ams.base.common.Address;
import com.anurag.ams.base.common.type.PlayerStatus;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 */
public class Player implements Serializable {
    private static final long serialVersionUID = 8525379245612472086L;

    /**
     * Unique generated ID, acts as primary key
     */
    private String id;


    private String firstName;
    private String lastName;

    /**
     * Unique handle for every player
     */
    private String handle;
    private String email;

    private Date birthday;
    private Character gender;
    private Address address;

    private PlayerStatus status;
    private Map<String, StatisticDef> statDef;
    private Map<String, Long> historicalStats;
    private List<Reward> rewardsUnlocked;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public PlayerStatus getStatus() {
        return status;
    }

    public void setStatus(PlayerStatus status) {
        this.status = status;
    }

    public Map<String, StatisticDef> getStatDef() {
        return statDef;
    }

    public void setStatDef(Map<String, StatisticDef> statDef) {
        this.statDef = statDef;
    }

    public Map<String, Long> getHistoricalStats() {
        return historicalStats;
    }

    public void setHistoricalStats(Map<String, Long> historicalStats) {
        this.historicalStats = historicalStats;
    }

    public List<Reward> getRewardsUnlocked() {
        return rewardsUnlocked;
    }

    public void setRewardsUnlocked(List<Reward> rewardsUnlocked) {
        this.rewardsUnlocked = rewardsUnlocked;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", handle='" + handle + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", gender=" + gender +
                ", address=" + address +
                ", status=" + status +
                ", statDef=" + statDef +
                ", historicalStats=" + historicalStats +
                ", rewardsUnlocked=" + rewardsUnlocked +
                '}';
    }
}
