package net.sports_nutrition.domain.repositories.repository_imlementations;


import net.sports_nutrition.domain.repositories.IGenericRepository;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 11.01.2016 12:49
 */

public abstract class GenericRepositoryImpl<T, PK extends Serializable> implements IGenericRepository<T, PK> {

    @Resource(name = "hibernateTemplate")
    private HibernateTemplate hibernateTemplate;

    private Class<T> type;

    public GenericRepositoryImpl() {
    }

    public GenericRepositoryImpl(Class<T> type) {
        this.type = type;
    }

    @Override
    public List<T> findByQueryName(String queryName) {
        return (List<T>) getHibernateTemplate().findByNamedQuery(queryName);

    }

    @Override
    @Transactional(readOnly = false)
    public <T> T save(T entity) {
        getHibernateTemplate().persist(entity);
        return entity;
    }

    @Override
    @Transactional(readOnly = false)
    public <T> T saveOrUpdate(T entity) {
        getHibernateTemplate().saveOrUpdate(entity);
        return entity;
    }

    @Override
    @Transactional(readOnly = false)
    public Boolean delete(T entity) {
        try {
            getHibernateTemplate().delete(entity);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional(readOnly = false)
    public <T> T edit(T entity) {
        try {
            return (T) getHibernateTemplate().merge(entity);
        } catch (Exception ex) {
            return null;
        }
    }


    public <T> T findById(PK entityId) {
        return (T) getHibernateTemplate().get(type, entityId);
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public Session getSession() {
        return hibernateTemplate.getSessionFactory().getCurrentSession();
    }

    public void setType(Class<T> type) {
        this.type = type;
    }

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public Class<T> getType() {
        return type;
    }


}


