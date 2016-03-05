package net.sports.nutrition.domain.repositories;

import net.sports.nutrition.domain.entities.Product;
import net.sports.nutrition.domain.entities.Taste;
import net.sports.nutrition.domain.enumx.SortType;
import net.sports.nutrition.form.beans.FormFilterBean;

import java.util.List;
import java.util.Map;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 11.01.2016 13:51
 */
public interface IProductRepository extends IGenericRepository<Product,Long> {

    Product getProductByName(String name);

    Product getProductByArticleNumber(Long articleNumber);

    Integer deleteProductById(Long id);

    List<Product> getProductsByCriteria(Long categoryId, FormFilterBean filterParams, SortType sortType);

    List<Product> getProductsByCriteria(Long categoryId, FormFilterBean filterParams, SortType sortType, Integer firstResult, Integer maxFetchSize);

    Long getProductsAmountByCategoryId(Long categoryId);

    <T> Map<T,Long> countProductsByProperty(String field, Long categoryId, List<T> propertyList, FormFilterBean filterParams);

    List<Product> getProductsByCategoryId(Long categoryId);

    Integer deleteAllProductByCategoryId(Long categoryId);

    Map<Taste, Long> countProductsByTasteAndCriteria(Long categoryId, List<Taste> tasteList, FormFilterBean filterParams);

    List<Long> getProductIdsByTaste(Long categoryId, Taste taste);
}