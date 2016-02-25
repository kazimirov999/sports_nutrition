package net.sports_nutrition.services.service_implementations;

import net.sports_nutrition.domain.entities.Category;
import net.sports_nutrition.domain.repositories.ICategoryRepository;
import net.sports_nutrition.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 25.01.2016 14:07
 */

@Transactional
@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public Integer deleteCategoryById(Long id) {

        return categoryRepository.deleteById(id);
    }

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    @Transactional(readOnly = true)
    @Override
    public Category getCategoryById(Long id) {

        return categoryRepository.findById(id);
    }

    @Override
    public void saveCategoryWithImage(Category category, MultipartFile file) throws IOException {
        category.setImageByte(file.getBytes());
        saveCategory(category);
    }

    @Override
    public Category updateCategory(Category category) {

        return categoryRepository.edit(category);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Category> findAllCategories() {

        return categoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Category getCategoryByName(String name) {

        return categoryRepository.getCategoryByName(name);
    }

    @Transactional(readOnly = true)
    @Override
    public Boolean categoryIsExist(Category category) {
        return getCategoryByName(category.getName()) != null;
    }

    @Transactional(readOnly = true)
    @Override
    public Boolean checkBeforeUpdateCategory(Category category) {
        if (category == null && category.getId() == null) return false;
        Boolean check = true;
        Category cat = getCategoryByName(category.getName());
        if (cat != null && !cat.getId().equals(category.getId()))
            check = false;

        return check;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Category> getRandomCategories(int amountCategory) {

        return categoryRepository.getRandomCategories(amountCategory);
    }
}
