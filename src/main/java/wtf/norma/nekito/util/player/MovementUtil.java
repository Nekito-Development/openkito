package wtf.norma.nekito.util.player;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovementInput;
import wtf.norma.nekito.module.impl.KillAura;
import wtf.norma.nekito.module.impl.TargetStrafe;
import wtf.norma.nekito.nekito;

import static java.lang.Math.toRadians;

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


    public static Entity getRidingEntity() {
        return Entity.ridingEntity;
    }

    public static boolean isBlockAboveHead() {
        AxisAlignedBB axisAlignedBB = new AxisAlignedBB(mc.thePlayer.posX - 0.3, mc.thePlayer.posY + mc.thePlayer.getEyeHeight(), mc.thePlayer.posZ + 0.3, mc.thePlayer.posX + 0.3, mc.thePlayer.posY + (!mc.thePlayer.onGround ? 1.5 : 2.5), mc.thePlayer.posZ - 0.3);
        return mc.theWorld.getCollidingBoundingBoxes(mc.thePlayer, axisAlignedBB).isEmpty();
    }

    public static boolean isOnLiquid() {
        AxisAlignedBB boundingBox = mc.thePlayer.getEntityBoundingBox();
        if (boundingBox == null) {
            return false;
        }
        boundingBox = boundingBox.contract(0.01D, 0.0D, 0.01D).offset(0.0D, -0.01D, 0.0D);
        boolean onLiquid = false;
        int y = (int) boundingBox.minY;
        for (int x = MathHelper.floor_double(boundingBox.minX); x < MathHelper
                .floor_double(boundingBox.maxX + 1.0D); x++) {
            for (int z = MathHelper.floor_double(boundingBox.minZ); z < MathHelper
                    .floor_double(boundingBox.maxZ + 1.0D); z++) {
                Block block = mc.theWorld.getBlockState((new BlockPos(x, y, z))).getBlock();
                if (block != Blocks.air) {
                    if (!(block instanceof BlockLiquid)) {
                        return false;
                    }
                    onLiquid = true;
                }
            }
        }
        return onLiquid;
    }
    public static void setMotion(double speed) {
        EntityLivingBase entity = KillAura.target;
        TargetStrafe ddospolska = (TargetStrafe) nekito.INSTANCE.getModuleManager().getModule(TargetStrafe.class);
        // credits: jakis gosciu z forum
        boolean targetStrafe = MovementUtil.mozeStrafe();
        MovementInput movementInput = mc.thePlayer.movementInput;
        float yaw = targetStrafe ? HeadUtility.getRotationsRandom(entity).getRotationYaw() : mc.thePlayer.rotationYaw;
        double forward = targetStrafe ? mc.thePlayer.getDistanceToEntity(entity) <= (float)ddospolska.range.getValue() ? 0 : 1 : movementInput.moveForward;
        double strafe = targetStrafe ? -1 : movementInput.moveStrafe;
        if ((forward == 0.0D) && (strafe == 0.0D)) {
            mc.thePlayer.motionX = 0;
            mc.thePlayer.motionZ = 0;
        } else {
            if (forward != 0.0D) {
                if (strafe > 0.0D) {
                    yaw += (forward > 0.0D ? -45 : 45);
                } else if (strafe < 0.0D) {
                    yaw += (forward > 0.0D ? 45 : -45);
                }
                strafe = 0.0D;
                if (forward > 0.0D) {
                    forward = 1;
                } else if (forward < 0.0D) {
                    forward = -1;
                }
            }

            mc.thePlayer.motionX = forward * speed * Math.cos(toRadians(yaw + 90.0F)) + strafe * speed * Math.sin(toRadians(yaw + 90.0F));
            mc.thePlayer.motionZ = forward * speed * Math.sin(toRadians(yaw + 90.0F)) - strafe * speed * Math.cos(toRadians(yaw + 90.0F));
        }
    }

    public static float getDirection() {float yaw = Minecraft.getMinecraft().thePlayer.rotationYaw;final float forward = Minecraft.getMinecraft().thePlayer.moveForward;final float strafe = Minecraft.getMinecraft().thePlayer.moveStrafing;yaw += ((forward < 0.0f) ? 180 : 0);final int i = (forward < 0.0f) ? -45 : ((forward == 0.0f) ? 90 : 45);if (strafe < 0.0f) {yaw += i;}if (strafe > 0.0f) {yaw -= i;}return yaw * 0.017453292f;}


    public static void strafe(double speed) {
        if (!isMoving()) {
            mc.thePlayer.motionX = (0.0F);
            mc.thePlayer.motionZ = (0.0F);
        } else {
            double direction = getDirection();
            mc.thePlayer.motionX = (Math.sin(direction) * -speed);
            mc.thePlayer.motionZ = (Math.cos(direction) * speed);
        }
    }

    public static boolean isMoving() {
        return mc.thePlayer.moveForward != 0 || mc.thePlayer.moveStrafing != 0;
    }


    public static float getSpeed() {
        return (float) Math.sqrt(mc.thePlayer.motionX * mc.thePlayer.motionX + mc.thePlayer.motionZ * mc.thePlayer.motionZ);
    }



    public static boolean mozeStrafe() {

        if (!nekito.INSTANCE.getModuleManager().getModule(KillAura.class).isToggled()) {
            return false;
        }
        if (!nekito.INSTANCE.getModuleManager().getModule(TargetStrafe.class).isToggled()) {
            return false;
        }
        if (TargetStrafe.jump.isEnabled()) {
            if (mc.gameSettings.keyBindJump.isPressed())
                return KillAura.target != null;
            else
                return false;
        } else {
            return KillAura.target != null;
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
