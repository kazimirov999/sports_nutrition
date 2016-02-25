package net.sports.nutrition.domain.repositories;

import net.sports.nutrition.domain.entities.Discount;

import java.math.BigDecimal;
import java.util.List;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 21.01.2016 0:07
 */
public interface IDiscountRepository extends IGenericRepository<Discount,Long> {

    Discount getDiscountByName(String discountName);

    public Boolean deleteDiscountById(Long discountId);

    Discount getDiscountBySize(BigDecimal discountSize);

    List<Discount> findAll();

    List<Discount> getDiscountsByCategoryId(Long categoryId);
}
