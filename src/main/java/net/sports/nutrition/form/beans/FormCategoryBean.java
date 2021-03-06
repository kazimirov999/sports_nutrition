package net.sports.nutrition.form.beans;

import net.sports.nutrition.utils.validators.file.annotation.File;
import net.sports.nutrition.utils.validators.file.annotation.Size;
import net.sports.nutrition.domain.entities.Category;
import net.sports.nutrition.utils.validators.file.Type;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.Serializable;

/**
 * Class contains information about the category that is added or edited in database.
 *
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public class FormCategoryBean implements Serializable {

    @Valid
    private Category category;

    @File(type = {Type.JPEG, Type.GIF})
    @Size(min = 4000, max = 1000000, message = "Size should be between {min} - {max} byte")
    private MultipartFile file;

    public FormCategoryBean() {
        category = new Category();
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FormCategoryBean)) return false;

        FormCategoryBean that = (FormCategoryBean) o;

        if (category != null ? !category.equals(that.category) : that.category != null) return false;
        return !(file != null ? !file.equals(that.file) : that.file != null);

    }

    @Override
    public int hashCode() {
        int result = category != null ? category.hashCode() : 0;
        result = 31 * result + (file != null ? file.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FormCategoryBean{" +
                "category=" + category +
                ", file=" + file +
                '}';
    }
}
