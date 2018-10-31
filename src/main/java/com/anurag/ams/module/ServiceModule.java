package com.anurag.ams.module;

import com.anurag.ams.core.dao.PlayerDAO;
import com.anurag.ams.core.dao.RewardDAO;
import com.anurag.ams.core.dao.StatisticDAO;
import com.anurag.ams.core.exception.SystemException;
import com.anurag.ams.core.service.PlayerService;
import com.anurag.ams.core.service.RewardService;
import com.anurag.ams.core.service.impl.PlayerServiceImpl;
import com.anurag.ams.core.service.impl.RewardServiceImpl;

import java.util.concurrent.Executors;

/**
 * Created by IntelliJ IDEA on 10/30/18
 *
 * @author Anurag Gautam
 */
public class ServiceModule implements Module {
    private final Module module;
    private final PlayerService playerService;
    private final RewardService rewardService;

    public ServiceModule(Module daoModule) {
        this.module = daoModule;
        this.rewardService = new RewardServiceImpl(
                Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 4),
                daoModule.getSingleton(RewardDAO.class),
                daoModule.getSingleton(StatisticDAO.class),
                daoModule.getSingleton(PlayerDAO.class)
        );

        this.playerService = new PlayerServiceImpl(
                daoModule.getSingleton(PlayerDAO.class),
                daoModule.getSingleton(StatisticDAO.class),
                rewardService
        );
    }

    @Override
    public <T> T getSingleton(Class<T> tClass) {
        Object instance = null;
        if (tClass.equals(PlayerService.class)) {
            instance = playerService;
        } else if (tClass.equals(RewardService.class)) {
            instance = rewardService;
        }
        if (instance != null) {
            return tClass.cast(instance);
        }
        throw new SystemException("No bean definition " + tClass);
    }
}
