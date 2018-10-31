package com.anurag.ams.persistence.ram.dao;

import com.anurag.ams.core.dao.PlayerDAO;
import com.anurag.ams.core.domain.Player;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Created by IntelliJ IDEA on 10/30/18
 *
 * @author Anurag Gautam
 */
public class PlayerDAOImpl implements PlayerDAO {
    private final Map<String, Player> map = new ConcurrentHashMap<>();

    @Override
    public Optional<Player> findByEmail(String email) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Player> findByHandle(String handle) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Player> save(Player entity) {
        if (entity.getId() == null) {
            entity.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        }
        map.put(entity.getId(), entity);
        return Optional.of(entity);
    }

    @Override
    public List<Player> find() {
        return new ArrayList<>(map.values());
    }

    @Override
    public Optional<Player> find(String id) {
        return Optional.of(map.get(id));
    }

    @Override
    public void delete(Player entity) {
        map.remove(entity.getId());
    }

    @Override
    public void delete(String id) {
        map.remove(id);
    }

    @Override
    public Optional<Player> findAndUpdate(String id, Function<Player, Player> mapper) {
        synchronized (map) {
            Player modified = mapper.apply(map.get(id));
            map.put(id, modified);
            return Optional.ofNullable(modified);
        }
    }
}
