package net.sports.nutrition.constants;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 19.02.2016 19:44
 */
public final class ConstantsUri {

    private ConstantsUri() {}

    static final public String INDEX = "/index";

    static final public String CATEGORY_IMG = "/category/photo/{categoryId}";
    static final public String CATEGORY_ADD = "/add/category";
    static final public String CATEGORY_DELETE = "/delete/category/{id}";
    static final public String CATEGORY_DELETE_RESULT = "/delete/category/result";
    static final public String CATEGORY_DELETE_ALL_PRODUCT = "/delete/all/products/{categoryId}";
    static final public String CATEGORY_EDIT = "/edit/category";
    static final public String CATEGORY_SHOW_EDIT_FORM = "/edit/category/{categoryId}";
    static final public String CATEGORY_EDIT_RESULT = "/edit/category/result";

    static final public String PRODUCT_IMG = "/product/photo/{productId}";
    static final public String PRODUCT_SHOW_ALL = "/products/{categoryId}";
    static final public String PRODUCT_SHOW_ALL_WITH_PAGE = "/products/{categoryId}/page/{pageNumber}";
    static final public String PRODUCT_SHOW_ALL_FIRST_PAGE = "/products/{categoryId}/page/1";
    static final public String PRODUCT_SORT = "/sort/{categoryId}";
    static final public String PRODUCT_SHOW = "/show/product/{productId}";
    static final public String PRODUCT_ADD_FORM = "/add/product";
    static final public String PRODUCT_ADD = "/add/product";
    static final public String PRODUCT_EDIT_FORM = "/edit/product/{productId}";
    static final public String PRODUCT_EDIT = "/edit/product";
    static final public String PRODUCT_DELETE = "/delete/product/{productId}";

    static final public String USER_REGISTER = "/register";
    static final public String USER_SHOW_ALL = "/show/users";
    static final public String USER_ACTIVATE = "/activate/user/{userId}";
    static final public String USER_DEACTIVATE = "/deactivate/user/{userId}";
    static final public String USER_MAKE_ADMIN = "/do_admin/user/{userId}";
    static final public String USER_DELETE = "/delete/user/{userId}";

    static final public String ORDER_SHOW_FORM = "/order";
    static final public String ORDER_SAVE = "/save/order";
    static final public String ORDER_SHOW_ALL = "/all/orders/page/{pageNumber}";
    static final public String ORDER_PLACE_DELETE = "/place_delete/order/{orderId}";
    static final public String ORDER_PLACE_SEND = "/place_send/order/{orderId}";
    static final public String ORDER_BASE_URL = "/all/orders/page/";
    static final public String CART_SHOW = "/cart";
    static final public String CART_CLEAN = "/clean/cart";
    static final public String CART_MANAGE = "/manage/cart";
    static final public String CART_BUY = "/buy";

    static final public String PROPERTY_MENU = "/manage/property/page";
    static final public String PROPERTY_MENU_DISPATCHER = "/property/{propertyName}";
    static final public String PROPERTY = "/property/";
    static final public String PROPERTY_ADD_FORM = "/add/property/{propertyName}";
    static final public String PROPERTY_EDIT_FORM = "/edit/property/{propertyName}/{propertyId}";
    static final public String PROPERTY_BRAND_ADD = "/add/property/brand";
    static final public String PROPERTY_TASTE_ADD = "/add/property/taste";
    static final public String PROPERTY_DISCOUNT_ADD = "/add/property/discount";
    static final public String PROPERTY_BRAND_EDIT = "/edit/property/brand";
    static final public String PROPERTY_TASTE_EDIT = "/edit/property/taste";
    static final public String PROPERTY_DISCOUNT_EDIT = "/edit/property/discount";
    static final public String PROPERTY_BRAND_DELETE = "/delete/property/brand/{brandId}";
    static final public String PROPERTY_TASTE_DELETE = "/delete/property/taste/{tasteId}";
    static final public String PROPERTY_DISCOUNT_DELETE = "/delete/property/discount/{discountId}";

    static final public String MESSAGE_SHOW = "/show/message";

    static final public String SECURITY_USER_LOGIN = "/login";
}
