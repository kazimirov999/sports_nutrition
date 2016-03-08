package net.sports.nutrition.domain.dao.impl;

        import net.sports.nutrition.domain.entities.Taste;
        import net.sports.nutrition.domain.dao.ITasteDao;
        import org.hibernate.criterion.Restrictions;
        import org.springframework.stereotype.Repository;

        import java.util.List;

/**
 * The Taste Data Access Object is the class providing
 * access to taste and taste type related data.
 *
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Repository
public class TasteDaoImpl extends GenericDaoImpl<Taste, Long> implements ITasteDao {

    public TasteDaoImpl() {
        super(Taste.class);
    }

    @Override
    public List<Taste> getAllTastesByCategoryId(Long categoryId) {

        return (List<Taste>) getHibernateTemplate()
                .findByNamedQueryAndNamedParam("Taste.getAllByCategoryId", "id", categoryId);
    }

    @Override
    public Taste getTasteByName(String tasteName) {

        return (Taste) getSession()
                .createCriteria(getType())
                .add(Restrictions.eq("name", tasteName))
                .uniqueResult();
    }

    @Override
    public Integer deleteTasteById(Long tasteId) {

        return getSession()
                .getNamedQuery("Taste.deleteById")
                .setLong("id", tasteId)
                .executeUpdate();
    }

    @Override
    public List<Taste> findAll() {

        return getSession().createCriteria(getType()).list();
    }

}
