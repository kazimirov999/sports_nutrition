package net.sports.nutrition.services.impl;

import net.sports.nutrition.domain.entities.Discount;
import net.sports.nutrition.domain.dao.IDiscountDao;
import net.sports.nutrition.services.IDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Service to work with the Discount, using IDiscountDao.
 * .<p>
 * Implementation of IDiscountDao interface is annotated for automatic resource injection.
 * </p>
 *
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Transactional
@Service
public class DiscountServiceImpl implements IDiscountService {

    @Autowired
    private IDiscountDao discountDao;

    @Override
    public Discount saveDiscount(Discount discount) {

        return discountDao.saveOrUpdate(discount);
    }

    @Override
    public Discount updateDiscount(Discount discount) {

        return discountDao.edit(discount);
    }

    @Transactional(readOnly = true)
    @Override
    public Discount getDiscountById(Long id) {

        return discountDao.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Discount> findAllDiscounts() {
        return discountDao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Discount getDiscountByName(String discountName) {

        return discountDao.getDiscountByName(discountName);
    }

    @Transactional(readOnly = true)
    @Override
    public Discount getDiscountBySize(BigDecimal discountSize) {

        return discountDao.getDiscountBySize(discountSize);
    }

    @Override
    public Boolean deleteDiscountById(Long id) {

        return discountDao.deleteDiscountById(id);
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
