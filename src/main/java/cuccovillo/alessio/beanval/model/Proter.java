package cuccovillo.alessio.beanval.model;

import cuccovillo.alessio.beanval.validator.List;

@List(values = {"Ciao", "Pippo"})
public class Proter extends GenericField {
    @Override
    public String getFieldName() {
        return "PROTER";
    }

    @Override
    public String getSapName() {
        return "APROTER";
    }
}
