package net.sports_nutrition.domain.entities;


import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 07.01.2016 13:04
 */
@NamedQueries({
        @NamedQuery(name = "Discount.getAll",
                query = "SELECT d FROM Discount d ORDER BY d.size"),
        @NamedQuery(name = "Discount.deleteById",
                query = "delete from Discount d where d.id= :id"),
        @NamedQuery(name = "Discount.getDiscountsByCategoryId",
                query = "SELECT d FROM Discount d INNER JOIN d.productSet p WHERE p.category.id = :id ORDER BY d.size ASC ")

})
@Entity
@Table(name = "Discounts")
public class Discount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "${error}")
    private String name;
    @NotNull(message = "Input size!")
    private BigDecimal size;

    @NotNull(message = "Input correct date!")
    @Column
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime expirationDate;

    @OneToMany(mappedBy = "discount", cascade = {CascadeType.DETACH}, fetch = FetchType.LAZY)
    private Set<Product> productSet = new HashSet<Product>();

    public Discount() {
    }

    public Discount(BigDecimal size, DateTime expirationDate, Set<Product> productSet) {
        this.size = size;
        this.expirationDate = expirationDate;
        this.productSet = productSet;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Discount)) return false;

        Discount discount = (Discount) o;

        if (!name.equals(discount.name)) return false;
        return size.equals(discount.size);

    }


    @Override
    public String toString() {
        return "Discount{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", size=" + size +
                ", expirationDate=" + expirationDate +
                // ", productSet=" + productSet +
                '}';
    }

    public int getAroundSize(){
        return size.intValue();
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

    public BigDecimal getSize() {
        return size;
    }

    public void setSize(BigDecimal size) {
        this.size = size;
    }

    public DateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(DateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Set<Product> getProductSet() {
        return productSet;
    }

    public void setProductSet(Set<Product> productSet) {
        this.productSet = productSet;
    }
}
