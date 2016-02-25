package net.sports.nutrition.domain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 07.01.2016 13:04
 */
@NamedQueries({
        @NamedQuery(name = "Country.getAmountProducts",
                query = "SELECT p.brand.country, COUNT(p) FROM Product p GROUP BY p.brand.country.name")
})
@Entity
@Table(name = "Countries")
public class Country implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String name;
    private String iso;


    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private Set<Brand> brandSet = new TreeSet<Brand>();

    public Country() {
    }

    public Country(String name, String iso, Set<Brand> brandSet) {
        this.name = name;
        this.iso = iso;
        this.brandSet = brandSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country)) return false;

        Country country = (Country) o;

        if (!name.equals(country.name)) return false;
        return iso.equals(country.iso);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + iso.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Country{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", iso='" + iso + '\'' +
               // ", brandSet=" + brandSet +
                '}';
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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
