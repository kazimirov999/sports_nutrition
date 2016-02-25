package net.sports_nutrition.form.beans;

import net.sports_nutrition.domain.entities.Product;
import net.sports_nutrition.domain.entities.Taste;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 06.02.2016 12:57
 */
public class FormBuyBean {

    private Product product;
    private Taste taste;

    public FormBuyBean() {
    }

    public FormBuyBean(Product product, Taste taste) {
        this.product = product;
        this.taste = taste;
    }

    @Override
    public String toString() {
        return "FormBuyBean{" +
                "product=" + product +
                ", taste=" + taste +
                '}';
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Taste getTaste() {
        return taste;
    }

    public void setTaste(Taste taste) {
        this.taste = taste;
    }
}
