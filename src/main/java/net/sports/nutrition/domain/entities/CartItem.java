package net.sports.nutrition.domain.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 05.02.2016 20:18
 */

@Entity
@Table(name = "Cart_items")
public class CartItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "taste_id")
    private Taste taste;

    private int quantity;
    private BigDecimal totalPrice;

    public CartItem() {

    }

    public CartItem(Product product, Taste taste) {
        this.setProduct(product);
        this.taste = taste;
        this.setQuantity(1);
        this.setTotalPrice(product.getRealPrice());
    }

    public void setProduct(Product product) {
        this.product = product;
        this.updateTotalPrice();
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.updateTotalPrice();
    }

    public void increaseQuantity() {
        this.quantity++;
        this.updateTotalPrice();

    }

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

        if (!product.equals(cartItem.product)) return false;
        return taste.equals(cartItem.taste);

    }

    @Override
    public int hashCode() {
        int result = product.hashCode();
        result = 31 * result + taste.hashCode();
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
