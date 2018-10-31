package com.anurag.ams.core.service;

import com.anurag.ams.base.request.CreateRewardRequest;
import com.anurag.ams.core.domain.Player;
import com.anurag.ams.core.domain.Reward;
import com.anurag.ams.core.domain.RewardDef;

import java.util.List;

/**
 * Created by IntelliJ IDEA on 10/29/18
 *
 * @author Anurag Gautam
 */
public interface RewardService {
    RewardDef create(CreateRewardRequest request);

    /**
     * @param name
     * @return
     * @throws com.anurag.ams.core.exception.NoSuchAchievementException
     */
    RewardDef find(String name);

    /**
     * @param name
     * @throws com.anurag.ams.core.exception.NoSuchAchievementException
     */
    void delete(String name);

    /**
     * @param achievement
     * @throws com.anurag.ams.core.exception.NoSuchAchievementException
     */
    void delete(RewardDef achievement);

    List<Reward> recompute(Player player);
}
