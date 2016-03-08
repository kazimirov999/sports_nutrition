package net.sports.nutrition.domain.dao;

import net.sports.nutrition.domain.entities.Taste;
import org.hibernate.HibernateException;

import java.util.List;

/**
 * The Taste Data Access Object is the interface providing
 * access to taste and taste type related data.
 *
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public interface ITasteDao extends IGenericDao<Taste, Long> {

    /**
     * Returns  all tastes.
     *
     * @return <tt>list of Taste</tt> if the tastes is exist, <tt>null</tt> otherwise
     * @throws HibernateException
     */
    List<Taste> findAll() throws HibernateException;

    /**
     * Returns the tastes by category id.
     *
     * @param categoryId - identifier of the category
     * @return <tt>list of Taste</tt> if the tastes is exist, <tt>null</tt> otherwise
     * @throws HibernateException
     */
    List<Taste> getAllTastesByCategoryId(Long categoryId) throws HibernateException;

    /**
     * Returns the taste by name.
     *
     * @param tasteName -  name of the taste
     * @return <tt>Taste</tt> if the taste is exist, <tt>null</tt> otherwise
     * @throws HibernateException
     */
    Taste getTasteByName(String tasteName) throws HibernateException;

    /**
     * Deletes the taste by id.
     *
     * @param tasteId - identifier of the taste
     * @return <tt>number greater than zero</tt> if the action is successful, <tt>-1</tt> otherwise.
     * @throws HibernateException
     */
    Integer deleteTasteById(Long tasteId) throws HibernateException;


}
