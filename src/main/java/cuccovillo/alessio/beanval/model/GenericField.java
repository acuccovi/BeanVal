package cuccovillo.alessio.beanval.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public abstract class GenericField implements Serializable {
    static final long serialVersionUID = 1L;
    private LocalDate start;
    private LocalDate end;
    private String value;

    public abstract String getFieldName();

    public abstract String getSapName();
}
