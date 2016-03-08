package net.sports.nutrition.domain.entities;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents brand of products.
 * <p>
 * It's marked as an entity class, and  provides the ability to store
 * Brand objects in the database and retrieve Brand objects from the database.
 * </p>
 *
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * @see CartItem
 */
@NamedQueries({
        @NamedQuery(name = "Brand.getAll",
                query = "SELECT b FROM Brand b ORDER BY b.name ASC"),
        @NamedQuery(name = "Brand.deleteById",
                query = "delete from Brand b where b.id= :id"),
        @NamedQuery(name = "Brand.getAllByCategoryIdSortByName",
                query = "SELECT DISTINCT(b) FROM Brand b INNER JOIN b.productSet p  WHERE p.category.id = :id ORDER BY b.name ASC "),
        @NamedQuery(name = "Brand.getCountProducts",
                query = "SELECT p.brand, COUNT(p) FROM Product p GROUP BY p.brand.name ORDER BY p.brand.name ASC"),
        @NamedQuery(name = "Brand.getByCountry",
                query = "SELECT b FROM Brand b WHERE b.country.id = :id ORDER BY b.name ASC")
})
@Entity
@Table(name = "brands",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name"})
)
public class Brand implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "{field.not.empty.error}")
    private String name;
    private String description;

    @NotNull(message = "{field.not.empty.error}")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id", nullable = true)
    private Country country;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private Set<Product> productSet = new HashSet<Product>();

    /**
     * Creates new empty instance of the Brand
     *
     * @see Brand#Brand(String, Country, String)
     */
    public Brand() {
    }

    /**
     * Creates a new instance of the Brand with the specified values
     *
     * @param name        - name
     * @param country     - producing country
     * @param description - description
     * @see Brand#Brand()
     * @see Country
     */
    public Brand(String name, Country country, String description) {
        this.name = name;
        this.country = country;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Brand)) return false;

        Brand brand = (Brand) o;

        if (id != null ? !id.equals(brand.id) : brand.id != null) return false;
        if (name != null ? !name.equals(brand.name) : brand.name != null) return false;
        if (description != null ? !description.equals(brand.description) : brand.description != null) return false;
        return !(country != null ? !country.equals(brand.country) : brand.country != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", country=" + country +
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Set<Product> getProductSet() {
        return productSet;
    }

    public void setProductSet(Set<Product> productSet) {
        this.productSet = productSet;
    }

}
