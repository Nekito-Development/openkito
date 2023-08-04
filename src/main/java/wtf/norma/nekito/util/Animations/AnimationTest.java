package wtf.norma.nekito.util.Animations;

public class AnimationTest {

    private float minValue;
    private float maxValue;
    private float speed;

    public void setMinValue(float minValue) {
        this.minValue = minValue;
    }

    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setValue(float value) {
        this.value = value;
    }

    private float value;

    private AnimationState state = AnimationState.STOPPED;

    public boolean isE;

    public AnimationTest(float minValue, float maxValue, float speed) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.speed = speed;
    }

    public void setState(AnimationState state) {
        this.state = state;
    }

    public float getOutput() {
        if (state == AnimationState.STOPPED) {
            return value;
        }

        isE = state == AnimationState.INCREASE;

        value = AnimationMath.fast(value, state == AnimationState.INCREASE ? maxValue : minValue , speed);


        return value;
    }

    public enum AnimationState {
        INCREASE,
        DECREASE,
        STOPPED
    }

}
