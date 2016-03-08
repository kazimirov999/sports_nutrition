package net.sports.nutrition.services.impl;

import net.sports.nutrition.domain.entities.Product;
import net.sports.nutrition.domain.dao.IProductDao;
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
 * Service to work with the Product, using IProductDao.
 * .<p>
 * Implementation of IProductDao interface is annotated for automatic resource injection.
 * </p>
 *
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Transactional
@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao productDao;

    @Override
    public Product saveProduct(Product product) {

        return productDao.saveOrUpdate(product);
    }

    @Override
    public Product saveProductWithImage(Product product, MultipartFile file) throws IOException {
        product.setImageByte(file.getBytes());

        return productDao.saveOrUpdate(product);
    }

    @Transactional(readOnly = true)
    @Override
    public Product getProductById(Long productId) {

        return productDao.findById(productId);
    }

    @Override
    public Product updateProduct(Product product) {
        return productDao.edit(product);
    }

    @Transactional(readOnly = true)
    @Override
    public Product getProductByName(String name) {

        return productDao.getProductByName(name);
    }

    @Transactional(readOnly = true)
    @Override
    public Product getProductByArticleNumber(Long articleNumber) {

        return productDao.getProductByArticleNumber(articleNumber);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> getProductsByCriteria(Long categoryId, FormFilterBean filterParams, SortType sortType, Integer firstResult, Integer maxFetchSize) {

        return productDao.getProductsByCriteria(categoryId, filterParams, sortType, firstResult, maxFetchSize);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> getProductsByCriteria(Long categoryId, FormFilterBean filterParams, SortType sortType) {

        return productDao.getProductsByCriteria(categoryId, filterParams, sortType);
    }

    @Override
    public Integer deleteAllProductByCategoryId(Long categoryId) {

        return productDao.deleteAllProductByCategoryId(categoryId);
    }

    @Override
    public Integer deleteProductById(Long productId) {

        return productDao.deleteProductById(productId);
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
