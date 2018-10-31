package com.anurag.ams.core.dao;

import com.anurag.ams.core.domain.Player;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA on 10/29/18
 *
 * @author Anurag Gautam
 */
public interface PlayerDAO extends BaseDAO<Player, String> {

    Optional<Player> findByEmail(String email);

    Optional<Player> findByHandle(String handle);
}
