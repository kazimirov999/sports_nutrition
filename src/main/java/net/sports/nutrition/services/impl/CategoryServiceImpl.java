package net.sports.nutrition.services.impl;

import net.sports.nutrition.domain.dao.ICategoryDao;
import net.sports.nutrition.services.ICategoryService;
import net.sports.nutrition.domain.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


/**
 * Service to work with the Category, using ICategoryDao.
 * .<p>
 * Implementation of ICategoryDao interface is annotated for automatic resource injection.
 * </p>
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Transactional
@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryDao categoryDao;

    @Override
    public Integer deleteCategoryById(Long id) {

        return categoryDao.deleteById(id);
    }

    @Override
    public void saveCategory(Category category) {
        categoryDao.save(category);
    }

    @Transactional(readOnly = true)
    @Override
    public Category getCategoryById(Long id) {

        return categoryDao.findById(id);
    }

    @Override
    public void saveCategoryWithImage(Category category, MultipartFile file) throws IOException {
        category.setImageByte(file.getBytes());
        saveCategory(category);
    }

    @Override
    public Category updateCategory(Category category) {

        return categoryDao.edit(category);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Category> findAllCategories() {

        return categoryDao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Category getCategoryByName(String name) {

        return categoryDao.getCategoryByName(name);
    }

    @Transactional(readOnly = true)
    @Override
    public Boolean categoryIsExist(Category category) {
        return getCategoryByName(category.getName()) != null;
    }

    @Transactional(readOnly = true)
    @Override
    public Boolean checkBeforeUpdateCategory(Category category) {
        if (category == null && category.getId() == null) {
            return false;
        }
        Boolean check = true;
        Category cat = getCategoryByName(category.getName());
        if (cat != null && !cat.getId().equals(category.getId())) {
            check = false;
        }

        return check;
    }
}
