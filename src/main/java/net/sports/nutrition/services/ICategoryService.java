package net.sports.nutrition.services;

import net.sports.nutrition.domain.entities.Category;
import org.hibernate.HibernateException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Service to work with the Category.
 *
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public interface ICategoryService {

    /**
     * Deletes the category by id.
     *
     * @param id - identifier of the category
     * @return <tt>number greater than zero</tt> if the action is successful, <tt>-1</tt> otherwise.
     */
    Integer deleteCategoryById(Long id);

    /**
     * Saves category .
     *
     * @param category - category for save.
     * @return <tt>Category<tt/> if the action is successful, throw exception  otherwise.
     */
    void saveCategory(Category category);

    Category getCategoryById(Long id);

    /**
     * Saves category width image.
     *
     * @param category - category for save.
     * @param file     - image of the category
     * @return <tt>Category<tt/> if the action is successful, throw exception  otherwise.
     * @throws IOException
     */
    void saveCategoryWithImage(Category category, MultipartFile file) throws IOException;

    /**
     * Updates category.
     * This method should be edit category but if category doesn't exist, save category as new Entity.
     *
     * @param category - category for update.
     * @return updated <tt>Category</tt>  if the action is successful, <tt>null</tt>  otherwise.
     */
    Category updateCategory(Category category);

    /**
     * Returns  all categories.
     *
     * @return <tt>list of Category</tt> if the categories is exist, <tt>null</tt> otherwise
     * @throws HibernateException
     */
    List<Category> findAllCategories();

    /**
     * Returns the category by name.
     *
     * @param name - name of the category
     * @return <tt>category</tt> if the category is exist, <tt>null</tt> otherwise
     * @throws HibernateException
     */
    Category getCategoryByName(String name);

    /**
     * Checks category of the presence in the database.
     *
     * @param category - category for check
     * @return <tt>true</tt> if category is exist, <tt>false</tt> otherwise
     */
    Boolean categoryIsExist(Category category);

    /**
     * Checks category before update.
     *
     * @param category - category for check
     * @return <tt>true</tt> if the category of the same name doesn't exist, <tt>null</tt>  otherwise.
     */
    Boolean checkBeforeUpdateCategory(Category category);
}
