package wtf.norma.nekito.module.value.impl;

import wtf.norma.nekito.module.value.Value;

public class BooleanValue extends Value<Boolean> {

    private boolean state;

    public BooleanValue(String name, boolean state) {
        super(name);
        this.state = state;
    }

    @Override
    public Boolean get() {
        return state;
    }

    @Override
    public void set(Boolean value) {
        this.state = value;
    }

    public void toggle() {
        state = !state;
    }

    @Override
    public String toString() {
        return "BooleanValue{" +
                "state=" + state +
                '}';
    }
}
