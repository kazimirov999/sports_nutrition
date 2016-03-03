package net.sports.nutrition.domain.repositories.impl;

import net.sports.nutrition.domain.entities.Category;
import net.sports.nutrition.domain.repositories.ICategoryRepository;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 14.01.2016 20:36
 */
@Repository
public class CategoryRepositoryImpl extends GenericRepositoryImpl<Category, Long> implements ICategoryRepository {

    public CategoryRepositoryImpl() {
        super(Category.class);
    }

    @Override
    public List<Category> findAll() {

        return getSession().createCriteria(getType()).list();
    }

    @Override
    public Integer deleteById(Long id) {

        return getSession()
                .getNamedQuery("Category.deleteById")
                .setLong("id", id)
                .executeUpdate();
    }

    @Override
    public Category getCategoryByName(String name) {

        return (Category) getSession()
                .createCriteria(getType())
                .add(Restrictions.eq("name", name))
                .uniqueResult();
    }
}
