package net.sports.nutrition.domain.dao;

import org.hibernate.HibernateException;

import java.io.Serializable;
import java.util.List;

/**
 * This is the interface for a Data Access Object (DAO).
 * <p>
 * It acts as a manager responsible for the persistence operations on a specific
 * type T of GenericEntity. Each GenericEntity must have a unique type field Pk. It is
 * responsible  for loading, saving, deleting, updating and searching entities of
 * the according type. For each (non-abstract) implementation of GenericEntity there
 * should exist one instance of this interface. Typically when you create a custom
 * Entity you will also create a custom interface and implementation of an according
 * GenericDao. If there is no custom implementation of a GenericDao for some type of
 * GenericEntity, a generic implementation is used as fallback. The PersistenceManager
 * can be seen as the manager of all GenericDao and acts as front-end to them.
 * </p>
 *
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public interface IGenericDao<T, PK extends Serializable> {

    /**
     * Saves an Entity.
     * Persist the given transient instance, first assigning a generated identifier
     *
     * @param entity -  the managed Entity.
     * @return <tt>Entity<tt/> if the action is successful, throw exception  otherwise.
     * @throws HibernateException
     */
    public <T> T save(T entity) throws HibernateException;

    /**
     * Saves or updates an Entity.
     * Updates the persistent instance with the identifier of the given detached instance.
     * If there is a persistent instance with the same identifier. If the Entity is a transient,
     * saves as a new object.
     *
     * @param entity -  the managed Entity.
     * @return <tt>Entity<tt/> if the action is successful, throw exception  otherwise.
     * @throws HibernateException
     */
    public <T> T saveOrUpdate(T entity) throws HibernateException;

    /**
     * Deletes an Entity.
     * This method should be safe i.e., it should fail to delete an Entity if there are
     * integrity constraints (for example, references by other entities).
     *
     * @param entity - the managed Entity.
     * @return <tt>true</tt> if the action is successful, <tt>false</tt>  otherwise.
     * @throws HibernateException
     */
    public Boolean delete(T entity) throws HibernateException;

    /**
     * Edits an Entity.
     * This method should be edit Entity but if Entity doesn't exist, save Entity as new Entity.
     *
     * @param entity -  the managed Entity.
     * @return updated Entity if the action is successful, <tt>null</tt>  otherwise.
     */
    public <T> T edit(T entity);

    /**
     * Finds an Entity by identifier.
     * This method should be find Entity by a unique id
     *
     * @param entityId -  identifier of the Entity.
     * @return <tt>Entity</tt> if the action is successful, <tt>null</tt>  otherwise.
     * @throws HibernateException
     */
    public <T> T findById(PK entityId) throws HibernateException;

    /**
     * Finds an Entity by hibernate query.
     * This method should be find Entity by Hibernate query
     *
     * @param queryName - a query expressed in Hibernate's query language.
     * @return <tt>list of Entity</tt> containing the results of the query execution
     * @throws HibernateException
     */
    public <T> List<T> findByQueryName(String queryName) throws HibernateException;


}