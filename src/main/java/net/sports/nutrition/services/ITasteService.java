package net.sports.nutrition.services;

import net.sports.nutrition.domain.entities.Taste;

import java.util.List;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 30.01.2016 17:35
 */
public interface ITasteService {

    Taste saveTaste(Taste taste);

    Taste getTasteById(Long id);

    List<Taste> getAllTastes();

    Taste getTasteByName(String tasteName);

    Integer deleteTasteById(Long tasteId);

    Boolean brandIsExist(Taste taste);

    Boolean checkBeforeUpdateTaste(Taste taste);
}
