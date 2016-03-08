package net.sports.nutrition.domain.dao;

import net.sports.nutrition.domain.entities.Category;
import org.hibernate.HibernateException;

import java.util.List;

/**
 * The Category Data Access Object is the interface providing
 * access to category and category type related data.
 *
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public interface ICategoryDao extends IGenericDao<Category, Long> {

    /**
     * Returns  all categories.
     *
     * @return <tt>list of Category</tt> if the categories is exist, <tt>null</tt> otherwise
     * @throws HibernateException
     */
    List<Category> findAll() throws HibernateException;

    /**
     * Deletes the category by id.
     *
     * @param id - identifier of the category
     * @return <tt>number greater than zero</tt> if the action is successful, <tt>-1</tt> otherwise.
     * @throws HibernateException
     */
    Integer deleteById(Long id) throws HibernateException;

    /**
     * Returns the category by name.
     *
     * @param name - name of the category
     * @return <tt>Category</tt> if the category is exist, <tt>null</tt> otherwise
     * @throws HibernateException
     */
    Category getCategoryByName(String name) throws HibernateException;
}
