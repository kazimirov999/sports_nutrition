package net.sports.nutrition.domain.entities;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 19.01.2016 21:31
 */
@NamedQueries({
        @NamedQuery(name = "Taste.getAll",
                query = "SELECT t FROM Taste t ORDER BY t.name ASC "),
        @NamedQuery(name = "Taste.deleteById",
                query = "delete from Taste t where t.id= :id"),
        @NamedQuery(name = "Taste.getAllByCategoryId",
                query = "SELECT taste FROM Product p INNER JOIN p.tasteList taste WHERE p.category.id = :id ORDER BY taste.name ASC")
})
@Entity
@Table(name = "tastes",
        uniqueConstraints=@UniqueConstraint(columnNames={"name"})
)
public class Taste implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Input name!")
    private String name;

    public Taste() {
    }

    public Taste(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Taste)) return false;

        Taste taste = (Taste) o;

        if (id != null ? !id.equals(taste.id) : taste.id != null) return false;
        return !(name != null ? !name.equals(taste.name) : taste.name != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Taste{" +
                "id=" + id +
                ", name='" + name + '\'' +
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
}
