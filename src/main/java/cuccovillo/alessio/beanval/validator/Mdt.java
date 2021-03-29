package cuccovillo.alessio.beanval.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MdtValidator.class)
@Documented
@Inherited
public @interface Mdt {
    String message() default "{cuccovillo.alessio.beanval.validator.Mdt.message}";

    String name();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
