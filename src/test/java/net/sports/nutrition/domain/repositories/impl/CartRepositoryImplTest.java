package net.sports.nutrition.domain.repositories.impl;

import net.sports.nutrition.domain.entities.*;
import net.sports.nutrition.domain.repositories.ICartRepository;
import net.sports.nutrition.domain.repositories.IProductRepository;
import net.sports.nutrition.domain.repositories.ITasteRepository;
import org.hibernate.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 27.02.2016 17:57
 */
@Transactional
@ContextConfiguration({"classpath:/test-root-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class CartRepositoryImplTest {

    @Autowired
    private HibernateTemplate hibernateTemplate;
    @Autowired
    private ICartRepository cartRepository;
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private ITasteRepository tasteRepository;

    @Test
    @Rollback(true)//test save mapping and cascade
    public void testFindById() {
        Cart cart = cartRepository.findById(new Long(1));
        assertNotNull(cart);

        Customer customer = cart.getCustomer();
        assertNotNull(customer);
        assertEquals("Victor", customer.getFirstName());

        Set<CartItem> cartItems = cart.getCartItems();
        assertNotNull(cartItems);
        assertEquals(2, cartItems.size());
    }

    @Test
    @Rollback(true)
    public void testFindAll() throws Exception {
        List<Cart> carts = cartRepository.findAll();
        assertNotNull(carts);
        assertEquals(2, carts.size());
        assertEquals("03002967", carts.get(0).getOrderId());
        assertEquals("48025693", carts.get(1).getOrderId());
    }

    @Test
    @Rollback(true)//test save, mapping and cascade
    public void testSave() throws Exception {
        Product product = productRepository.getProductByName("Super BCA");
        Taste taste = tasteRepository.getTasteByName("Chocolate");
        CartItem cartItem = new CartItem(product, taste);
        Set<CartItem> cartItems = new HashSet<>();
        cartItems.add(cartItem);
        Customer customer = new Customer("Nik", "Blumberg", "nick@nk.net", "096555555", "Black str.,81");

        Cart cart = cartRepository.save(new Cart(cartItems, customer));
        assertNotNull(cart);
        assertNotNull(cart.getCartItems());
        assertNotNull(cart.getCartItems().iterator().next().getProduct());
        assertNotNull(cart.getCartItems().iterator().next().getTaste());
        assertNotNull(cart.getCustomer());

        assertEquals("Super BCA", cart.getCartItems().iterator().next().getProduct().getName());
        assertEquals("Chocolate", cart.getCartItems().iterator().next().getTaste().getName());
        assertEquals("Nik", cart.getCustomer().getFirstName());
    }

    @Test
    @Rollback(true)//test delete, mapping and cascade
    public void testDelete() throws Exception {
        Cart cart = cartRepository.findById(new Long(1));
        Boolean result = cartRepository.delete(cart);
        assertTrue(result);

        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        assertNull(cartRepository.findById(new Long(1)));
        assertNull(session.byId(CartItem.class).load(new Long(1)));
        assertNull(session.byId(CartItem.class).load(new Long(2)));
        assertNull(session.byId(Customer.class).load(new Long(1)));
    }
}