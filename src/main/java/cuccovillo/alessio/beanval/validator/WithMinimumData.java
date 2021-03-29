package cuccovillo.alessio.beanval.validator;

import cuccovillo.alessio.beanval.model.GenericField;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = WithMinimumDataValidator.class)
@Documented
@Inherited
public @interface WithMinimumData {
    Class<? extends GenericField> mandatoryFields();

    String message() default "{cuccovillo.alessio.beanval.validator.WithMinimumData.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
