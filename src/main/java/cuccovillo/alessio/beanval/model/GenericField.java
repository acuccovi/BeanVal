package cuccovillo.alessio.beanval.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
public abstract class GenericField implements Serializable {
    static final long serialVersionUID = 1L;
    @Getter
    @Setter
    protected LocalDate start;
    @Getter
    @Setter
    protected LocalDate end;
    @Setter
    protected String value;

    public abstract String getValue();

    public abstract String getFieldName();

    public abstract String getSapName();
}
