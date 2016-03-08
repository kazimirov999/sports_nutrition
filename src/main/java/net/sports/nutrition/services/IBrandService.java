package net.sports.nutrition.services;

import net.sports.nutrition.domain.entities.Brand;

import java.util.List;

/**
 * Service to work with the Brand.
 *
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public interface IBrandService {

    /**
     * Returns brand by identifier.
     *
     * @param id -  identifier of the Entity.
     * @return <tt>Brand</tt> if the action is successful, <tt>null</tt>  otherwise.
     */
    Brand getBrandById(Long id);

    /**
     * Saves brand.
     *
     * @param brand - brand for save.
     * @return <tt>Brand<tt/> if the action is successful, throw exception  otherwise.
     */
    Brand saveBrand(Brand brand);

    /**
     * Returns  all brands.
     *
     * @return <tt>list of Brand</tt> if the brands is exist, <tt>null</tt> otherwise
     */
    List<Brand> findAllBrands();

    /**
     * Returns  the brand by name.
     *
     * @param brandName -  name of the brand
     * @return <tt>Brand</tt> if the brand is exist, <tt>null</tt> otherwise
     */
    Brand getBrandByName(String brandName);

    /**
     * Checks brand of the presence in the database.
     *
     * @param brand - brand for check
     * @return <tt>true</tt> if brand is exist, <tt>false</tt> otherwise
     */
    Boolean brandIsExist(Brand brand);

    /**
     * Updates brand.
     * This method should be edit Brand but if Brand doesn't exist, save Brand as new Entity.
     *
     * @param brand - brand for update.
     * @return updated <tt>Brand</tt>  if the action is successful, <tt>null</tt>  otherwise.
     */
    Brand updateBrand(Brand brand);

    /**
     * Deletes the brand by id.
     *
     * @param brandId - identifier of the brand
     * @return <tt>number greater than zero</tt> if the action is successful, <tt>-1</tt> otherwise.
     */
    Integer deleteById(Long brandId);

    /**
     * Checks brand before update.
     *
     * @param brand - brand for check
     * @return <tt>true</tt> if the brand of the same name doesn't exist, <tt>null</tt>  otherwise.
     */
    Boolean checkBeforeUpdateBrand(Brand brand);
}
