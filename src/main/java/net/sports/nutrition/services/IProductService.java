package net.sports.nutrition.services;

import net.sports.nutrition.domain.entities.Product;
import net.sports.nutrition.domain.enumx.SortType;
import net.sports.nutrition.form.beans.FormFilterBean;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 24.01.2016 10:56
 */
public interface IProductService {

    Product saveProduct(Product product);

    Product saveProductWithImage(Product product, MultipartFile file) throws IOException;

    Product getProductById(Long productId);

    Product updateProduct(Product product);

    Boolean productIsExist(Product product);

    Product getProductByName(String name);

    Product getProductByArticleNumber(Long articleNumber);

    List<Product> getProductsByCriteria(Long categoryId, FormFilterBean filter, SortType sortType, Integer firstResult, Integer maxFetchSise);

    List<Product> getProductsByCriteria(Long categoryId, FormFilterBean filterParams, SortType sortType);

    Integer deleteAllProductByCategoryId(Long categoryId);

    Integer deleteProductById(Long productId);

    Boolean checkBeforeUpdateProduct(Product product);
}
