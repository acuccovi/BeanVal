package cuccovillo.alessio.beanval;

import cuccovillo.alessio.beanval.model.Faclim;
import cuccovillo.alessio.beanval.model.GenericField;
import cuccovillo.alessio.beanval.model.Proter;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class BeanValApplicationTests {
    private static Validator validator;

    @BeforeAll
    static void contextLoads() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void validData() {
        Proter p = new Proter();
        p.setValue("Ciao");
        Collection<ConstraintViolation<GenericField>> violations = validate(p);
        assertTrue(violations.isEmpty());
    }

    @Test
    void invalidData() {
        Proter p = new Proter();
        p.setValue("Ciao!");
        Collection<ConstraintViolation<GenericField>> violations = validate(p);
        assertFalse(violations.isEmpty());
    }

    @Test
    void invalidDataWithDefaultMessage() {
        Proter p = new Proter();
        p.setValue("Ciao!");
        Collection<ConstraintViolation<GenericField>> violations = validate(p);
        for (ConstraintViolation<GenericField> violation : violations) {
            assertEquals("Value not valid", violation.getMessage());
            log.info("Default message: [{}]", violation.getMessage());
        }
    }

    @Test
    void invalidDataWithCustomMessage() {
        Faclim f = new Faclim();
        f.setValue("Ciao!");
        Collection<ConstraintViolation<GenericField>> violations = validate(f);
        assertFalse(violations.isEmpty());
        for (ConstraintViolation<GenericField> violation : violations) {
            assertEquals("Cambia questo valore", violation.getMessage());
            log.info("Custom message: [{}]", violation.getMessage());
        }
    }

    private Collection<ConstraintViolation<GenericField>> validate(GenericField field) {
        return validator.validate(field);
    }


}
