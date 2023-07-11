package wtf.norma.nekito.helper.math;

import net.minecraft.client.Minecraft;
import net.minecraft.util.MathHelper;

public class AnimationHelper {

    private static final double ANIMATION_SPEED = 0.20;

    private final double animationSpeed;
    private double animationState, prevAnimationState;

    public AnimationHelper() {
        this(0);
    }

    public AnimationHelper(double animationSpeed) {
        this.animationSpeed = ANIMATION_SPEED + animationSpeed;
    }

    public static double createAnimation(double value) {
        return Math.sqrt(1 - Math.pow(value - 1, 2));
    }

    public static double dropAnimation(double value) {
        double c1 = 1.70158;
        double c3 = c1 + 1;
        return 1 + c3 * Math.pow(value - 1, 3) + c1 * Math.pow(value - 1, 2);
    }

    public void update(boolean add) {
        prevAnimationState = animationState;
        animationState = MathHelper.clamp(animationState + (add ? animationSpeed : -animationSpeed), 0, 1);
    }

    public void set(double animation) {
        this.animationState = animation;
        this.prevAnimationState = animation;
    }

    public double get() {
        return createAnimation(this.prevAnimationState
                + (this.animationState - this.prevAnimationState) * Minecraft.getMinecraft().getRenderPartialTicks());
    }

    public double getDrop() {
        return dropAnimation(this.prevAnimationState
                + (this.animationState - this.prevAnimationState) * Minecraft.getMinecraft().getRenderPartialTicks());
    }

    public void reset() {
        this.prevAnimationState = 0;
        this.animationState = 0;
    }
}