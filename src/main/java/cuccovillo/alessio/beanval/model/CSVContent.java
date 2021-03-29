package cuccovillo.alessio.beanval.model;

import cuccovillo.alessio.beanval.validator.ValidationGroup;
import cuccovillo.alessio.beanval.validator.WithMinimumData;
import lombok.Data;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Data
@WithMinimumData(
        mandatoryFields = Polo.class,
        groups = ValidationGroup.FirstCheckGroup.class
)
public class CSVContent {
    @Valid
    private List<GenericField> fields;

    public CSVContent() {
        this.fields = new ArrayList<>();
    }
}
