package net.sports.nutrition.services;

import net.sports.nutrition.form.beans.FormFilterBean;
import net.sports.nutrition.form.containers.FormFilterContent;
import net.sports.nutrition.form.containers.FormPropertyContent;

/**
 * Service to work with the FormFilter.
 *
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * @see FormFilterContent
 */
public interface IFormService {

    /**
     * Creates content for product add and edit form.
     *
     * @return properties for product form
     */
    FormPropertyContent createContentForProductForm();

    /**
     * Creates content for filter form width amount products for each properties.
     *
     * @param categoryId   - identifier of the category
     * @param filterParams - the search options
     * @return <tt>FormFilterContent</tt>
     * @see IFormService#createContentForFilterFormWithoutAmount(Long)
     */
    FormFilterContent createContentForFilterFormWithAmount(Long categoryId, FormFilterBean filterParams);

    /**
     * Creates content for filter form without amount products for each properties.
     *
     * @param categoryId - identifier of the category
     * @return <tt>FormFilterContent</tt>
     * @see IFormService#createContentForFilterFormWithAmount(Long, FormFilterBean)
     */
    FormFilterContent createContentForFilterFormWithoutAmount(Long categoryId);


}
