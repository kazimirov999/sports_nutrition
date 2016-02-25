package net.sports_nutrition.domain.entities;

import net.sports_nutrition.utils.GeneratorUtil;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 05.02.2016 20:14
 */

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "session")
@Entity
@Table(name = "Carts")
public class Cart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String orderId;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime date;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="cart_id")
    private Set<CartItem> cartItems;

    private BigDecimal grandTotalPrice;

    public Cart() {
        setCartItems(new HashSet<CartItem>());
        setGrandTotalPrice(new BigDecimal(0));
    }

    public Cart buildByProxy(Cart proxyCart){
        this.setCustomer(proxyCart.getCustomer());
        this.setCartItems(proxyCart.getCartItems());
        this.setGrandTotalPrice(proxyCart.getGrandTotalPrice());
        this.orderId = GeneratorUtil.generateId();
        this.date = DateTime.now();
        return this;
    }

    public void addCartItem(CartItem item) {

        if (cartItems.contains(item)) {
            for (CartItem cartItem : cartItems)
                if (cartItem.equals(item)) {
                    cartItem.setQuantity(cartItem.getQuantity() + item.getQuantity());
                    break;
                }
        } else {
            cartItems.add(item);
        }

        updateGrandTotal();
    }

    public void removeCartItem(CartItem item) {
        cartItems.remove(item);
        updateGrandTotal();
    }

    public void increaseQuantity(CartItem item) {
        if (cartItems.contains(item))
            for (CartItem cartItem : cartItems)
                if (cartItem.equals(item)) {
                    cartItem.increaseQuantity();
                    updateGrandTotal();
                    break;
                }
    }

    public void decreaseQuantity(CartItem item) {
        if (cartItems.contains(item))
            for (CartItem cartItem : cartItems)
                if (cartItem.equals(item)) {
                    if (cartItem.getQuantity() > 1) {
                        cartItem.decreaseQuantity();
                        updateGrandTotal();
                        break;
                    }
                }
    }

    public void cleanCart() {
        this.cartItems = null;
        setCartItems(new HashSet<CartItem>());
    }

    public int size(){
        int count = 0;
        for (CartItem cartItem:cartItems)
            count += cartItem.getQuantity();
        return count;
    }

    private void updateGrandTotal() {
        grandTotalPrice = new BigDecimal(0);
        for (CartItem item : cartItems)
            grandTotalPrice = grandTotalPrice.add(item.getTotalPrice());
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", date=" + date +
                ", customer=" + customer +
                ", cartItems=" + cartItems +
                ", grandTotalPrice=" + grandTotalPrice +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public BigDecimal getGrandTotalPrice() {
        return grandTotalPrice;
    }

    public void setGrandTotalPrice(BigDecimal grandTotal) {
        this.grandTotalPrice = grandTotal;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }
}
