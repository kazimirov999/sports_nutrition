package net.sports.nutrition.domain.repositories;

import net.sports.nutrition.domain.entities.Cart;

import java.util.List;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 06.02.2016 16:11
 */
public interface ICartRepository extends IGenericRepository<Cart,Long> {

    List<Cart> findAll();

    Integer deleteCartById(Long cartId);
}
