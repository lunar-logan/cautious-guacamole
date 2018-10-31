package com.anurag.ams.core.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * Created by IntelliJ IDEA on 10/29/18
 *
 * @author Anurag Gautam
 */
public interface BaseDAO<T, ID extends Serializable> {
    Optional<T> save(T entity);

    List<T> find();

    Optional<T> find(ID id);

    void delete(T entity);

    void delete(ID id);

    Optional<T> findAndUpdate(ID id, Function<T, T> mapper);
}
