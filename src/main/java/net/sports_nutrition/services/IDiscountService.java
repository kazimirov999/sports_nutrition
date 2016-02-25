package net.sports_nutrition.services;

import net.sports_nutrition.domain.entities.Discount;

import java.math.BigDecimal;
import java.util.List;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 30.01.2016 17:55
 */
public interface IDiscountService {

    Discount saveDiscount(Discount discount);

    Discount updateDiscount(Discount discount);

    Discount getDiscountById(Long id);

    List<Discount> findAllDiscounts();

    Discount getDiscountByName(String discountName);

    Boolean deleteDiscountById(Long discountId);

    Discount getDiscountBySize(BigDecimal discountSize);

    Boolean discountIsExist(Discount discount);

    Boolean checkBeforeUpdateDiscount(Discount discount);
}
