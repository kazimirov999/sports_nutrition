package net.sports.nutrition.form.containers;

import net.sports.nutrition.domain.entities.Taste;
import net.sports.nutrition.domain.entities.Brand;
import net.sports.nutrition.domain.entities.Discount;
import net.sports.nutrition.domain.enumx.Form;
import net.sports.nutrition.domain.enumx.Gender;

import java.io.Serializable;
import java.util.List;

/**
 * Class for storage content of filter to find products.
 *
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
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
