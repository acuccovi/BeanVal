package cuccovillo.alessio.beanval;

import cuccovillo.alessio.beanval.model.CSVContent;
import cuccovillo.alessio.beanval.model.GenericField;
import cuccovillo.alessio.beanval.model.Polo;
import cuccovillo.alessio.beanval.model.Proter;
import cuccovillo.alessio.beanval.validator.ValidationGroup;
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
        p.setValue("012345678901234567890123456789012345678901234567890123456789012345678901234567890");
        Collection<ConstraintViolation<GenericField>> violations = validate(p);
        assertFalse(violations.isEmpty());
    }

    @Test
    void invalidDataWithDefaultMessage() {
        Proter p = new Proter();
        p.setValue("012345678901234567890123456789012345678901234567890123456789012345678901234567890");
        Collection<ConstraintViolation<GenericField>> violations = validate(p);
        assertFalse(violations.isEmpty());
        for (ConstraintViolation<GenericField> violation : violations) {
            assertEquals("la lunghezza deve essere compresa tra 0 e 80", violation.getMessage());
            log.info("Default message: [{}]", violation.getMessage());
        }
    }

    @Test
    void validMdt() {
        Polo p = new Polo();
        p.setValue("A");
        Collection<ConstraintViolation<GenericField>> violations = validate(p);
        assertTrue(violations.isEmpty());
    }

    @Test
    void invalidMdt() {
        Polo p = new Polo();
        p.setValue("");
        Collection<ConstraintViolation<GenericField>> violations = validate(p);
        assertFalse(violations.isEmpty());
    }

    @Test
    void validCSV() {
        Polo p = new Polo();
        p.setValue("A");
        Proter proter = new Proter();
        proter.setValue("Ciao");
        CSVContent csv = new CSVContent();
        csv.getFields().add(p);
        csv.getFields().add(proter);
        Collection<ConstraintViolation<CSVContent>> classViolations = validator.validate(csv, ValidationGroup.FirstCheckGroup.class);
        assertTrue(classViolations.isEmpty());
        Collection<ConstraintViolation<CSVContent>> violations = validator.validate(csv);
        assertTrue(violations.isEmpty());
    }

    @Test
    void invalidCSVField() {
        Polo polo = new Polo();
        polo.setValue("A1");
        Proter proter = new Proter();
        proter.setValue("Ciao");
        CSVContent csv = new CSVContent();
        csv.getFields().add(polo);
        csv.getFields().add(proter);
        Collection<ConstraintViolation<CSVContent>> fieldsViolations = validator.validate(csv);
        assertFalse(fieldsViolations.isEmpty());
        fieldsViolations.forEach(v -> {
            log.info(v.getLeafBean().getClass());
            log.info(v.getPropertyPath());
            log.info(v.getMessage());
        });
    }

    @Test
    void invalidCSVFirstCheck() {
        Proter proter = new Proter();
        proter.setValue("Ciao");
        CSVContent csv = new CSVContent();
        csv.getFields().add(proter);
        Collection<ConstraintViolation<CSVContent>> violations = validator.validate(csv, ValidationGroup.FirstCheckGroup.class);
        assertFalse(violations.isEmpty());
        violations.forEach(v -> {
            log.info(v.getLeafBean().getClass());
            log.info(v.getPropertyPath());
            log.info(v.getMessage());
        });
    }

    private Collection<ConstraintViolation<GenericField>> validate(GenericField field) {
        return validator.validate(field);
    }

}
