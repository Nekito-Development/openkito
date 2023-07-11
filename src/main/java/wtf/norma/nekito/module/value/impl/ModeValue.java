package wtf.norma.nekito.module.value.impl;

import wtf.norma.nekito.module.value.Value;

import java.util.Arrays;

public class ModeValue extends Value<String> {

    private final String[] values;
    private int selected;

    public ModeValue(String name, String defaultValue, String... values) {
        super(name);
        this.values = values;
        this.selected = Arrays.binarySearch(values, defaultValue);
    }

    @Override
    public String get() {
        return values[selected];
    }

    @Override
    public void set(String value) {
        selected = Arrays.binarySearch(values, value);
    }

    public String next() {
        if (selected + 1 >= values.length)
            selected = 0;
        else
            selected++;

        return values[selected];
    }

    @Override
    public String toString() {
        return "ModeValue{" +
                "values=" + Arrays.toString(values) +
                ", selected=" + selected +
                '}';
    }
}
