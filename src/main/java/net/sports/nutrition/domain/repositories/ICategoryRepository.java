package net.sports.nutrition.domain.repositories;

import net.sports.nutrition.domain.entities.Category;

import java.util.List;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 14.01.2016 20:34
 */
public interface ICategoryRepository extends IGenericRepository<Category,Long> {

    List<Category> findAll();

    Integer deleteById(Long id);

    Category getCategoryByName(String name);
}
