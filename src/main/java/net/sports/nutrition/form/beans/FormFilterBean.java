package net.sports.nutrition.form.beans;

import net.sports.nutrition.domain.enumx.Form;
import net.sports.nutrition.domain.enumx.Gender;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 21.01.2016 23:55
 */

public class FormFilterBean implements Serializable{
     final static private String MESSAGE = "error.input.integer.filter";

    @Digits(message = MESSAGE , integer = 2000, fraction = 100000)
    @Min(value = 0, message ="error.input.integer.filter")
    @Max(value = 10000000, message = MESSAGE)
    private BigDecimal lowPrice ;

    @Digits(message = MESSAGE, integer = 2000, fraction = 100000)
    @Min(value = 0, message ="error.input.integer.filter")
    @Max(value = 10000000, message = MESSAGE)
    private BigDecimal highPrice ;
    private Set<Long> brandIdList;
    private Set<Long> tasteIdList;
    private Set<Long> discountIdList;
    private Set<Form> formList;
    private Set<Gender> genderList;

    public FormFilterBean() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FormFilterBean)) return false;

        FormFilterBean that = (FormFilterBean) o;

        if (lowPrice != null ? !lowPrice.equals(that.lowPrice) : that.lowPrice != null) return false;
        if (highPrice != null ? !highPrice.equals(that.highPrice) : that.highPrice != null) return false;
        if (brandIdList != null ? !brandIdList.equals(that.brandIdList) : that.brandIdList != null) return false;
        if (tasteIdList != null ? !tasteIdList.equals(that.tasteIdList) : that.tasteIdList != null) return false;
        if (discountIdList != null ? !discountIdList.equals(that.discountIdList) : that.discountIdList != null)
            return false;
        if (formList != null ? !formList.equals(that.formList) : that.formList != null) return false;
        return !(genderList != null ? !genderList.equals(that.genderList) : that.genderList != null);

    }

    @Override
    public int hashCode() {
        int result = lowPrice != null ? lowPrice.hashCode() : 0;
        result = 31 * result + (highPrice != null ? highPrice.hashCode() : 0);
        result = 31 * result + (brandIdList != null ? brandIdList.hashCode() : 0);
        result = 31 * result + (tasteIdList != null ? tasteIdList.hashCode() : 0);
        result = 31 * result + (discountIdList != null ? discountIdList.hashCode() : 0);
        result = 31 * result + (formList != null ? formList.hashCode() : 0);
        result = 31 * result + (genderList != null ? genderList.hashCode() : 0);
        return result;
    }

    public Set<Form> getFormList() {
        return formList;
    }

    public void setFormList(Set<Form> formList) {
        this.formList = formList;
    }

    public Set<Gender> getGenderList() {
        return genderList;
    }

    public void setGenderList(Set<Gender> genderList) {
        this.genderList = genderList;
    }

    public static String getMESSAGE() {
        return MESSAGE;
    }

    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(BigDecimal lowPrice) {
        this.lowPrice = lowPrice;
    }

    public BigDecimal getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(BigDecimal highPrice) {
        this.highPrice = highPrice;
    }

    public Set<Long> getBrandIdList() {
        return brandIdList;
    }

    public void setBrandIdList(Set<Long> brandIdList) {
        this.brandIdList = brandIdList;
    }

    public Set<Long> getTasteIdList() {
        return tasteIdList;
    }

    public void setTasteIdList(Set<Long> tasteIdList) {
        this.tasteIdList = tasteIdList;
    }

    public Set<Long> getDiscountIdList() {
        return discountIdList;
    }

    public void setDiscountIdList(Set<Long> discountIdList) {
        this.discountIdList = discountIdList;
    }



    @Override
    public String toString() {
        return "FormFilterBean{" +
                "lowPrice=" + lowPrice +
                ", highPrice=" + highPrice +
                ", brandIdList=" + brandIdList +
                ", tasteIdList=" + tasteIdList +
                ", discountIdList=" + discountIdList +
                ", formList=" + formList +
                ", genderList=" + genderList +
                '}';
    }
}
