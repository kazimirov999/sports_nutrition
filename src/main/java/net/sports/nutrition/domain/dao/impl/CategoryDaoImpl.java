package net.sports.nutrition.domain.dao.impl;

import net.sports.nutrition.domain.entities.Category;
import net.sports.nutrition.domain.dao.ICategoryDao;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The Category Data Access Object is the class providing
 * access to category and category type related data.
 *
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Repository
public class CategoryDaoImpl extends GenericDaoImpl<Category, Long> implements ICategoryDao {

    public CategoryDaoImpl() {
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
