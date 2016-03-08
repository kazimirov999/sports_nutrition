package net.sports.nutrition.form.beans;

import net.sports.nutrition.domain.entities.Product;
import net.sports.nutrition.utils.validators.file.Type;
import net.sports.nutrition.utils.validators.file.annotation.File;
import net.sports.nutrition.utils.validators.file.annotation.Size;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

/**
 * Class contains information about the product that is added or edited in database.
 *
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public class FormProductBean {

    @Valid
    private Product product;

    //@FileNotEmpty(message = "Select file!")
    @File(type = {Type.JPEG, Type.JPG, Type.GIF})
    @Size(min = 4000, max = 1000000, message = "Size should be between {min} - {max} byte")
    private MultipartFile file;

    /**
     * Creates a new empty instance of the FormProductBean.
     */
    public FormProductBean() {
    }

    /**
     * Creates a new instance of the FormProductBean with the specified values.
     *
     * @param product - product
     * @param file    - image of the product
     */
    public FormProductBean(Product product, MultipartFile file) {
        this(product);
        this.file = file;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FormProductBean)) return false;

        FormProductBean that = (FormProductBean) o;

        return !(product != null ? !product.equals(that.product) : that.product != null);
    }

    @Override
    public int hashCode() {
        return product != null ? product.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "FormProductBean{" +
                "producty=" + product +
                ", file=" + file +
                '}';
    }

    public FormProductBean(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }


}
