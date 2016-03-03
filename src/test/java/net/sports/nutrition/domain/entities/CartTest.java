package net.sports.nutrition.domain.entities;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 26.02.2016 18:32
 */
public class CartTest {

    private Cart cart;
    private CartItem cartItemFirst;
    private CartItem cartItemSecond;
    private Product productFirst;
    private Product productSecond;
    private Taste tasteFirst;
    private Taste tasteSecond;

    @Before
    public void init() {
        cart = new Cart();
        tasteFirst = Mockito.mock(Taste.class);
        productFirst = Mockito.mock(Product.class);
        tasteSecond = Mockito.mock(Taste.class);
        productSecond = Mockito.mock(Product.class);

        when(productFirst.getRealPrice()).thenReturn(new BigDecimal(1));
        when(productSecond.getRealPrice()).thenReturn(new BigDecimal(1));

        cartItemFirst = new CartItem(productFirst, tasteFirst);
        cartItemSecond = new CartItem(productSecond, tasteSecond);
    }

    @Test
    public void testAddCartItem() throws Exception {
        cart.addCartItem(cartItemFirst);
        assertEquals(1, cart.getCartItems().size());

        cart.addCartItem(cartItemFirst);
        assertEquals(1, cart.getCartItems().size());
        assertEquals(2, cart.findCartItem(cartItemFirst).getQuantity());

        cart.addCartItem(new CartItem(productFirst, tasteFirst));
        assertEquals(1, cart.getCartItems().size());
        assertEquals(3, cart.findCartItem(cartItemFirst).getQuantity());

        cart.addCartItem(cartItemSecond);
        assertEquals(2, cart.getCartItems().size());

    }

    @Test
    public void testRemoveCartItem() throws Exception {
        cartItemFirst.setQuantity(2);
        cart.addCartItem(cartItemFirst);
        cart.addCartItem(cartItemSecond);

        assertEquals(3, cart.size());

        cart.removeCartItem(cartItemSecond);
        assertNull(cart.findCartItem(cartItemSecond));
        assertEquals(2, cart.size());

        cart.removeCartItem(new CartItem(productFirst, tasteFirst));
        assertEquals(0, cart.size());

    }

    @Test
    public void testIncreaseQuantity() throws Exception {
        cart.addCartItem(cartItemFirst);
        cart.addCartItem(cartItemSecond);

        for (int i = 2; i < 15; i++) {
            cart.increaseQuantity(cartItemFirst);
            cart.increaseQuantity(cartItemSecond);
            assertEquals(i, cart.findCartItem(cartItemFirst).getQuantity());
            assertEquals(i, cart.findCartItem(cartItemSecond).getQuantity());
        }
    }

    @Test
    public void testDecreaseQuantity() throws Exception {
        cart.addCartItem(cartItemFirst);
        cart.addCartItem(cartItemSecond);
        cartItemFirst.setQuantity(15);
        cartItemSecond.setQuantity(15);

        for (int i = 14; i >= 1; i--) {
            cart.decreaseQuantity(cartItemFirst);
            cart.decreaseQuantity(cartItemSecond);
            assertEquals(i, cart.findCartItem(cartItemFirst).getQuantity());
            assertEquals(i, cart.findCartItem(cartItemSecond).getQuantity());
        }
        assertEquals(1, cart.findCartItem(cartItemFirst).getQuantity());
        assertEquals(1, cart.findCartItem(cartItemSecond).getQuantity());

        cart.decreaseQuantity(cartItemFirst);
        cart.decreaseQuantity(cartItemSecond);
        assertEquals(1, cart.findCartItem(cartItemFirst).getQuantity());
        assertEquals(1, cart.findCartItem(cartItemSecond).getQuantity());
    }
}