package com.anurag.ams.persistence.ram.dao;

import com.anurag.ams.core.dao.StatisticDAO;
import com.anurag.ams.core.domain.MatchStatistic;
import com.anurag.ams.core.domain.Statistic;
import com.anurag.ams.core.exception.SystemException;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Created by IntelliJ IDEA on 10/30/18
 *
 * @author Anurag Gautam
 */
public class StatisticDAOImpl extends AbstractDAO<Statistic, String> implements StatisticDAO {
    private final Object transaction = new Object();

    /**
     * This primary is a helper to support constant time lookups for {@link MatchStatistic}
     * for a particular player {@link com.anurag.ams.core.domain.Player} for a particular
     * {@link com.anurag.ams.core.domain.Match}
     * <p>
     * {@code matchStatIndex} is a {@link Map} of <b>player ID</b> versus another {@link Map} of
     * <b>match ID</b> versus the {@link MatchStatistic} object
     */
    private final Map<String, Map<String, MatchStatistic>> matchStatIndex = new ConcurrentHashMap<>();

    @Override
    public Optional<Statistic> save(Statistic entity) {
        synchronized (transaction) {

            // Persist the entity
            Statistic statistic = super.save(entity).orElseThrow(() -> new SystemException("Could not persist " + entity));

            // Create appropriate index, for faster lookups later
            matchStatIndex.putIfAbsent(statistic.getPlayerId(), new ConcurrentHashMap<>());

            /*
                Index any (if present) MatchStatistic objects not indexed before.
                Helpful when updating the Statistic entity
            */
            List<MatchStatistic> matchStats = statistic.getMatchStats();
            if (matchStats != null) {
                matchStats.forEach(matchStat -> {
                    matchStatIndex.get(statistic.getPlayerId()).put(matchStat.getMatchId(), matchStat);
                });
            }

            return Optional.of(statistic);
        }
    }

    @Override
    public Optional<Long> find(String playerId, String matchId, String statName) {
        return Optional.ofNullable(matchStatIndex.get(playerId))
                .map(m -> m.get(matchId))
                .map(MatchStatistic::getStats)
                .map(stats -> stats.get(statName));
    }

    @Override
    public Optional<MatchStatistic> find(String playerId, String matchId) {
        return Optional.ofNullable(matchStatIndex.get(playerId))
                .map(m -> m.get(matchId));
    }

    /**
     * Used to update stats for a given player per match basis
     *
     * @param playerId the player ID
     * @param matchId  match ID
     * @param mapper
     */
    @Override
    public void findAndUpdate(String playerId, String matchId, Function<MatchStatistic, MatchStatistic> mapper) {
        matchStatIndex.computeIfPresent(playerId, (id1, map) -> {
            map.computeIfPresent(matchId, (id2, statistic) -> {
                MatchStatistic updatedStatistic = mapper.apply(statistic);
                if (statistic != updatedStatistic) {
                    throw new SystemException("Mapper function returned a new entity, it must have modified the older one");
                }

                return updatedStatistic;
            });
            return map;
        });
//        primary.computeIfPresent(playerId, (id1, statistic) -> {
//            matchStatIndex.computeIfPresent(playerId, (id2, map) -> {
//                map.computeIfPresent(matchId, (id3, mapStat) -> {
//                    MatchStatistic updated = mapper.apply(mapStat);
//                    if (updated != mapStat) { // Reference changed
//                        throw new SystemException("Mapper function returned a new entity, it must have modified the older one");
//                    }
//                    return updated;
//                });
//                return map;
//            });
//            return statistic;
//        });
    }

    @Override
    protected String getId(Statistic entity) {
        return entity.getPlayerId();
    }

    @Override
    protected String generateId() {
        throw new UnsupportedOperationException();
    }
}
