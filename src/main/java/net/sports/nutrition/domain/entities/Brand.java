package net.sports.nutrition.domain.entities;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 07.01.2016 13:03
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
@Table(name = "Brands",
        uniqueConstraints=@UniqueConstraint(columnNames={"name"})
)
public class Brand implements Comparable, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "{field.not.empty.error}")
    private String name;
    private String description;


    @NotNull(message = "{field.not.empty.error}")
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id", nullable = true)
    private Country country;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private Set<Product> productSet = new HashSet<Product>();

    public Brand() {
    }

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

        return name.equals(brand.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", country=" + country +
                // ", productSet=" + productSet +
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

    public int compareTo(Object o) {
        return 0;
    }
}
