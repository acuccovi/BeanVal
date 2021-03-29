package cuccovillo.alessio.beanval;

import cuccovillo.alessio.beanval.model.CSVContent;
import cuccovillo.alessio.beanval.model.GenericField;
import cuccovillo.alessio.beanval.model.Polo;
import cuccovillo.alessio.beanval.model.Proter;
import cuccovillo.alessio.beanval.validator.ValidationGroup;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

@SpringBootApplication
public class BeanValApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeanValApplication.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {
            try (InputStream is = getClass().getResourceAsStream("input.csv");
                 Scanner reader = new Scanner(Objects.requireNonNull(is))) {
                reader.nextLine();
                reader.nextLine();
                CSVContent csv = new CSVContent();
                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    String[] data = line.split(";");
                    try {
                        GenericField field = makeField(data);
                        csv.getFields().add(field);
                    } catch (Exception ignore) {
                    }
                }
                Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
                Set<ConstraintViolation<CSVContent>> violations = validator.validate(csv, ValidationGroup.FirstCheckGroup.class);
                if (!violations.isEmpty()) {
                    violations.forEach(System.out::println);
                } else {
                    violations = validator.validate(csv);
                    violations.forEach(System.out::println);
                }
            }
        };
    }

    private GenericField makeField(String[] data) {
        GenericField field;
        switch (data[1].toUpperCase()) {
            case Polo.FIELD_NAME:
                field = new Polo();
                break;
            case Proter.FIELD_NAME:
                field = new Proter();
                break;
            default:
                throw new IllegalArgumentException();
        }
        field.setStart(convertDate(data[2]));
        field.setStart(convertDate(data[3]));
        field.setValue(data[4]);
        return field;
    }

    private LocalDate convertDate(String date) {
        return LocalDate.parse(date);
    }
}
