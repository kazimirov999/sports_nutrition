package net.sports.nutrition.form.beans;

import net.sports.nutrition.domain.enumx.SortType;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FormSortedBean)) return false;

        FormSortedBean that = (FormSortedBean) o;

        if (sortType != that.sortType) return false;
        return !(productAvailability != null ? !productAvailability.equals(that.productAvailability) : that.productAvailability != null);

    }

    @Override
    public int hashCode() {
        int result = sortType != null ? sortType.hashCode() : 0;
        result = 31 * result + (productAvailability != null ? productAvailability.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FormSortedBean{" +
                "sortType=" + sortType +
                ", productAvailability=" + productAvailability +
                '}';
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

}
