package com.anurag.ams.module;

import com.anurag.ams.core.dao.PlayerDAO;
import com.anurag.ams.core.dao.RewardDAO;
import com.anurag.ams.core.dao.StatisticDAO;
import com.anurag.ams.core.exception.SystemException;
import com.anurag.ams.persistence.ram.dao.PlayerDAOImpl;
import com.anurag.ams.persistence.ram.dao.RewardDAOImpl;
import com.anurag.ams.persistence.ram.dao.StatisticDAOImpl;

/**
 * Created by IntelliJ IDEA on 10/30/18
 *
 * @author Anurag Gautam
 */
public class DAOModule implements Module {
    private final PlayerDAO playerDAO = new PlayerDAOImpl();
    private final RewardDAO rewardDAO = new RewardDAOImpl();
    private final StatisticDAO statisticDAO = new StatisticDAOImpl();


    @Override
    public <T> T getSingleton(Class<T> tClass) {
        if (tClass.equals(PlayerDAO.class)) {
            return tClass.cast(playerDAO);
        } else if (tClass.equals(RewardDAO.class)) {
            return tClass.cast(rewardDAO);
        } else if (tClass.equals(StatisticDAO.class)) {
            return tClass.cast(statisticDAO);
        }
        throw new SystemException("No bean definition " + tClass);
    }
}
