package com.anurag.ams.persistence.ram.dao;

import com.anurag.ams.core.dao.BaseDAO;
import com.anurag.ams.core.exception.SystemException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Created by IntelliJ IDEA on 10/30/18
 *
 * @author Anurag Gautam
 */
public abstract class AbstractDAO<T, ID extends Serializable> implements BaseDAO<T, ID> {

    /**
     * This map stores the entities indexed by their primary keys
     */
    protected final Map<ID, T> primary = new ConcurrentHashMap<>();

    @Override
    public Optional<T> save(T entity) {
        ID id = getId(entity);
        if (id == null) {
            id = generateId();
        }
        primary.put(id, entity);
        return Optional.of(entity);
    }

    @Override
    public List<T> find() {
        return new ArrayList<>(primary.values());
    }

    @Override
    public Optional<T> find(ID id) {
        return Optional.ofNullable(primary.get(id));
    }

    @Override
    public void delete(T entity) {
        delete(getId(entity));
    }

    @Override
    public void delete(ID id) {
        primary.remove(id);
    }

    @Override
    public Optional<T> findAndUpdate(ID id, Function<T, T> mapper) {
        synchronized (primary) {
            T curRef = primary.get(id);
            T modified = mapper.apply(curRef);
            if (curRef != null && modified != curRef) {
                throw new SystemException("Mapper function returned a new entity, it must have modiefied the older one");
            }
            primary.put(id, modified);
            return Optional.ofNullable(modified);
        }
    }

    protected abstract ID getId(T entity);

    protected abstract ID generateId();
}
