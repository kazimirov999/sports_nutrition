package net.sports.nutrition.domain.repositories;

import net.sports.nutrition.domain.entities.Taste;

import java.util.List;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 21.01.2016 16:44
 */

public interface ITasteRepository extends IGenericRepository<Taste,Long> {

    List<Taste> getAllTasteByCategoryId(Long categoryId);

    Taste getTasteByName(String tasteName);

    Integer deleteTasteById(Long tasteId);

    List<Taste> findAll();
}
