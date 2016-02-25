package net.sports.nutrition.form.containers;

import net.sports.nutrition.domain.entities.Brand;
import net.sports.nutrition.domain.entities.Discount;
import net.sports.nutrition.domain.entities.Taste;
import net.sports.nutrition.domain.enumx.Form;
import net.sports.nutrition.domain.enumx.Gender;

import java.io.Serializable;
import java.util.Map;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 24.01.2016 20:25
 */
public class FormFilterContent implements Serializable {

    private Map<Form, Long> formMap;
    private Map<Gender, Long> genderMap;
    private Map<Brand, Long> brandMap;
    private Map<Taste, Long> tasteMap;
    private Map<Discount, Long> discountMap;

    public FormFilterContent() {
    }

    public Map<Form, Long> getFormMap() {
        return formMap;
    }

    public void setFormMap(Map<Form, Long> formMap) {
        this.formMap = formMap;
    }

    public Map<Gender, Long> getGenderMap() {
        return genderMap;
    }

    public void setGenderMap(Map<Gender, Long> genderMap) {
        this.genderMap = genderMap;
    }

    public Map<Brand, Long> getBrandMap() {
        return brandMap;
    }

    public void setBrandMap(Map<Brand, Long> brandMap) {
        this.brandMap = brandMap;
    }

    public Map<Taste, Long> getTasteMap() {
        return tasteMap;
    }

    public void setTasteMap(Map<Taste, Long> tasteMap) {
        this.tasteMap = tasteMap;
    }

    public Map<Discount, Long> getDiscountMap() {
        return discountMap;
    }

    public void setDiscountMap(Map<Discount, Long> discountMap) {
        this.discountMap = discountMap;
    }


}