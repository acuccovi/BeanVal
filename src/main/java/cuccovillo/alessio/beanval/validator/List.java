package cuccovillo.alessio.beanval.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ListValidator.class)
public @interface List {
    String message() default "Value not valid";

    String[] values() default {};

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
