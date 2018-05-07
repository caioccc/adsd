package br.com.leucotron.livre.core.dto;

import br.com.leucotron.livre.util.Comparison;

public class FilterDTO {

    String field;
    Object value;
    Comparison comparison;

    public FilterDTO(String key, Object value, Comparison comparison) {
        this.field = key;
        this.value = value;
        this.comparison = comparison;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Comparison getComparison() {
        return comparison;
    }

    public void setComparison(Comparison comparison) {
        this.comparison = comparison;
    }
}
