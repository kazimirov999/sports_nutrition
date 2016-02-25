package net.sports_nutrition.domain.repositories.repository_imlementations;

        import net.sports_nutrition.domain.entities.Taste;
        import net.sports_nutrition.domain.repositories.ITasteRepository;
        import org.hibernate.criterion.Restrictions;
        import org.springframework.stereotype.Repository;

        import java.util.List;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 21.01.2016 16:45
 */
@Repository
public class TasteRepositoryImpl extends GenericRepositoryImpl<Taste, Long> implements ITasteRepository {

    public TasteRepositoryImpl() {
        super(Taste.class);
    }

    @Override
    public List<Taste> getAllTasteByCategoryId(Long categoryId) {

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
