package wtf.norma.nekito.module.value.impl;

import wtf.norma.nekito.module.value.Value;

public class RangeNumberValue extends Value<Float> {

    private final float min;
    private final float max;
    private final float increment;
    private float value;

    public RangeNumberValue(String name, float value, float min, float max, float increment) {
        super(name);
        this.value = value;
        this.min = min;
        this.max = max;
        this.increment = increment;
    }

    @Override
    public Float get() {
        return value;
    }

    @Override
    public void set(Float value) {
        this.value = value;
    }

    public void increment() {
        if (value + increment < max)
            value += increment;
    }

    public void decrement() {
        if (value - increment > min)
            value -= increment;
    }

    public float getMin() {
        return min;
    }

    public float getMax() {
        return max;
    }

    public float getIncrement() {
        return increment;
    }

    @Override
    public String toString() {
        return "RangeNumberValue{" +
                "value=" + value +
                ", min=" + min +
                ", max=" + max +
                ", increment=" + increment +
                '}';
    }
}
