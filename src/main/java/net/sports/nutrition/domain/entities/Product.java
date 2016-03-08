package net.sports.nutrition.domain.entities;

import net.sports.nutrition.domain.enumx.Form;
import net.sports.nutrition.domain.enumx.Gender;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents Product.
 * <p>
 * It's marked as an entity class, and  provides the ability to store.
 * Product objects in the database and retrieve Discount objects from the database.
 * </p>
 *
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * @see Category
 */
@NamedQueries({
        @NamedQuery(name = "Product.getAllByCategory",
                query = "SELECT p FROM Product p WHERE p.category.id = :id"),
        @NamedQuery(name = "Product.CountByCategory",
                query = "SELECT COUNT(p) FROM Product p WHERE p.category.id = :id"),
        @NamedQuery(name = "Product.deleteByCategoryId",
                query = "delete from Product p where p.category.id= :id"),
        @NamedQuery(name = "Product.deleteById",
                query = "delete from Product p where p.id= :id"),
        @NamedQuery(name = "Product.getProductIdsByTaste",
                query = "SELECT p.id FROM Product p INNER JOIN p.tasteList t WHERE p.category.id = :categoryId AND t.id = :tasteId "),
        @NamedQuery(name = "Product.getProductsIdsByTasteIds",
                query = "SELECT p.id FROM Product p INNER JOIN p.tasteList t WHERE p.category.id = :id AND t.id IN(:tasteIds) ")

})
@Entity
@Table(name = "products",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"articleNumber"})})
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "{error.select.not.be.empty}}")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})//CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "category_id", nullable = true)
    private Category category;

    @NotNull(message = "{error.field.not.empty}")
    @DecimalMin(value = "0", message = "Size is not correct!")
    @DecimalMax(value = "2000000000000000", message = "Size is not correct!")
    private Long articleNumber;

    @Size(min = 3, max = 255, message = "{error.name.product}")
    private String name;

    @NotNull(message = "{error.field.not.empty}")
    @DecimalMin(value = "0", message = "{error.price}")
    @DecimalMax(value = "2000000", message = "{error.price}")
    private BigDecimal price;

    @NotNull(message = "{error.field.not.empty}")
    @DecimalMin(value = "0", message = "{error.size}")
    @DecimalMax(value = "2000000", message = "{error.size}")
    private Integer stockAmount;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String fullDescription;

    @Column(columnDefinition = "TEXT")
    private String composition;

    @NotEmpty(message = "{error.field.not.empty}")
    private String quantityInPackage;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "discount_id", nullable = true, insertable = true, updatable = true)
    private Discount discount;

    @Lob
    @Basic(fetch = FetchType.EAGER)
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] imageByte;

    @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id", nullable = true)
    private Brand brand;

    @NotNull(message = "{error.select.not.be.empty}")
    @Enumerated(EnumType.STRING)
    private Form form;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", nullable = true)
    private List<Video> videoList = new ArrayList<Video>();

    @NotNull(message = "{error.select.not.be.empty}")
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "product_taste_join",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "teste_id")})
    private List<Taste> tasteList = new ArrayList<>();

    @NotNull(message = "{error.select.not.be.empty}")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    /**
     * Create new empty instance of the Product.
     */
    public Product() {
    }

    /**
     * Counts real price of product
     *
     * @return real price of product including discount.
     */
    public BigDecimal getRealPrice() {

        return (discount == null || discount.getSize().equals(0)) ? price :
                (price.subtract((price.multiply(discount.getSize()).divide(new BigDecimal(100)))))
                        .setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        if (id != null ? !id.equals(product.id) : product.id != null) return false;
        if (category != null ? !category.equals(product.category) : product.category != null) return false;
        if (articleNumber != null ? !articleNumber.equals(product.articleNumber) : product.articleNumber != null)
            return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (price != null ? !price.equals(product.price) : product.price != null) return false;
        if (stockAmount != null ? !stockAmount.equals(product.stockAmount) : product.stockAmount != null) return false;
        if (description != null ? !description.equals(product.description) : product.description != null) return false;
        if (fullDescription != null ? !fullDescription.equals(product.fullDescription) : product.fullDescription != null)
            return false;
        if (composition != null ? !composition.equals(product.composition) : product.composition != null) return false;
        if (quantityInPackage != null ? !quantityInPackage.equals(product.quantityInPackage) : product.quantityInPackage != null)
            return false;
        if (discount != null ? !discount.equals(product.discount) : product.discount != null) return false;
        if (form != product.form) return false;
        return gender == product.gender;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (articleNumber != null ? articleNumber.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (stockAmount != null ? stockAmount.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (fullDescription != null ? fullDescription.hashCode() : 0);
        result = 31 * result + (composition != null ? composition.hashCode() : 0);
        result = 31 * result + (quantityInPackage != null ? quantityInPackage.hashCode() : 0);
        result = 31 * result + (discount != null ? discount.hashCode() : 0);
        result = 31 * result + (form != null ? form.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", category=" + category +
                ", articleNumber=" + articleNumber +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stockAmount=" + stockAmount +
                ", description='" + description + '\'' +
                ", fullDescription='" + fullDescription + '\'' +
                ", composition='" + composition + '\'' +
                ", quantityInPackage='" + quantityInPackage + '\'' +
                ", discount=" + discount +
                ", imageByte=" + Arrays.toString(imageByte) +
                ", brand=" + brand +
                ", form=" + form +
                //", videoList=" + videoList +
                ", tasteList=" + tasteList +
                ", gender=" + gender +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(Long articleNumber) {
        this.articleNumber = articleNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStockAmount() {
        return stockAmount;
    }

    public void setStockAmount(Integer stockAmount) {
        this.stockAmount = stockAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public String getQuantityInPackage() {
        return quantityInPackage;
    }

    public void setQuantityInPackage(String quantityInPackage) {
        this.quantityInPackage = quantityInPackage;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public List<Video> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<Video> videoList) {
        this.videoList = videoList;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public byte[] getImageByte() {
        return imageByte;
    }

    public void setImageByte(byte[] imageByte) {
        this.imageByte = imageByte;
    }

    public List<Taste> getTasteList() {
        return tasteList;
    }

    public void setTasteList(List<Taste> tasteList) {
        this.tasteList = tasteList;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
