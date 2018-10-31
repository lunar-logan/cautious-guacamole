package com.anurag.ams.core.service.impl;

import com.anurag.ams.base.request.CreatePlayerRequest;
import com.anurag.ams.core.dao.PlayerDAO;
import com.anurag.ams.core.dao.StatisticDAO;
import com.anurag.ams.core.domain.*;
import com.anurag.ams.core.exception.NoSuchPlayerException;
import com.anurag.ams.core.exception.NoSuchStatException;
import com.anurag.ams.core.exception.SystemException;
import com.anurag.ams.core.service.PlayerService;
import com.anurag.ams.core.service.RewardService;

import java.util.*;

/**
 * Created by IntelliJ IDEA on 10/29/18
 *
 * @author Anurag Gautam
 */
public class PlayerServiceImpl implements PlayerService {
    private final PlayerDAO playerDAO;
    private final StatisticDAO statDAO;
    private final RewardService rewardService;

    public PlayerServiceImpl(PlayerDAO playerDAO, StatisticDAO statDAO, RewardService rewardService) {
        this.playerDAO = playerDAO;
        this.statDAO = statDAO;
        this.rewardService = rewardService;
    }

    @Override
    public Player create(CreatePlayerRequest request) {
        Player player = new Player();
        player.setFirstName(request.getFirstName());
        player.setLastName(request.getLastName());
        player.setBirthday(request.getBirthday());
        player.setEmail(request.getEmail());
        player.setHandle(request.getHandle());
        player.setGender(request.getGender());
        return playerDAO.save(player).get();
    }

    @Override
    public Optional<Player> findById(String id) {
        return playerDAO.find(id);
    }

    @Override
    public Optional<Player> findByEmail(String email) {
        return playerDAO.findByEmail(email);
    }

    @Override
    public Optional<Player> findByHandle(String handle) {
        return playerDAO.findByHandle(handle);
    }

    @Override
    public void delete(Player player) {
        playerDAO.delete(player);
    }

    @Override
    public void deleteByPlayerId(String playerId) {
        playerDAO.delete(playerId);
    }


    @Override
    public void defineStat(String playerId, String matchId, StatisticDef stat) {
        try {
            playerDAO.findAndUpdate(playerId, player -> {
                if (player == null) {
                    throw new NoSuchPlayerException("NO player found with ID: '" + playerId + "'");
                }

                Map<String, StatisticDef> stats = player.getStatDef();
                if (stats == null) {
                    stats = new HashMap<>();
                    player.setStatDef(stats);
                }

                if (stats.putIfAbsent(stat.getName(), stat) != null) {
                    throw new SystemException("Statistic '" + stat + "' already defined for player: '" + playerId + "', delete first");
                }

                statDAO.save(getStatistic(playerId, matchId, stat.getName()));
                return player;
            });
        } catch (Exception ex) {
            throw new SystemException(ex);
        }
    }

    private Statistic getStatistic(String playerId, String matchId, String statName) {
        MatchStatistic matchStat = new MatchStatistic();
        matchStat.setMatchId(matchId);
        matchStat.setStats(new HashMap<>());
        matchStat.getStats().put(statName, 0L);

        Statistic statistic = new Statistic();
        statistic.setPlayerId(playerId);
        statistic.setMatchStats(Collections.singletonList(matchStat));
        return statistic;
    }


    @Override
    public Long findStat(String playerId, String matchId, String statName) {
        return statDAO.find(playerId, matchId, statName).orElse(null);
    }

    @Override
    public Map<String, Long> findHistoricalStats(String playerId) {
        return playerDAO.find(playerId).map(Player::getHistoricalStats).orElse(Collections.emptyMap());
    }

    @Override
    public Map<String, Long> findStat(String playerId, String matchId) {
        return statDAO.find(playerId, matchId)
                .map(MatchStatistic::getStats)
                .orElseGet(Collections::emptyMap);
    }

    @Override
    public void findAndUpdateStat(String playerId, String matchId, String statName, long delta) {
        // Fetch the scope of he stat
        playerDAO.find(playerId)
                .map(Player::getStatDef)
                .map(defs -> defs.get(statName))
                .ifPresent(statDef -> {
                    if (statDef.getScope() == null) {
                        updateMatchStats(playerId, matchId, statName, delta);
                    } else {
                        switch (statDef.getScope()) {
                            case PLAYER:
                                playerDAO.findAndUpdate(playerId, player -> {
                                    Map<String, Long> stats = player.getHistoricalStats();
                                    if (stats == null) {
                                        stats = new HashMap<>();
                                        player.setHistoricalStats(stats);
                                    }

                                    stats.put(statName, stats.getOrDefault(statName, 0L) + delta);
                                    return player;
                                });
                                break;
                            case MATCH:
                                updateMatchStats(playerId, matchId, statName, delta);
                                break;
                        }
                    }

                });
    }

    private void updateMatchStats(String playerId, String matchId, String statName, long delta) {
        statDAO.findAndUpdate(playerId, matchId, matchStat -> {
            if (matchStat == null) {
                throw new NoSuchStatException("No stat with name '" + statName + "' found for player ID: " + playerId);
            }


            Map<String, Long> stats = matchStat.getStats();
            if (stats == null) {
                stats = new HashMap<>();
                matchStat.setStats(stats);
            }
            stats.put(statName, stats.getOrDefault(statName, 0L) + delta);
            return matchStat;
        });

        statDAO.find(playerId, matchId, statName)
                .ifPresent(newVal -> {
                    playerDAO.findAndUpdate(playerId, player -> {
                        Map<String, Long> historicalStats = player.getHistoricalStats();
                        if (historicalStats == null) {
                            historicalStats = new HashMap<>();
                            player.setHistoricalStats(historicalStats);
                        }

                        historicalStats.put(statName, newVal);
                        return player;
                    });
                });
    }

    @Override
    public void deleteStat(String playerId, String statName) {
        playerDAO.findAndUpdate(playerId, player -> {
            if (player == null) {
                throw new NoSuchPlayerException("No player with ID: " + playerId);
            }
            Map<String, StatisticDef> def = player.getStatDef();
            if (def != null) {
                def.remove(statName);
            }

            return player;
        });

    }

    @Override
    public Optional<List<Reward>> getUnlockedRewards(String playerId) {
        return playerDAO.find(playerId).map(Player::getRewardsUnlocked);
    }

    @Override
    public Optional<List<Reward>> getUnlockedRewards(String playerId, boolean recompute) {
        if (!recompute) return getUnlockedRewards(playerId);
        return playerDAO.find(playerId)
                .map(rewardService::recompute)
                .map(rewards -> {
                    playerDAO.findAndUpdate(playerId, player -> {
                        player.setRewardsUnlocked(rewards);
                        return player;
                    });

                    return rewards;
                });
    }
}
