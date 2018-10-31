package com.anurag.ams.persistence.ram.dao;

import com.anurag.ams.base.common.type.RewardType;
import com.anurag.ams.core.dao.RewardDAO;
import com.anurag.ams.core.domain.RewardDef;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by IntelliJ IDEA on 10/30/18
 *
 * @author Anurag Gautam
 */
public class RewardDAOImpl extends AbstractDAO<RewardDef, String> implements RewardDAO {
    private final Object transaction = new Object();
    private final Map<RewardType, List<RewardDef>> rewardTypeIndex = new ConcurrentHashMap<>();

    @Override
    public Optional<RewardDef> save(RewardDef entity) {
        synchronized (transaction) {
            Optional<RewardDef> maybe = super.save(entity);
            maybe.ifPresent(def -> {
                rewardTypeIndex.computeIfAbsent(def.getType(), k -> new ArrayList<>()).add(def);
            });
            return maybe;
        }
    }

    @Override
    public Optional<List<RewardDef>> find(RewardType type) {
        return Optional.ofNullable(rewardTypeIndex.get(type));
    }

    @Override
    protected String getId(RewardDef entity) {
        return entity.getName();
    }

    @Override
    protected String generateId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
