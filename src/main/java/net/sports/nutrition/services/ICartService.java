package net.sports.nutrition.services;

import net.sports.nutrition.domain.entities.Cart;

import java.util.List;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 06.02.2016 16:28
 */
public interface ICartService {

    Cart saveCart(Cart cart);

    List<Cart> findAllCarts();

    Cart getCartById(Long cartId);

    Integer deleteCartById(Long cartId);

    Boolean deleteCart(Cart cart);

    String generateBodyTextForSuccessPlaceOrderEmail(String pattern, Cart cart);

    String generateBodyTextForFailurePlaceOrderEmail(String patternStr, Cart cart);
}
