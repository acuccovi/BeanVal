package cuccovillo.alessio.beanval.model;

import cuccovillo.alessio.beanval.validator.Mdt;

public class Polo extends GenericField {
    public static final String FIELD_NAME = "POLO";

    @Override
    public String getFieldName() {
        return FIELD_NAME;
    }

    @Override
    public String getSapName() {
        return "APOLO";
    }

    @Override
    @Mdt(name = "APOLO")
    public String getValue() {
        return value;
    }
}
