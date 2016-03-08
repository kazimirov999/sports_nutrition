package net.sports.nutrition.services;

import net.sports.nutrition.domain.entities.Taste;

import java.util.List;

/**
 * Service to work with the Taste.
 *
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public interface ITasteService {

    /**
     * Saves taste.
     *
     * @param taste - taste for save.
     * @return <tt>Taste<tt/> if the action is successful, throw exception  otherwise.
     */
    Taste saveTaste(Taste taste);

    /**
     * Returns taste by identifier.
     *
     * @param id -  identifier of the taste.
     * @return <tt>Taste</tt> if the action is successful, <tt>null</tt>  otherwise.
     */
    Taste getTasteById(Long id);

    /**
     * Returns  all tastes.
     *
     * @return <tt>list of Taste</tt> if the tastes is exist, <tt>null</tt> otherwise
     */
    List<Taste> findAllTastes();

    /**
     * Returns the taste by name.
     *
     * @param tasteName -  name of the taste
     * @return <tt>Taste</tt> if the taste is exist, <tt>null</tt> otherwise
     */
    Taste getTasteByName(String tasteName);

    /**
     * Deletes the taste by id.
     *
     * @param tasteId - identifier of the taste
     * @return <tt>number greater than zero</tt> if the action is successful, <tt>-1</tt> otherwise.
     */
    Integer deleteTasteById(Long tasteId);

    /**
     * Checks taste of the presence in the database.
     *
     * @param taste - taste for check
     * @return <tt>true</tt> if taste is exist, <tt>false</tt> otherwise
     */
    Boolean tasteIsExist(Taste taste);

    /**
     * Checks taste before update.
     *
     * @param taste - taste for check
     * @return <tt>true</tt> if the taste of the same name doesn't exist, <tt>null</tt>  otherwise.
     */
    Boolean checkBeforeUpdateTaste(Taste taste);
}
