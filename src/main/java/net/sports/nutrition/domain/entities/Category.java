package net.sports.nutrition.domain.entities;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents category of products.
 * <p>
 * It's marked as an entity class, and  provides the ability to store
 * Category objects in the database and retrieve Category objects from the database.
 * </p>
 *
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@NamedQueries({
        @NamedQuery(name = "Category.deleteById", query = "delete from Category where id= :id"),
})
@Entity
@Table(name = "categories",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotEmpty(message = "{error.field.not.empty}")
    private String name;

    @Column(columnDefinition = "TEXT")
    @NotEmpty(message = "{error.field.not.empty}")
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    public Set<Product> productSet = new HashSet<>();

    @Lob
    @Basic(fetch = FetchType.EAGER)
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] imageByte;

    /**
     * Creates new empty instance of the Category.
     *
     * @see Category#Category(String, String)
     */
    public Category() {
    }

    /**
     * Creates a new instance of the Category with the specified values.
     *
     * @param name        - name of category
     * @param description - description of category
     * @see Country#Country()
     * @see Country
     */
    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;

        Category category = (Category) o;

        if (id != null ? !id.equals(category.id) : category.id != null) return false;
        if (name != null ? !name.equals(category.name) : category.name != null) return false;
        return !(description != null ? !description.equals(category.description) : category.description != null);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Category{" +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Product> getProductSet() {
        return productSet;
    }

    public void setProductSet(Set<Product> productSet) {
        this.productSet = productSet;
    }

    public byte[] getImageByte() {
        return imageByte;
    }

    public void setImageByte(byte[] imageByte) {
        this.imageByte = imageByte;
    }
}
