package net.sports.nutrition.services.impl;

import net.sports.nutrition.domain.entities.Product;
import net.sports.nutrition.domain.repositories.IProductRepository;
import net.sports.nutrition.services.IProductService;
import net.sports.nutrition.domain.enumx.SortType;
import net.sports.nutrition.form.beans.FormFilterBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 24.01.2016 10:58
 */

@Transactional
@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {

        return productRepository.saveOrUpdate(product);
    }

    @Override
    public Product saveProductWithImage(Product product, MultipartFile file) throws IOException {
        product.setImageByte(file.getBytes());

        return productRepository.saveOrUpdate(product);
    }

    @Transactional(readOnly = true)
    @Override
    public Product getProductById(Long productId) {

        return productRepository.findById(productId);
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.edit(product);
    }

    @Transactional(readOnly = true)
    @Override
    public Product getProductByName(String name) {

        return productRepository.getProductByName(name);
    }

    @Transactional(readOnly = true)
    @Override
    public Product getProductByArticleNumber(Long articleNumber) {

        return productRepository.getProductByArticleNumber(articleNumber);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> getProductsByCriteria(Long categoryId, FormFilterBean filterParams, SortType sortType, Integer firstResult, Integer maxFetchSise) {

        return productRepository.getProductsByCriteria(categoryId, filterParams, sortType, firstResult, maxFetchSise);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> getProductsByCriteria(Long categoryId, FormFilterBean filterParams, SortType sortType) {

        return productRepository.getProductsByCriteria(categoryId, filterParams, sortType);
    }

    @Override
    public Integer deleteAllProductByCategoryId(Long categoryId) {

        return productRepository.deleteAllProductByCategoryId(categoryId);
    }

    @Override
    public Integer deleteProductById(Long productId) {

        return productRepository.deleteProductById(productId);
    }

    @Transactional(readOnly = true)
    @Override
    public Boolean productIsExist(Product product) {
        Boolean isExist = false;
        if (getProductByArticleNumber(product.getArticleNumber()) != null) {
            isExist = true;
        }
        return isExist;
    }

    @Transactional(readOnly = true)
    @Override
    public Boolean checkBeforeUpdateProduct(Product product) {
        Boolean check = true;
        if (product == null && product.getId() == null) {
            return false;
        }
        Product p = getProductByArticleNumber(product.getArticleNumber());
        if (p != null && !p.getId().equals(product.getId())) {
            check = false;
        }

        return check;
    }
}
