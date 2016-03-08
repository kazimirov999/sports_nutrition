package net.sports.nutrition.form.beans;

import net.sports.nutrition.domain.entities.Product;
import net.sports.nutrition.domain.entities.Taste;

/**
 * Class contains information about the product that is bought.
 *
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FormBuyBean)) return false;

        FormBuyBean that = (FormBuyBean) o;

        if (product != null ? !product.equals(that.product) : that.product != null) return false;
        return !(taste != null ? !taste.equals(that.taste) : that.taste != null);

    }

    @Override
    public int hashCode() {
        int result = product != null ? product.hashCode() : 0;
        result = 31 * result + (taste != null ? taste.hashCode() : 0);
        return result;
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
