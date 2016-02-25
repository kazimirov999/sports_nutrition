package net.sports_nutrition.services;

import net.sports_nutrition.form.beans.FormFilterBean;
import net.sports_nutrition.form.containers.FormPropertyContent;
import net.sports_nutrition.form.containers.FormFilterContent;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 21.01.2016 22:28
 */
public interface IFormService {

   public FormPropertyContent createContentForFilterForm(Long categoryId);

   public FormFilterContent createContentForFilterFormWithAmount(Long categoryId, FormFilterBean filterParams);

   FormFilterContent createContentForFilterFormWithoutAmount(Long categoryId);

   FormPropertyContent createContentForProductForm();
}
