package cuccovillo.alessio.beanval.validator;

import cuccovillo.alessio.beanval.model.CSVContent;
import cuccovillo.alessio.beanval.model.GenericField;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.stream.Stream;

public class WithMinimumDataValidator implements ConstraintValidator<WithMinimumData, CSVContent> {
    Class<? extends GenericField> mandatoryFields;

    @Override
    public void initialize(WithMinimumData constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.mandatoryFields = constraintAnnotation.mandatoryFields();
    }

    @Override
    public boolean isValid(CSVContent value, ConstraintValidatorContext context) {
        return value.getFields().stream().anyMatch(
                f -> Stream.of(mandatoryFields).anyMatch(
                        m -> f.getClass().getName().equals(m.getName())
                )
        );
    }
}
