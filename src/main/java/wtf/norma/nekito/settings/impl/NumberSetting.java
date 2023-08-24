package wtf.norma.nekito.settings.impl;


import wtf.norma.nekito.settings.Setting;

public class NumberSetting extends Setting {
    public float value, min, max, increment;

    public NumberSetting(String name, float value, float min, float max, float increment) {
        this.name = name;
        this.value = value;
        this.min = min;
        this.max = max;
        this.increment = increment;
    }

    public float getFloatValue() {
        return this.value;
    }
    public int getCurrentValueInt() {
        return (int)this.value;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public void setValueNumber(float value) {
        this.value = value;
    }


    public float getIncrement() {
        return increment;
    }

    public void setIncrement(float inc) {
        this.increment = inc;
    }
}
