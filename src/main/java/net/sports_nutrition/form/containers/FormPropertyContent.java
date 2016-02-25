package net.sports_nutrition.form.containers;

import net.sports_nutrition.domain.entities.Brand;
import net.sports_nutrition.domain.entities.Discount;
import net.sports_nutrition.domain.entities.Taste;
import net.sports_nutrition.domain.enumx.Form;
import net.sports_nutrition.domain.enumx.Gender;

import java.io.Serializable;
import java.util.List;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 21.01.2016 21:19
 */

public class FormPropertyContent implements Serializable {

    private Form form;
    private Gender gender;
    private List<Brand> brandList;
    private List<Taste> tasteList;
    private List<Discount> discountList;

    public FormPropertyContent() {
    }

    public FormPropertyContent(List<Brand> brandList, List<Taste> tasteList, List<Discount> discountList) {
        this.brandList = brandList;
        this.tasteList = tasteList;
        this.discountList = discountList;
    }

    public Gender[] getGender() {
        return gender.values();
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Form[] getForm() {
        return form.values();
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public List<Brand> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<Brand> brandList) {
        this.brandList = brandList;
    }

    public List<Taste> getTasteList() {
        return tasteList;
    }

    public void setTasteList(List<Taste> tasteList) {
        this.tasteList = tasteList;
    }

    public List<Discount> getDiscountList() {
        return discountList;
    }

    public void setDiscountList(List<Discount> discountList) {
        this.discountList = discountList;
    }
}
