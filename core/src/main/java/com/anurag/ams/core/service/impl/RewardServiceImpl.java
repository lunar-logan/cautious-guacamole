package com.anurag.ams.core.service.impl;

import com.anurag.ams.base.common.type.RewardType;
import com.anurag.ams.base.request.CreateRewardRequest;
import com.anurag.ams.core.dao.PlayerDAO;
import com.anurag.ams.core.dao.RewardDAO;
import com.anurag.ams.core.dao.StatisticDAO;
import com.anurag.ams.core.domain.Player;
import com.anurag.ams.core.domain.Reward;
import com.anurag.ams.core.domain.RewardDef;
import com.anurag.ams.core.service.RewardService;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA on 10/30/18
 *
 * @author Anurag Gautam
 */
public class RewardServiceImpl implements RewardService {
    private static final Logger log = Logger.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());
    private final ExecutorService executor;
    private final RewardDAO rewardDAO;
    private final StatisticDAO statDAO;
    private final PlayerDAO playerDAO;

    public RewardServiceImpl(ExecutorService executor, RewardDAO dao, StatisticDAO statDAO, PlayerDAO playerDAO) {
        this.executor = executor;
        this.rewardDAO = dao;
        this.statDAO = statDAO;
        this.playerDAO = playerDAO;
    }

    @Override
    public RewardDef create(CreateRewardRequest request) {
        RewardDef def = new RewardDef();
        def.setName(request.getName());
        def.setType(request.getType());
        def.setDescription(request.getDescription());
        def.setCondition(request.getCondition());
        def.setDefined(new Date());
        return rewardDAO.save(def).get();
    }

    @Override
    public RewardDef find(String name) {
        return null;
    }

    @Override
    public void delete(String name) {

    }

    @Override
    public void delete(RewardDef achievement) {

    }


    @Override
    public List<Reward> recompute(Player player) {
        List<Reward> unlocked = new ArrayList<>();
        statDAO.find(player.getId()).ifPresent(stat -> {
            if (stat.getMatchStats() != null) {
                stat.getMatchStats().forEach(matchStat -> {
                    if (matchStat.getStats() != null) {
                        rewardDAO.find(RewardType.MATCH_SPECIFIC)
                                .ifPresent(definitions -> {
                                    definitions.forEach(def -> {
                                        if (def.getCondition().eval(matchStat.getStats())) {
                                            Reward reward = getReward(def.getName(), matchStat.getMatchId());
                                            unlocked.add(reward);
                                        }
                                    });
                                });
                    }
                });
            }
        });


        rewardDAO.find(RewardType.PLAYER_SPECIFIC)
                .ifPresent(definitions -> {
                    definitions.forEach(def -> {
                        if (def.getCondition().eval(player.getHistoricalStats())) {
                            Reward reward = getReward(def.getName(), null);
                            unlocked.add(reward);
                        }
                    });
                });

        return unlocked;
    }

    private Reward getReward(String name, String matchId) {
        Reward reward = new Reward();
        reward.setName(name);
        reward.setUnlocked(new Date());
        reward.setMatchId(matchId);
        return reward;
    }
}
