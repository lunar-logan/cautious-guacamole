package com.anurag.ams.persistence;

import com.anurag.ams.base.common.type.RewardType;
import com.anurag.ams.base.expression.Expression;
import com.anurag.ams.base.request.CreatePlayerRequest;
import com.anurag.ams.base.request.CreateRewardRequest;
import com.anurag.ams.core.dao.PlayerDAO;
import com.anurag.ams.core.dao.RewardDAO;
import com.anurag.ams.core.dao.StatisticDAO;
import com.anurag.ams.core.domain.Player;
import com.anurag.ams.core.domain.Reward;
import com.anurag.ams.core.domain.RewardDef;
import com.anurag.ams.core.domain.StatisticDef;
import com.anurag.ams.core.service.PlayerService;
import com.anurag.ams.core.service.RewardService;
import com.anurag.ams.core.service.impl.PlayerServiceImpl;
import com.anurag.ams.core.service.impl.RewardServiceImpl;
import com.anurag.ams.persistence.ram.dao.PlayerDAOImpl;
import com.anurag.ams.persistence.ram.dao.RewardDAOImpl;
import com.anurag.ams.persistence.ram.dao.StatisticDAOImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executors;

/**
 * Created by IntelliJ IDEA on 10/31/18
 *
 * @author Anurag Gautam
 */
public class PlayerServiceImplTest {
    private final PlayerDAO playerDAO = new PlayerDAOImpl();
    private final StatisticDAO statisticDAO = new StatisticDAOImpl();
    private final RewardDAO rewardDAO = new RewardDAOImpl();
    private final RewardService rewardService = new RewardServiceImpl(Executors.newCachedThreadPool(), rewardDAO, statisticDAO, playerDAO);
    private final PlayerService playerService = new PlayerServiceImpl(playerDAO, statisticDAO, rewardService);


    private Player player = null;
    private RewardDef rewardDef = null;

    @Before
    public void setup() {
        CreatePlayerRequest request = new CreatePlayerRequest();
        request.setFirstName("Anurag");
        request.setHandle("anurag");
        player = playerService.create(request);
        System.out.println(player);

        CreateRewardRequest rewardRequest = new CreateRewardRequest();
        rewardRequest.setType(RewardType.MATCH_SPECIFIC);
        rewardRequest.setName("Bruiser");
        rewardRequest.setCondition(Expression.geq("hits", 100L));
        rewardDef = rewardService.create(rewardRequest);
        System.out.println(rewardDef);
    }

    @Test
    public void testCreatePlayer() {
        Assert.assertNotNull(player);
    }


    @Test
    public void testEmptyFindStat1() {
        Map<String, Long> stat = playerService.findStat(player.getId(), "123");
        Assert.assertTrue(stat.isEmpty());
    }

    @Test
    public void testEmptyFindStat2() {
        Long hits = playerService.findStat(player.getId(), "123", "hits");
        Assert.assertNull(hits);
    }

    @Test
    public void testDefineStat() {
        playerService.defineStat(player.getId(), "123", new StatisticDef() {{
            setName("hits");
        }});

        Assert.assertEquals(0L, (long) playerService.findStat(player.getId(), "123", "hits"));
    }

    @Test
    public void testUpdateStat() {
        playerService.defineStat(player.getId(), "123", new StatisticDef() {{
            setName("hits");
        }});

        playerService.findAndUpdateStat(player.getId(), "123", "hits", 100);

        Assert.assertEquals(100L, (long) playerService.findStat(player.getId(), "123", "hits"));
    }


    @Test
    public void testDefineReward() {
        Assert.assertNotNull(rewardDef);
    }

    @Test
    public void testFetchUnlockedRewards() {
        playerService.defineStat(player.getId(), "123", new StatisticDef() {{
            setName("hits");
        }});

        playerService.findAndUpdateStat(player.getId(), "123", "hits", 100);

        Optional<List<Reward>> unlockedRewards = playerService.getUnlockedRewards(player.getId(), true);
        Assert.assertTrue(unlockedRewards.isPresent());

        List<Reward> rewards = unlockedRewards.get();
        System.out.println(rewards);
        Assert.assertFalse(rewards.isEmpty());
        Assert.assertEquals(1, rewards.size());
        Assert.assertEquals("123", rewards.get(0).getMatchId());
    }

    @Test
    public void testFetchUnlockedRewards2() {
        playerService.defineStat(player.getId(), "123", new StatisticDef() {{
            setName("hits");
        }});

        playerService.defineStat(player.getId(), "123", new StatisticDef() {{
            setName("miss");
        }});

        playerService.findAndUpdateStat(player.getId(), "123", "hits", 100);
        playerService.findAndUpdateStat(player.getId(), "123", "miss", 10);

        CreateRewardRequest rewardRequest = new CreateRewardRequest();
        rewardRequest.setType(RewardType.PLAYER_SPECIFIC);
        rewardRequest.setName("Sharp Shooter");
        rewardRequest.setCondition(Expression.le("miss", 20L));
        RewardDef def = rewardService.create(rewardRequest);
        System.out.println(def);

        Optional<List<Reward>> unlockedRewards = playerService.getUnlockedRewards(player.getId(), true);
        Assert.assertTrue(unlockedRewards.isPresent());

        List<Reward> rewards = unlockedRewards.get();
        System.out.println(rewards);
        Assert.assertFalse(rewards.isEmpty());
        Assert.assertEquals(2, rewards.size());
    }
}