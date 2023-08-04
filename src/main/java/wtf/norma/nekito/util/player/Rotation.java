package wtf.norma.nekito.util.player;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public final class Rotation {

    private final float rotationYaw;

    private final float rotationPitch;

    public Rotation(float rotationYaw, float rotationPitch) {
        this.rotationYaw = rotationYaw;
        this.rotationPitch = rotationPitch;
    }

    public float getRotationYaw() {
        return rotationYaw;
    }

    public float getRotationPitch() {
        return rotationPitch;
    }
}