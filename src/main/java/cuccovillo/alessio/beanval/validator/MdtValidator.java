package cuccovillo.alessio.beanval.validator;

import cuccovillo.alessio.beanval.service.MdtService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class MdtValidator implements ConstraintValidator<Mdt, String> {
    private String name;
    private MdtService mdtService;

    public MdtValidator() throws IOException {
        this.mdtService = new MdtService();
    }

    @Override
    public void initialize(Mdt constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.name = constraintAnnotation.name();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        AtomicBoolean result = new AtomicBoolean(false);
        mdtService.getMdtValues(name).ifPresent(mdt -> result.set(mdt.values().stream().anyMatch(row -> row.getCode().equals(value))));
        return result.get();
    }
}
