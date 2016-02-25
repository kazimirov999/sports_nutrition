package net.sports_nutrition.services.service_implementations;

import net.sports_nutrition.controllers.OrderController;
import net.sports_nutrition.domain.repositories.IBrandRepository;
import net.sports_nutrition.domain.repositories.IDiscountRepository;
import net.sports_nutrition.domain.repositories.IProductRepository;
import net.sports_nutrition.domain.repositories.ITasteRepository;
import net.sports_nutrition.form.beans.FormFilterBean;
import net.sports_nutrition.form.containers.FormFilterContent;
import net.sports_nutrition.form.containers.FormPropertyContent;
import net.sports_nutrition.services.IFormService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 21.01.2016 22:16
 */

@Transactional(readOnly = true)
@Service
public class FormServiceImpl implements IFormService {

    @Autowired
    private IBrandRepository brandRepository;
    @Autowired
    private IDiscountRepository discountRepository;
    @Autowired
    private ITasteRepository tasteRepository;
    @Autowired
    private IProductRepository productRepository;

    private static final Logger log = org.jboss.logging.Logger.getLogger(OrderController.class);


    public FormServiceImpl() {
    }

    @Override
    public FormPropertyContent createContentForFilterForm(Long categoryId) {
        FormPropertyContent content = new FormPropertyContent();

        content.setBrandList(brandRepository.getBrandByCategoryId(categoryId));
        content.setDiscountList(discountRepository.getDiscountsByCategoryId(categoryId));
        content.setTasteList(tasteRepository.getAllTasteByCategoryId(categoryId));

        return content;
    }

    @Override
    public FormFilterContent createContentForFilterFormWithAmount(Long categoryId, FormFilterBean filterParams) {
        Long start = System.nanoTime();
        FormFilterContent resultContent = new FormFilterContent();
        FormPropertyContent content = createContentForFilterForm(categoryId);

        resultContent.setBrandMap(productRepository.countProductsByProperty("brand", categoryId, content.getBrandList(), filterParams));
        resultContent.setDiscountMap(productRepository.countProductsByProperty("discount", categoryId, content.getDiscountList(), filterParams));
        resultContent.setFormMap(productRepository.countProductsByProperty("form", categoryId, Arrays.asList(content.getForm()), filterParams));
        resultContent.setGenderMap(productRepository.countProductsByProperty("gender", categoryId, Arrays.asList(content.getGender()), filterParams));
        resultContent.setTasteMap(productRepository.countProductsByTasteAndCriteria(categoryId, content.getTasteList(), filterParams));

        log.info("Time counted products by properties: " + (System.nanoTime()-start));

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


    @Override
    public FormPropertyContent createContentForProductForm() {
        FormPropertyContent formPropertyContent = new FormPropertyContent();

        formPropertyContent.setTasteList(tasteRepository.findAll());
        formPropertyContent.setBrandList(brandRepository.findAll());
        formPropertyContent.setDiscountList(discountRepository.findAll());

        return formPropertyContent;
    }

    private <T> Map<T,Long> convertListProductToMap(List<T> elemList){
        Map<T,Long> result = new HashMap<>();
        for(T elem : elemList){
            result.put(elem,new Long(0));
        }
            return result;
    }
}

