package net.sports.nutrition.utils.validators.file.annotation;

import net.sports.nutrition.utils.validators.file.constraint.FileExistenceConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 27.01.2016 22:13
 */
@Documented
@Constraint(validatedBy = FileExistenceConstraintValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface FileNotEmpty {

    String message() default "File is not exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
