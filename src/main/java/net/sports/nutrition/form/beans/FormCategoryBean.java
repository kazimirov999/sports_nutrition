package net.sports.nutrition.form.beans;

import net.sports.nutrition.utils.validators.file.annotation.File;
import net.sports.nutrition.utils.validators.file.annotation.Size;
import net.sports.nutrition.domain.entities.Category;
import net.sports.nutrition.utils.validators.file.Type;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.Serializable;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 27.01.2016 15:49
 */
public class FormCategoryBean implements Serializable {

    @Valid
    private Category category;

    @File(type = {Type.JPEG, Type.GIF})
    @Size(min = 4000, max = 1000000, message = "Size should be between {min} - {max} byte")
    private MultipartFile file;

    public FormCategoryBean() {
    }

    public FormCategoryBean(Category category, MultipartFile file) {
        this(category);
        this.file = file;
    }

    public FormCategoryBean(Category category) {
        this.category = category;

    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "FormCategoryBean{" +
                "category=" + category +
                ", file=" + file +
                '}';
    }
}
