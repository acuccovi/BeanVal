package cuccovillo.alessio.beanval.model;

import cuccovillo.alessio.beanval.validator.List;

@List(values = "Pinco", message = "Cambia questo valore")
public class Faclim extends GenericField {
    @Override
    public String getFieldName() {
        return "FACLIM";
    }

    @Override
    public String getSapName() {
        return "AFACLIM";
    }
}
