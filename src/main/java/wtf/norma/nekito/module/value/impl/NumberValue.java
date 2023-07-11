package wtf.norma.nekito.module.value.impl;

import wtf.norma.nekito.module.value.Value;

public class NumberValue<T extends Number> extends Value<T> {

    private T value;

    public NumberValue(String name, T value) {
        super(name);
        this.value = value;
    }

    @Override
    public T get() {
        return value;
    }

    @Override
    public void set(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "NumberValue{" +
                "value=" + value +
                '}';
    }
}
