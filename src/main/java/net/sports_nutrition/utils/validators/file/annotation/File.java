package net.sports_nutrition.utils.validators.file.annotation;

        import net.sports_nutrition.utils.validators.file.Type;
        import net.sports_nutrition.utils.validators.file.constraint.FileTypeConstraintValidator;

        import javax.validation.Constraint;
        import javax.validation.Payload;
        import java.lang.annotation.*;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 27.01.2016 22:13
 */
@Documented
@Constraint(validatedBy = FileTypeConstraintValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface File {

    Type type()[] default Type.JPG;

    String message() default "Type is not correct!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
