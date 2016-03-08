package net.sports.nutrition.domain.dao;

import net.sports.nutrition.domain.entities.Product;
import net.sports.nutrition.domain.entities.Taste;
import net.sports.nutrition.domain.enumx.SortType;
import net.sports.nutrition.form.beans.FormFilterBean;
import org.hibernate.HibernateException;

import java.util.List;
import java.util.Map;

/**
 * The Product Data Access Object is the interface providing
 * access to product and product type related data.
 *
 * @author : Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public interface IProductDao extends IGenericDao<Product, Long> {

    /**
     * Returns the product by name.
     *
     * @param name - name of the product
     * @return <tt>Product</tt> if the product is exist, <tt>null</tt> otherwise
     * @throws HibernateException
     */
    Product getProductByName(String name) throws HibernateException;

    /**
     * Returns the product by article number.
     *
     * @param articleNumber -  article the number of product
     * @return <tt>Product</tt> if the product is exist, <tt>null</tt> otherwise
     * @throws HibernateException
     */
    Product getProductByArticleNumber(Long articleNumber) throws HibernateException;

    /**
     * Returns the products by criteria.
     *
     * @param categoryId   -  identifier of the category
     * @param filterParams - the search options
     * @param sortType     - the type sort products
     * @return <tt>list of Product</tt> if the products is exist, <tt>null</tt> otherwise
     * @throws HibernateException
     * @see FormFilterBean
     * @see SortType
     * @see IProductDao#getProductsByCriteria(Long, FormFilterBean, SortType, Integer, Integer)
     */
    List<Product> getProductsByCriteria(Long categoryId, FormFilterBean filterParams, SortType sortType)
            throws HibernateException;

    /**
     * Returns the products by criteria.
     *
     * @param categoryId   -  identifier the of category
     * @param filterParams - the search options
     * @param sortType     - the product sorting type
     * @param firstResult  - the first result to retrieve, numbered from 0
     * @param maxFetchSize - the fetch size
     * @return <tt>list of Product</tt> if the products is exist, <tt>null</tt> otherwise
     * @throws HibernateException
     * @see FormFilterBean
     * @see SortType
     * @see IProductDao#getProductsByCriteria(Long, FormFilterBean, SortType)
     */
    List<Product> getProductsByCriteria(Long categoryId, FormFilterBean filterParams,
                                        SortType sortType, Integer firstResult, Integer maxFetchSize) throws HibernateException;

    /**
     * Returns the products by category id.
     *
     * @param categoryId -  identifier of the category
     * @return <tt>list of Product</tt> if the products is exist, <tt>null</tt> otherwise
     * @throws HibernateException
     */
    List<Product> getProductsByCategoryId(Long categoryId) throws HibernateException;

    /**
     * Returns the ids of products by category id and the taste.
     *
     * @param categoryId -  identifier of the category
     * @return <tt>list of Product</tt> if the products is exist, <tt>null</tt> otherwise
     * @throws HibernateException
     * @see Taste
     * @see IProductDao#getProductsByCriteria(Long, FormFilterBean, SortType)
     */
    List<Long> getProductIdsByTaste(Long categoryId, Taste taste) throws HibernateException;

    /**
     * Returns the products amount by category id.
     *
     * @param categoryId -  identifier of the category
     * @return <tt>list of Product</tt> if the products is exist, <tt>null</tt> otherwise
     * @throws HibernateException
     */
    Long getProductsAmountByCategoryId(Long categoryId) throws HibernateException;

    /**
     * Returns the products amount by property.
     *
     * @param field        - the field in the table for property
     * @param categoryId   - identifier of the category
     * @param propertyList - properties for counting
     * @param filterParams - the search options
     * @return map where key - property, value - count
     * @throws HibernateException
     * @see IProductDao#countProductsByTasteAndCriteria(Long, List, FormFilterBean)
     */
    <T> Map<T, Long> countProductsByProperty(String field, Long categoryId, List<T> propertyList, FormFilterBean filterParams)
            throws HibernateException;

    /**
     * Deletes  product by id.
     *
     * @param id - identifier of the product
     * @return <tt>number greater than zero</tt> if the action is successful, <tt>-1</tt> otherwise.
     * @throws HibernateException
     */
    Integer deleteProductById(Long id) throws HibernateException;

    /**
     * Deletes all products by category id.
     *
     * @param categoryId - the identifier of category
     * @return number of remote products
     * @throws HibernateException
     */
    Integer deleteAllProductByCategoryId(Long categoryId) throws HibernateException;

    /**
     * Returns the products amount by taste.
     *
     * @param categoryId   - the identifier of category
     * @param tasteList    - list of tastes for counting
     * @param filterParams - the search options
     * @return map where key - taste, value - count
     * @throws HibernateException
     * @see IProductDao#countProductsByProperty(String, Long, List, FormFilterBean)
     */
    Map<Taste, Long> countProductsByTasteAndCriteria(Long categoryId, List<Taste> tasteList, FormFilterBean filterParams)
            throws HibernateException;

}