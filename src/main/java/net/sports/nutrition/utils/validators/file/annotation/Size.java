package net.sports.nutrition.utils.validators.file.annotation;

import net.sports.nutrition.utils.validators.file.constraint.FileSizeConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 27.01.2016 23:56
 */
@Documented
@Constraint(validatedBy = FileSizeConstraintValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD,ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface Size {

    int min() default 0;

    int max() default 5000000;

    String message() default "Size is not correct";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

