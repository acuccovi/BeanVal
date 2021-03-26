package cuccovillo.alessio.beanval.validator;

import cuccovillo.alessio.beanval.model.GenericField;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.stream.Stream;

public class ListValidator implements ConstraintValidator<List, GenericField> {
    private String[] values;

    @Override
    public void initialize(List constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.values = constraintAnnotation.values();
    }

    @Override
    public boolean isValid(GenericField value, ConstraintValidatorContext context) {
        return Stream.of(values).anyMatch(v -> v.equals(value.getValue()));
    }
}
