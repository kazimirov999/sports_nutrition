package net.sports.nutrition.domain.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Represents the product and its taste and number. It's part of shopping cart.
 * <p>
 * It's marked as an entity class, and  provides the ability to store CartItem
 * objects in the database and retrieve CartItem objects from the database.
 * </p>
 *
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * @see Cart
 */
@Entity
@Table(name = "cart_items")
public class CartItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "taste_id")
    private Taste taste;

    private int quantity;
    private BigDecimal totalPrice;

    /**
     * Creates new instance of the CartItem.
     *
     * @see CartItem#CartItem(Product, Taste)
     */
    public CartItem() {
    }

    /**
     * Creates a new instance of the CartItem, with the specified values.
     *
     * @param product - product is purchased
     * @param taste   - taste of product
     * @see CartItem#CartItem()
     */
    public CartItem(Product product, Taste taste) {
        this.setProduct(product);
        this.taste = taste;
        this.setQuantity(1);
        this.setTotalPrice(product.getRealPrice());
    }

    /**
     * Sets product and updates totalPrice of CartItem
     *
     * @param product - product is purchased
     */
    public void setProduct(Product product) {
        this.product = product;
        this.updateTotalPrice();
    }

    /**
     * Sets quantity and updates totalPrice of CartItem
     *
     * @param quantity - number of product
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.updateTotalPrice();
    }

    /**
     * Increases quantity of product and updates totalPrice of CartItem
     */
    public void increaseQuantity() {
        this.quantity++;
        this.updateTotalPrice();
    }

    /**
     * Decreases quantity of product and updates totalPrice of CartItem
     */
    public void decreaseQuantity() {
        this.quantity--;
        this.updateTotalPrice();
    }

    private void updateTotalPrice() {
        totalPrice = this.product.getRealPrice().multiply(new BigDecimal(quantity));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItem)) return false;

        CartItem cartItem = (CartItem) o;

        if (id != null ? !id.equals(cartItem.id) : cartItem.id != null) return false;
        if (product != null ? !product.equals(cartItem.product) : cartItem.product != null) return false;
        return !(taste != null ? !taste.equals(cartItem.taste) : cartItem.taste != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (product != null ? product.hashCode() : 0);
        result = 31 * result + (taste != null ? taste.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", product=" + product +
                ", taste=" + taste +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public Taste getTaste() {
        return taste;
    }

    public void setTaste(Taste taste) {
        this.taste = taste;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}
