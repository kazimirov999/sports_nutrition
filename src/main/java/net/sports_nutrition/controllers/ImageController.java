package net.sports_nutrition.controllers;

import net.sports_nutrition.constants.ConstantsUri;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 10.02.2016 17:25
 */

@Controller
public class ImageController extends AbstractGlobalController {

    @ResponseBody
    @RequestMapping(value = ConstantsUri.CATEGORY_IMG)
    public byte[] downloadCategoryPhoto(@PathVariable("categoryId") Long categoryId) {

        return categoryService.getCategoryById(categoryId).getImageByte();
    }

    @ResponseBody
    @RequestMapping(value = ConstantsUri.PRODUCT_IMG)
    public byte[] downloadProductPhoto(@PathVariable("productId") Long productId) {

        return productService.getProductById(productId).getImageByte();
    }

}