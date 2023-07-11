package wtf.norma.nekito.module.value.impl;

import wtf.norma.nekito.module.value.Value;

public class ObjectValue extends Value<Object> {

    private Object value;

    public ObjectValue(String name, Object value) {
        super(name);
        this.value = value;
    }

    @Override
    public Object get() {
        return value;
    }

    @Override
    public void set(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ObjectValue{" +
                "value=" + value +
                '}';
    }
}
