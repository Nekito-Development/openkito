package wtf.norma.nekito.util.player;

import net.minecraft.entity.player.EntityPlayer;
import wtf.norma.nekito.util.Util;

public class Rotate implements Util {
    float yaw, pitch;

    public Rotate(float yaw, float pitch) {
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public float getYaw() {
        return this.yaw;
    }

    public float getPitch() {
        return this.pitch;
    }

    public void toPlayer(EntityPlayer player) {
        if (Float.isNaN(yaw) || Float.isNaN(pitch))
            return;

        fixedSensitivity(mc.gameSettings.mouseSensitivity);

        player.rotationYaw = yaw;
        player.rotationPitch = pitch;
    }

    public void fixedSensitivity(Float sensitivity) {
        float f = sensitivity * 0.6F + 0.2F;
        float gcd = f * f * f * 1.2F;

        yaw -= yaw % gcd;
        pitch -= pitch % gcd;
    }

}
