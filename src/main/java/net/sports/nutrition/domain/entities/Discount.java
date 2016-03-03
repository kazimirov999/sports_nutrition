package net.sports.nutrition.domain.entities;


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
@Table(name = "discounts")
public class Discount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "${{field.not.empty.error}}")
    private String name;
    @NotNull(message = "{field.not.empty.error}")
    private BigDecimal size;

    @NotNull(message = "Input correct date!")
    @Column
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime expirationDate;

    @OneToMany(mappedBy = "discount", cascade = {CascadeType.DETACH}, fetch = FetchType.LAZY)
    private Set<Product> productSet = new HashSet<Product>();

    public Discount() {
    }

    public Discount(String name, BigDecimal size, DateTime expirationDate, Set<Product> productSet) {
        this.name = name;
        this.size = size;
        this.expirationDate = expirationDate;
        this.productSet = productSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Discount)) return false;

        Discount discount = (Discount) o;

        if (id != null ? !id.equals(discount.id) : discount.id != null) return false;
        if (name != null ? !name.equals(discount.name) : discount.name != null) return false;
        if (size != null ? !size.equals(discount.size) : discount.size != null) return false;
        return !(expirationDate != null ? !expirationDate.equals(discount.expirationDate) : discount.expirationDate != null);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (size != null ? size.hashCode() : 0);
        result = 31 * result + (expirationDate != null ? expirationDate.hashCode() : 0);
        return result;
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
