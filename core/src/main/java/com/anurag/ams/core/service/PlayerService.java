package com.anurag.ams.core.service;

import com.anurag.ams.base.request.CreatePlayerRequest;
import com.anurag.ams.core.domain.Player;
import com.anurag.ams.core.domain.StatisticDef;
import com.anurag.ams.core.domain.Reward;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA on 10/28/18
 *
 * @author Anurag Gautam
 */
public interface PlayerService {
    Player create(CreatePlayerRequest request);

    Optional<Player> findById(String id);

    Optional<Player> findByEmail(String email);

    Optional<Player> findByHandle(String handle);

    void delete(Player player);

    void deleteByPlayerId(String playerId);

    void defineStat(String playerId, String matchId, StatisticDef stat);

    Long findStat(String playerId, String matchId, String statName);

    Map<String, Long> findHistoricalStats(String playerId);

    Map<String, Long> findStat(String playerId, String matchId);

    void findAndUpdateStat(String playerId, String matchId, String statName, long delta);

    void deleteStat(String playerId, String statName);

    Optional<List<Reward>> getUnlockedRewards(String playerId);

    Optional<List<Reward>> getUnlockedRewards(String playerId, boolean recompute);
}