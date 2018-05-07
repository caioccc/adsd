package br.com.leucotron.livre.util;

public class Condition {

    public Comparison comparison;

    public Object value;

    public String field;

    public Condition(Comparison comparison, Object value, String field) {
        this.comparison = comparison;
        this.value = value;
        this.field = field;
    }

    public static class Builder {
        private Comparison comparison;
        private Object value;
        private String field;

        public Builder setComparison(Comparison comparison) {
            this.comparison = comparison;
            return this;
        }

        public Builder setValue(Object value) {
            this.value = value;
            return this;
        }

        public Builder setField(String field) {
            this.field = field;
            return this;
        }

        public Condition build() {
            return new Condition(comparison, value, field);
        }
    }
}
