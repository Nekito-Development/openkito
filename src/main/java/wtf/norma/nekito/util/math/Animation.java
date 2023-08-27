package wtf.norma.nekito.util.math;

import net.minecraft.client.Minecraft;
import net.minecraft.util.MathHelper;
import wtf.norma.nekito.Nekito;

public class Animation {
    private final double animationSpeed;
    private double animationState, prevAnimationState;

    public Animation() {
        this(0);
    }

    public Animation(double animationSpeed) {
        this.animationSpeed =        Nekito.INSTANCE.animationSpeed + animationSpeed;

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
        return Nekito.INSTANCE.createAnimation(this.prevAnimationState
                + (this.animationState - this.prevAnimationState) * Minecraft.getMinecraft().getRenderPartialTicks());
    }

    public double getDrop() {
        return Nekito.INSTANCE.dropAnimation(this.prevAnimationState
                + (this.animationState - this.prevAnimationState) * Minecraft.getMinecraft().getRenderPartialTicks());
    }

    public void reset() {
        this.prevAnimationState = 0;
        this.animationState = 0;
    }
}