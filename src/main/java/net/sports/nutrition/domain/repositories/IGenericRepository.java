package net.sports.nutrition.domain.repositories;

import java.io.Serializable;
import java.util.List;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 11.01.2016 12:27
 */
public interface IGenericRepository<T, PK extends Serializable> {

    public <T> T save(T entity);

    public <T> T saveOrUpdate(T entity);

    public  Boolean delete(T entity);

    public <T> T edit(T entity);

    public <T> T findById(PK entityId);

    public <T> List<T> findByQueryName(String queryName);


}