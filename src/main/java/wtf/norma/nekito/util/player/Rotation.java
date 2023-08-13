package wtf.norma.nekito.util.player;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import wtf.norma.nekito.util.Animations.AnimationMath;
import wtf.norma.nekito.util.Util;

public final class Rotation implements Util {

    private final float rotationYaw;

    private final float rotationPitch;

    public Rotation(float rotationYaw, float rotationPitch) {
        this.rotationYaw = rotationYaw;
        this.rotationPitch = rotationPitch;
    }





    public static float getAngle(Entity entity) {
        double x = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * mc.getRenderPartialTicks() - mc.getRenderManager().renderPosX;
        double z = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * mc.getRenderPartialTicks() - mc.getRenderManager().renderPosZ;
        float yaw = (float) -Math.toDegrees(Math.atan2(x, z));
        return (float) (yaw - AnimationMath.Interpolate(mc.thePlayer.rotationYaw, mc.thePlayer.prevRotationYaw, 1.0D));
    }
    public static float getAngle(float x, float z) {
        float yaw = (float) -Math.toDegrees(Math.atan2(x, z));
        return (float) (yaw - AnimationMath.Interpolate(mc.thePlayer.rotationYaw, mc.thePlayer.prevRotationYaw, 1.0D));
    }




    public float getRotationYaw() {
        return rotationYaw;
    }

    public float getRotationPitch() {
        return rotationPitch;
    }
}