package net.sports.nutrition.services.impl;

import net.sports.nutrition.controllers.OrderController;
import net.sports.nutrition.domain.dao.IBrandDao;
import net.sports.nutrition.domain.dao.IDiscountDao;
import net.sports.nutrition.domain.dao.IProductDao;
import net.sports.nutrition.domain.dao.ITasteDao;
import net.sports.nutrition.form.containers.FormPropertyContent;
import net.sports.nutrition.services.IFormService;
import net.sports.nutrition.form.beans.FormFilterBean;
import net.sports.nutrition.form.containers.FormFilterContent;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service to work with the FormFilter.
 * .<p>
 * Implementations of IDiscountDao, IBrandDao, ITasteDao, IProductDao
 * interface is annotated for automatic resource injection.
 * Class build content of filter to find products.
 * </p>
 *
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * @see FormFilterContent
 */
@Transactional(readOnly = true)
@Service
public class FormServiceImpl implements IFormService {

    @Autowired
    private IBrandDao brandDao;
    @Autowired
    private IDiscountDao discountDao;
    @Autowired
    private ITasteDao tasteDao;
    @Autowired
    private IProductDao productDao;

    private static final Logger log = org.jboss.logging.Logger.getLogger(OrderController.class);

    @Override
    public FormPropertyContent createContentForProductForm() {
        FormPropertyContent formPropertyContent = new FormPropertyContent();

        formPropertyContent.setTasteList(tasteDao.findAll());
        formPropertyContent.setBrandList(brandDao.findAll());
        formPropertyContent.setDiscountList(discountDao.findAll());

        return formPropertyContent;
    }

    @Override
    public FormFilterContent createContentForFilterFormWithAmount(Long categoryId, FormFilterBean filterParams) {
        Long start = System.nanoTime();
        FormFilterContent resultContent = new FormFilterContent();
        FormPropertyContent content = createContentForFilterForm(categoryId);

        resultContent.setBrandMap(productDao.countProductsByProperty("brand", categoryId, content.getBrandList(), filterParams));
        resultContent.setDiscountMap(productDao.countProductsByProperty("discount", categoryId, content.getDiscountList(), filterParams));
        resultContent.setFormMap(productDao.countProductsByProperty("form", categoryId, Arrays.asList(content.getForm()), filterParams));
        resultContent.setGenderMap(productDao.countProductsByProperty("gender", categoryId, Arrays.asList(content.getGender()), filterParams));
        resultContent.setTasteMap(productDao.countProductsByTasteAndCriteria(categoryId, content.getTasteList(), filterParams));

        log.info("Time counted products by properties: " + (System.nanoTime() - start));

        return resultContent;
    }

    @Override
    public FormFilterContent createContentForFilterFormWithoutAmount(Long categoryId) {
        FormFilterContent resultContent = new FormFilterContent();
        FormPropertyContent content = createContentForFilterForm(categoryId);

        resultContent.setBrandMap(convertListProductToMap(content.getBrandList()));
        resultContent.setDiscountMap(convertListProductToMap(content.getDiscountList()));
        resultContent.setFormMap(convertListProductToMap(Arrays.asList(content.getForm())));
        resultContent.setGenderMap(convertListProductToMap(Arrays.asList(content.getGender())));

        resultContent.setTasteMap(convertListProductToMap(content.getTasteList()));

        return resultContent;
    }

    private FormPropertyContent createContentForFilterForm(Long categoryId) {
        FormPropertyContent content = new FormPropertyContent();

        content.setBrandList(brandDao.getBrandsByCategoryId(categoryId));
        content.setDiscountList(discountDao.getDiscountsByCategoryId(categoryId));
        content.setTasteList(tasteDao.getAllTastesByCategoryId(categoryId));

        return content;
    }

    private <T> Map<T, Long> convertListProductToMap(List<T> elemList) {
        Map<T, Long> result = new HashMap<>();
        for (T elem : elemList) {
            result.put(elem, new Long(0));
        }
        return result;
    }
}

