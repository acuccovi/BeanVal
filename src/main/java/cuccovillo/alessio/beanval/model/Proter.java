package cuccovillo.alessio.beanval.model;

import org.hibernate.validator.constraints.Length;

public class Proter extends GenericField {
    public static final String FIELD_NAME = "PROTER";

    @Override
    public String getFieldName() {
        return "PROTER";
    }

    @Override
    public String getSapName() {
        return "APROTER";
    }

    @Override
    @Length(max = 80)
    public String getValue() {
        return value;
    }
}
