package com.anurag.ams.core.dao;

import com.anurag.ams.base.common.type.RewardType;
import com.anurag.ams.core.domain.RewardDef;

import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA on 10/29/18
 *
 * @author Anurag Gautam
 */
public interface RewardDAO extends BaseDAO<RewardDef, String> {
    Optional<List<RewardDef>> find(RewardType type);
}
