package wtf.norma.nekito.util.player;

import net.minecraft.client.Minecraft;
import net.minecraft.util.MovementInput;

public class MovementUtil {
    public final static Minecraft mc = Minecraft.getMinecraft();



    public static boolean isOnGround(double height) {
        // jestes cwelem i chuj
        if (!mc.theWorld.getCollidingBoundingBoxes(mc.thePlayer, mc.thePlayer.getEntityBoundingBox().offset(0.0D, -height, 0.0D)).isEmpty()) {
            return true;
        } else {
            return false;
        }
    }




    public double direction() {
        float rotationYaw = mc.thePlayer.rotationYaw;
        if (mc.thePlayer.moveForward < 0f) rotationYaw += 180f;
        float forward = 1f;
        if (mc.thePlayer.moveForward < 0f) forward = -0.5f;
        else if (mc.thePlayer.moveForward > 0f) forward = 0.5f;
        if (mc.thePlayer.moveStrafing > 0f) rotationYaw -= 90f * forward;
        if (mc.thePlayer.moveStrafing < 0f) rotationYaw += 90f * forward;
        return Math.toRadians(rotationYaw);
    }

    public void strafe(float speed) {
        if (!moving()) return;
        double yaw = direction();

        mc.thePlayer.motionX = -Math.sin(yaw) * speed;
        mc.thePlayer.motionZ = Math.cos(yaw) * speed;
    }

    public boolean moving() {
        return mc.thePlayer != null && (mc.thePlayer.movementInput.moveForward != 0f || mc.thePlayer.movementInput.moveStrafe != 0f);
    }
}
