package net.sports.nutrition.services.impl;

import net.sports.nutrition.domain.entities.Cart;
import net.sports.nutrition.domain.repositories.ICartRepository;
import net.sports.nutrition.services.ICartService;
import net.sports.nutrition.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 06.02.2016 16:23
 */

@Transactional
@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private ICartRepository cartRepository;

    @Override
    public Cart saveCart(Cart cart) {

        return cartRepository.saveOrUpdate(cart);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Cart> findAllCarts() {

        return cartRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Cart getCartById(Long cartId) {

        return cartRepository.findById(cartId);
    }

    @Override
    public Boolean deleteCart(Cart cart) {

        return cartRepository.delete(cart);
    }

    @Override
    public String generateBodyTextForSuccessPlaceOrderEmail(String patternStr, Cart cart) {
        StringBuilder str = new StringBuilder(patternStr);
        StringUtil.replaceString(str, "name", cart.getCustomer().getFirstName() + " " + cart.getCustomer().getLastName());
        StringUtil.replaceString(str, "orderId", cart.getOrderId());
        StringUtil.replaceString(str, "totalPrice", cart.getGrandTotalPrice().toString());

        return str.toString();
    }

    @Override
    public String generateBodyTextForFailurePlaceOrderEmail(String patternStr, Cart cart) {
        StringBuilder str = new StringBuilder(patternStr);
        StringUtil.replaceString(str, "name", cart.getCustomer().getFirstName() + " " + cart.getCustomer().getLastName());
        StringUtil.replaceString(str, "orderId", cart.getOrderId());

        return str.toString();
    }

}
