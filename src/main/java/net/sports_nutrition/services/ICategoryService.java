package net.sports_nutrition.services;

import net.sports_nutrition.domain.entities.Category;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 25.01.2016 14:07
 */
public interface ICategoryService {

    Integer deleteCategoryById(Long id);

    void saveCategory(Category category);

    Category getCategoryById(Long id);

    void saveCategoryWithImage(Category category, MultipartFile file) throws IOException;

    Category updateCategory(Category category);

    List<Category> findAllCategories();

    Category getCategoryByName(String name);

    Boolean categoryIsExist(Category category);

    Boolean checkBeforeUpdateCategory(Category category);

    List<Category> getRandomCategories(int amountCategory);
}
