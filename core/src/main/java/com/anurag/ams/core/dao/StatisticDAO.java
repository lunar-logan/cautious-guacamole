package com.anurag.ams.core.dao;

import com.anurag.ams.core.domain.MatchStatistic;
import com.anurag.ams.core.domain.Statistic;

import java.util.Optional;
import java.util.function.Function;

/**
 * Created by IntelliJ IDEA on 10/29/18
 *
 * @author Anurag Gautam
 */
public interface StatisticDAO extends BaseDAO<Statistic, String> {
    Optional<Long> find(String playerId, String matchId, String stat);

    Optional<MatchStatistic> find(String playerId, String matchId);

    void findAndUpdate(String playerId, String matchId, Function<MatchStatistic, MatchStatistic> mapper);
}
