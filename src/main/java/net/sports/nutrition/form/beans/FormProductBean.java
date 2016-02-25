package net.sports.nutrition.form.beans;

import net.sports.nutrition.domain.entities.Product;
import net.sports.nutrition.utils.validators.file.annotation.File;
import net.sports.nutrition.utils.validators.file.Type;
import net.sports.nutrition.utils.validators.file.annotation.Size;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 30.01.2016 15:44
 */
public class FormProductBean{

    @Valid
    private Product product;

    //@FileNotEmpty(message = "Select file!")
    @File(type = {Type.JPEG,Type.JPG, Type.GIF})
    @Size(min = 4000, max = 1000000, message = "Size should be between {min} - {max} byte")
    private MultipartFile file;

    public FormProductBean(Product product, MultipartFile file) {
        this(product);
        this.file = file;
    }

    public FormProductBean(Product product) {
        this.product = product;
    }

    public FormProductBean() {
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

    @Override
    public String toString() {
        return "FormProductBean{" +
                "producty=" + product +
                ", file=" + file +
                '}';
    }
}
