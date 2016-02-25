package net.sports_nutrition.services.service_implementations;

import net.sports_nutrition.domain.entities.Discount;
import net.sports_nutrition.domain.repositories.IDiscountRepository;
import net.sports_nutrition.services.IDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 30.01.2016 17:54
 */

@Transactional
@Service
public class DiscountServiceImpl implements IDiscountService {

    @Autowired
    private IDiscountRepository discountRepository;

    @Override
    public Discount saveDiscount(Discount discount) {

        return discountRepository.saveOrUpdate(discount);
    }

    @Override
    public Discount updateDiscount(Discount discount) {

        return discountRepository.edit(discount);
    }

    @Transactional(readOnly = true)
    @Override
    public Discount getDiscountById(Long id) {

        return discountRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Discount> findAllDiscounts() {
        return discountRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Discount getDiscountByName(String discountName) {

        return discountRepository.getDiscountByName(discountName);
    }

    @Transactional(readOnly = true)
    @Override
    public Discount getDiscountBySize(BigDecimal discountSize) {

        return discountRepository.getDiscountBySize(discountSize);
    }

    @Override
    public Boolean deleteDiscountById(Long id) {

        return discountRepository.deleteDiscountById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Boolean discountIsExist(Discount discount) {
        Boolean isExist = false;
        if (getDiscountByName(discount.getName()) != null ||
                getDiscountBySize(discount.getSize()) != null)
            isExist = true;

        return isExist;
    }

    @Transactional(readOnly = true)
    @Override
    public Boolean checkBeforeUpdateDiscount(Discount discount) {
        Boolean check = true;
        List<Discount> dis = new ArrayList<>();
        dis.add(getDiscountByName(discount.getName()));
        dis.add(getDiscountBySize(discount.getSize()));
        for (Discount d : dis)
            if (d != null && !d.getId().equals(discount.getId()))
                check = false;

        return check;
    }
}
