package net.sports.nutrition.services.impl;

import net.sports.nutrition.domain.entities.Taste;
import net.sports.nutrition.domain.dao.ITasteDao;
import net.sports.nutrition.services.ITasteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service to work with the Taste, using ITasteDao.
 * .<p>
 * Implementation of ITasteDao interface is annotated for automatic resource injection.
 * </p>
 *
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Transactional
@Service
public class TasteServiceImpl implements ITasteService {

    @Autowired
    private ITasteDao tasteDao;

    @Override
    public Taste saveTaste(Taste taste) {
        return tasteDao.saveOrUpdate(taste);
    }

    @Transactional(readOnly = true)
    @Override
    public Taste getTasteById(Long id) {

        return tasteDao.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Taste> findAllTastes() {

        return tasteDao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Taste getTasteByName(String tasteName) {

        return tasteDao.getTasteByName(tasteName);
    }

    @Override
    public Integer deleteTasteById(Long tasteId) {

        return tasteDao.deleteTasteById(tasteId);
    }

    @Transactional(readOnly = true)
    @Override
    public Boolean tasteIsExist(Taste taste) {
        Boolean isExist = false;
        if (getTasteByName(taste.getName()) != null) {
            isExist = true;
        }

        return isExist;
    }

    @Transactional(readOnly = true)
    @Override
    public Boolean checkBeforeUpdateTaste(Taste taste) {
        Boolean check = true;
        Taste t = getTasteByName(taste.getName());
        if (t != null && !t.getId().equals(taste.getId())) {
            check = false;
        }

        return check;
    }
}
