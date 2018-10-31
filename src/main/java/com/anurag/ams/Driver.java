package com.anurag.ams;

import com.anurag.ams.base.common.type.RewardType;
import com.anurag.ams.base.common.type.StatisticScope;
import com.anurag.ams.base.expression.Expression;
import com.anurag.ams.base.request.CreatePlayerRequest;
import com.anurag.ams.base.request.CreateRewardRequest;
import com.anurag.ams.core.domain.Player;
import com.anurag.ams.core.domain.Reward;
import com.anurag.ams.core.domain.RewardDef;
import com.anurag.ams.core.domain.StatisticDef;
import com.anurag.ams.core.service.PlayerService;
import com.anurag.ams.core.service.RewardService;
import com.anurag.ams.module.DAOModule;
import com.anurag.ams.module.ServiceModule;

import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA on 10/30/18
 *
 * @author Anurag Gautam
 */
public class Driver {
    private static final ServiceModule serviceModule = new ServiceModule(new DAOModule());
    private static final PlayerService service = serviceModule.getSingleton(PlayerService.class);

    public static void main(String[] args) throws InterruptedException {

        // Lets create a player
        Player player = createPlayer("Anurag", "Gautam", "anurag");
        System.out.println("Created player: " + player);

        createRewards();
        System.out.println("Rewards created");

        defineStats(player);

        // Increment the stats
        service.findAndUpdateStat(player.getId(), null, "wins", 200);
        service.findAndUpdateStat(player.getId(), null, "games", 2000);
        service.findAndUpdateStat(player.getId(), "123", "damage", 2000);

        printCurrentUnlockedRewards(player);
        System.out.println(service.findHistoricalStats(player.getId()));
    }

    private static void defineStats(Player player) {
        service.defineStat(player.getId(), "", new StatisticDef() {{
            setName("wins");
            setScope(StatisticScope.PLAYER);
        }});

        service.defineStat(player.getId(), "", new StatisticDef() {{
            setName("games");
            setScope(StatisticScope.PLAYER);
        }});

        service.defineStat(player.getId(), "123", new StatisticDef() {{
            setName("damage");
            setScope(StatisticScope.MATCH);
        }});
    }

    private static void createRewards() {
//        createReward("Sharpshooter Award", Expression.geq(""), RewardType.PLAYER_SPECIFIC);
        createReward("Bruiser", Expression.geq("damage", 500L), RewardType.MATCH_SPECIFIC);
        createReward("Veteran", Expression.geq("games", 1000L), RewardType.PLAYER_SPECIFIC);
        createReward("Big Winner", Expression.geq("wins", 200L), RewardType.PLAYER_SPECIFIC);
    }

    private static void printCurrentUnlockedRewards(Player player) {
        List<Reward> rewards = service.getUnlockedRewards(player.getId(), true).orElse(Collections.emptyList());
        System.out.println("Rewards unlocked: " + rewards);
    }

    static Player createPlayer(String firstName, String lastName, String handle) {
        CreatePlayerRequest request = new CreatePlayerRequest();
        request.setFirstName("Anurag");
        request.setLastName("Gautam");
        request.setHandle("anurag");
        return serviceModule.getSingleton(PlayerService.class).create(request);
    }

    static RewardDef createReward(String name, Expression condition, RewardType type) {
        CreateRewardRequest req = new CreateRewardRequest();
        req.setName(name);
        req.setCondition(condition);
        req.setType(type);
        return serviceModule.getSingleton(RewardService.class).create(req);
    }
}
