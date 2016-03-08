package net.sports.nutrition.domain.dao.impl;


import net.sports.nutrition.domain.dao.IGenericDao;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * Implementation of IGenericDao using Hibernate.
 * <p>
 * The HibernateTemplate property is annotated for automatic resource injection.
 * </p>
 *
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public abstract class GenericDaoImpl<T, PK extends Serializable> implements IGenericDao<T, PK> {

    @Resource(name = "hibernateTemplate")
    private HibernateTemplate hibernateTemplate;

    private Class<T> type;

    public GenericDaoImpl() {
    }

    public GenericDaoImpl(Class<T> type) {
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
            getSession().delete(entity);
            getSession().flush();
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


