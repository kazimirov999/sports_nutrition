package net.sports_nutrition.services.service_implementations;

import net.sports_nutrition.domain.entities.Taste;
import net.sports_nutrition.domain.repositories.ITasteRepository;
import net.sports_nutrition.services.ITasteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 30.01.2016 17:35
 */

@Transactional
@Service
public class TasteServiceImpl implements ITasteService {

    @Autowired
    private ITasteRepository tasteRepository;

    @Override
    public Taste saveTaste(Taste taste) {
        return tasteRepository.saveOrUpdate(taste);
    }

    @Transactional(readOnly = true)
    @Override
    public Taste getTasteById(Long id) {

        return tasteRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Taste> getAllTastes() {

        return tasteRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Taste getTasteByName(String tasteName) {

        return tasteRepository.getTasteByName(tasteName);
    }

    @Override
    public Integer deleteTasteById(Long tasteId) {

        return tasteRepository.deleteTasteById(tasteId);
    }

    @Transactional(readOnly = true)
    @Override
    public Boolean brandIsExist(Taste taste) {
        Boolean isExist = false;
        if (getTasteByName(taste.getName()) != null) {
            System.out.println("isExist---->>");
            isExist = true;
        }
        return isExist;
    }

    @Transactional(readOnly = true)
    @Override
    public Boolean checkBeforeUpdateTaste(Taste taste) {
        Boolean check = true;
        Taste t = getTasteByName(taste.getName());
        if (t != null && !t.getId().equals(taste.getId()))
            check = false;
        return check;
    }
}
