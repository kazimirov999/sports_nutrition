package net.sports.nutrition.services;

import net.sports.nutrition.domain.entities.Cart;

import java.util.List;

/**
 * Service to work with the Cart.
 *
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public interface ICartService {

    /**
     * Saves cart.
     *
     * @param cart - cart for save.
     * @return <tt>Cart<tt/> if the action is successful, throw exception  otherwise.
     */
    Cart saveCart(Cart cart);

    /**
     * Returns  all carts.
     *
     * @return <tt>list of Cart</tt> if the carts is exist, <tt>null</tt> otherwise
     */
    List<Cart> findAllCarts();

    /**
     * Returns cart by identifier.
     *
     * @param cartId -  identifier of the Cart.
     * @return <tt>Cart</tt> if the action is successful, <tt>null</tt>  otherwise.
     */
    Cart getCartById(Long cartId);

    /**
     * Deletes an Cart.
     *
     * @param cart - the managed Entity.
     * @return <tt>true</tt> if the action is successful, <tt>false</tt>  otherwise.
     */
    Boolean deleteCart(Cart cart);

    /**
     * Generates the message body for successful ordering
     *
     * @return <tt>text for body message</tt>
     */
    String generateBodyTextForSuccessPlaceOrderEmail(String pattern, Cart cart);

    /**
     * Generates the message body for failure ordering
     *
     * @return <tt>text for body message</tt>
     */
    String generateBodyTextForFailurePlaceOrderEmail(String patternStr, Cart cart);
}
