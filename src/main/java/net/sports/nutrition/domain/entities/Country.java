package net.sports.nutrition.domain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

/**
 * Represents Country.
 * <p>
 * It's marked as an entity class, and  provides the ability to store
 * Country objects in the database and retrieve Country objects from the database.
 * </p>
 *
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@NamedQueries({
        @NamedQuery(name = "Country.getAmountProducts",
                query = "SELECT p.brand.country, COUNT(p) FROM Product p GROUP BY p.brand.country.name")
})
@Entity
@Table(name = "countries")
public class Country implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String iso;


    @OneToMany(mappedBy = "country", cascade = CascadeType.DETACH)
    private Set<Brand> brandSet = new TreeSet<Brand>();

    /**
     * Creates new empty instance of the Country.
     *
     * @see Country#Country(String, String)
     */
    public Country() {
    }

    /**
     * Creates a new instance of the Country with the specified values.
     *
     * @param name - name of category
     * @param iso  - iso of country
     * @see Country#Country()
     */
    public Country(String name, String iso) {
        this.name = name;
        this.iso = iso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country)) return false;

        Country country = (Country) o;

        if (id != null ? !id.equals(country.id) : country.id != null) return false;
        if (name != null ? !name.equals(country.name) : country.name != null) return false;
        return !(iso != null ? !iso.equals(country.iso) : country.iso != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (iso != null ? iso.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Country{" +
                "Id=" + id +
                ", name='" + name + '\'' +
                ", iso='" + iso + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public Set<Brand> getBrandSet() {
        return brandSet;
    }

    public void setBrandSet(Set<Brand> brandSet) {
        this.brandSet = brandSet;
    }
}
