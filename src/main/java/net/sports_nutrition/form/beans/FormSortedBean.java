package net.sports_nutrition.form.beans;

import net.sports_nutrition.domain.enumx.SortType;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 05.02.2016 1:01
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "session")
public class FormSortedBean implements Serializable {

    private SortType sortType = SortType.NAME_ASC;
    private Boolean productAvailability = false;
    public FormSortedBean() {
    }


    public SortType getSortType() {
        return sortType;
    }

    public void setSortType(SortType sortType) {
        this.sortType = sortType;
    }

    public Boolean getProductAvailability() {
        return productAvailability;
    }

    public void setProductAvailability(Boolean productAvailability) {
        this.productAvailability = productAvailability;
    }

    @Override
    public String toString() {
        return "FormSortedBean{" +
                "sortType=" + sortType +
                ", productAvailability=" + productAvailability +
                '}';
    }
}
