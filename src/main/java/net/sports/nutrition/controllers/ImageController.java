package net.sports.nutrition.controllers;

import net.sports.nutrition.constants.ConstantsUri;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * The Controller is responsible for processing  requests
 * related to image.
 *
 * @author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Controller
public class ImageController extends AbstractGlobalController {

    /**
     * Writes image of category to the response stream.
     *
     * @param categoryId - category id
     * @return byte array
     */
    @ResponseBody
    @RequestMapping(value = ConstantsUri.CATEGORY_IMG)
    public byte[] downloadCategoryPhoto(@PathVariable("categoryId") Long categoryId) {

        return categoryService.getCategoryById(categoryId).getImageByte();
    }

    /**
     * Writes image of product to the response stream.
     *
     * @param productId - product id
     * @return byte array
     */
    @ResponseBody
    @RequestMapping(value = ConstantsUri.PRODUCT_IMG)
    public byte[] downloadProductPhoto(@PathVariable("productId") Long productId) {

        return productService.getProductById(productId).getImageByte();
    }

}