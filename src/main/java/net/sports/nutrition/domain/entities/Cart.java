package net.sports.nutrition.domain.entities;

import net.sports.nutrition.utils.GeneratorUtil;
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
 * Represents information about buying products(shopping cart).
 * <p>
 * It is marked as an entity class, and  provides the ability to store Cart
 * objects in the database and retrieve Cart objects from the database.
 * Also uses as a regular spring bean, that stored in session.
 * </p>
 *
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "session")
@Entity
@Table(name = "carts")
public class Cart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String orderId;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime date;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id")
    private Set<CartItem> cartItems;

    private BigDecimal grandTotalPrice;

    /**
     * Creates new instance of the Cart.
     *
     * @see Cart#Cart(Set, Customer)
     */
    public Cart() {
        this.orderId = GeneratorUtil.generateId();
        setCartItems(new HashSet<CartItem>());
        setGrandTotalPrice(new BigDecimal(0));
    }

    /**
     * Creates a new instance of the Cart, with the specified values
     *
     * @param cartItems - set of information about buying a product
     * @param customer  - information about the customer
     * @see Cart#Cart()
     * @see CartItem
     * @see Customer
     */
    public Cart(Set<CartItem> cartItems, Customer customer) {
        this.orderId = GeneratorUtil.generateId();
        this.cartItems = cartItems;
        this.customer = customer;
    }

    /**
     * Initializes the object fields by fields of proxy object.
     *
     * @param proxyCart - proxy Cart object, that contains information
     *                  about purchase in current session
     * @return this Cart instance
     */
    public Cart buildByProxy(Cart proxyCart) {
        this.setCustomer(proxyCart.getCustomer());
        this.setCartItems(proxyCart.getCartItems());
        this.setGrandTotalPrice(proxyCart.getGrandTotalPrice());
        this.date = DateTime.now();
        return this;
    }

    /**
     * Search cartItem in cartItems set.
     *
     * @param cartItemSearch - cartItem object for search
     * @return find <tt>cartItem</tt> if exist in set else <tt>null<tt/>
     * @see CartItem
     */
    public CartItem findCartItem(CartItem cartItemSearch) {
        if (cartItems.contains(cartItemSearch)) {
            for (CartItem cartItem : cartItems)
                if (cartItem.equals(cartItemSearch)) {
                    return cartItem;
                }
        }
        return null;
    }

    /**
     * Adds cartItem in  cartItems set and updates grand total price.
     * If the object is already exist in the set, it will not be added,
     * and the quantity field in the existing object will increase by one
     * else item will be added to cartItems Set.
     *
     * @param item - cartItem object for add
     * @see CartItem
     */
    public void addCartItem(CartItem item) {

        if (cartItems.contains(item)) {
            increaseQuantity(item);
        } else {
            cartItems.add(item);
        }
        updateGrandTotal();
    }

    /**
     * Removes cartItem from cartItems set and updates grand total price.
     *
     * @param item - cartItem object for remove
     * @see CartItem
     */
    public void removeCartItem(CartItem item) {
        cartItems.remove(item);
        updateGrandTotal();
    }

    /**
     * Increases the number of product in one and updates grand total price.
     * If item is null then the method do nothing.
     *
     * @param item - cartItem object for increase
     * @see Cart#decreaseQuantity(CartItem)
     * @see CartItem
     */
    public void increaseQuantity(CartItem item) {
        CartItem cartItem = findCartItem(item);
        if (cartItem != null) {
            cartItem.increaseQuantity();
            updateGrandTotal();
        }
    }

    /**
     * Decreases the number of product in one and updates grand total price.
     * If amount product in the shopping cart less than one or item is null
     * then the method do nothing.
     *
     * @param item - cartItem object for increase
     * @see Cart#increaseQuantity(CartItem)
     * @see CartItem
     */
    public void decreaseQuantity(CartItem item) {
        CartItem cartItem = findCartItem(item);
        if (cartItem != null) {
            if (cartItem.getQuantity() > 1) {
                cartItem.decreaseQuantity();
                updateGrandTotal();
            }
        }
    }

    /**
     * Cleans shopping cart
     */
    public void cleanCart() {
        this.cartItems = null;
        setCartItems(new HashSet<CartItem>());
    }

    /**
     * Counts the number of products in the shopping cart
     *
     * @return the number of products in the shopping cart
     */
    public int size() {
        int count = 0;
        for (CartItem cartItem : cartItems)
            count += cartItem.getQuantity();
        return count;
    }

    private void updateGrandTotal() {
        grandTotalPrice = new BigDecimal(0);
        for (CartItem item : cartItems)
            grandTotalPrice = grandTotalPrice.add(item.getTotalPrice());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart)) return false;

        Cart cart = (Cart) o;

        if (id != null ? !id.equals(cart.id) : cart.id != null) return false;
        if (orderId != null ? !orderId.equals(cart.orderId) : cart.orderId != null) return false;
        if (date != null ? !date.equals(cart.date) : cart.date != null) return false;
        return !(customer != null ? !customer.equals(cart.customer) : cart.customer != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (orderId != null ? orderId.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        return result;
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
