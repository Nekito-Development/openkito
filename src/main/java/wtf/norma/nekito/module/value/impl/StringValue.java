package wtf.norma.nekito.module.value.impl;

import wtf.norma.nekito.module.value.Value;

public class StringValue extends Value<String> {

    private String value;

    public StringValue(String name, String value) {
        super(name);
        this.value = value;
    }

    @Override
    public String get() {
        return value;
    }

    @Override
    public void set(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "StringValue{" +
                "value='" + value + '\'' +
                '}';
    }
}
