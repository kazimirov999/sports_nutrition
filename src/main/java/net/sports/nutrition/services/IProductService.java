package net.sports.nutrition.services;

import net.sports.nutrition.domain.entities.Product;
import net.sports.nutrition.domain.enumx.SortType;
import net.sports.nutrition.form.beans.FormFilterBean;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Service to work with the Product.
 *
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public interface IProductService {

    /**
     * Saves product.
     *
     * @param product - product for save.
     * @return <tt>Product<tt/> if the action is successful, throw exception  otherwise.
     */
    Product saveProduct(Product product);

    /**
     * Saves product width image.
     *
     * @param product - product for save.
     * @param file    - image of the product
     * @return <tt>Product<tt/> if the action is successful, throw exception  otherwise.
     * @throws IOException
     */
    Product saveProductWithImage(Product product, MultipartFile file) throws IOException;

    /**
     * Returns product by identifier.
     *
     * @param productId -  identifier of the Product.
     * @return <tt>Product</tt> if the action is successful, <tt>null</tt>  otherwise.
     */
    Product getProductById(Long productId);

    /**
     * Updates product.
     * This method should be edit product but if product doesn't exist, save product as new Entity.
     *
     * @param product - product for update.
     * @return updated <tt>Product</tt>  if the action is successful, <tt>null</tt>  otherwise.
     */
    Product updateProduct(Product product);

    /**
     * Returns the product by name.
     *
     * @param name - name of the product
     * @return <tt>Product</tt> if the product is exist, <tt>null</tt> otherwise
     */
    Product getProductByName(String name);

    /**
     * Returns the product by article number.
     *
     * @param articleNumber -  article the number of product
     * @return <tt>Product</tt> if the product is exist, <tt>null</tt> otherwise
     */
    Product getProductByArticleNumber(Long articleNumber);

    /**
     * Returns the products by criteria.
     *
     * @param categoryId   -  identifier the of category
     * @param filter       - the search options
     * @param sortType     - the product sorting type
     * @param firstResult  - the first result to retrieve, numbered from 0
     * @param maxFetchSize - the fetch size
     * @return <tt>list of Product</tt> if the products is exist, <tt>null</tt> otherwise
     * @see FormFilterBean
     * @see SortType
     * @see IProductService#getProductsByCriteria(Long, FormFilterBean, SortType)
     */
    List<Product> getProductsByCriteria(Long categoryId, FormFilterBean filter, SortType sortType, Integer firstResult, Integer maxFetchSize);

    /**
     * Returns the products by criteria.
     *
     * @param categoryId   -  identifier of the category
     * @param filterParams - the search options
     * @param sortType     - the type sort products
     * @return <tt>list of Product</tt> if the products is exist, <tt>null</tt> otherwise
     * @see FormFilterBean
     * @see SortType
     * @see IProductService#getProductsByCriteria(Long, FormFilterBean, SortType, Integer, Integer)
     */
    List<Product> getProductsByCriteria(Long categoryId, FormFilterBean filterParams, SortType sortType);

    /**
     * Deletes all products by category id.
     *
     * @param categoryId - the identifier of category
     * @return number of remote products
     */
    Integer deleteAllProductByCategoryId(Long categoryId);

    /**
     * Deletes  product by id.
     *
     * @param productId - identifier of the product
     * @return <tt>number greater than zero</tt> if the action is successful, <tt>-1</tt> otherwise.
     */
    Integer deleteProductById(Long productId);

    /**
     * Checks product of the presence in the database.
     *
     * @param product - product for check
     * @return <tt>true</tt> if product is exist, <tt>false</tt> otherwise
     */
    Boolean productIsExist(Product product);

    /**
     * Checks product before update.
     *
     * @param product - product for check
     * @return <tt>true</tt> if the product of the same article number doesn't exist, <tt>null</tt>  otherwise.
     */
    Boolean checkBeforeUpdateProduct(Product product);
}
