package wtf.norma.nekito.module.impl;

import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.event.impl.EventUpdate;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.util.player.MovementUtil;


public class NoClip extends Module {

    public NoClip() {
        super("No Clip", Category.OTHER, Keyboard.KEY_NONE);
    }



    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Override
    public void onEvent(Event e) {
        if (e instanceof EventUpdate) {
            if (mc.thePlayer != null && !jestcwelem(mc.thePlayer)) {
                if (mc.thePlayer.isCollidedHorizontally)
                    mc.thePlayer.isCollidedHorizontally = false;
                if (mc.thePlayer.isCollidedVertically)
                    mc.thePlayer.isCollidedVertically = false;
                mc.thePlayer.noClip = true;
                mc.thePlayer.onGround = false;
                MovementUtil.setMotion(MovementUtil.getSpeed());
                if (mc.gameSettings.keyBindJump.isKeyDown() && mc.thePlayer.ticksExisted % 10 == 0) {
                    mc.thePlayer.jump();
                }
                if (!(mc.gameSettings.keyBindSneak.pressed && mc.thePlayer.ticksExisted % 10 < 5)) {
                    if (mc.thePlayer.motionY < 0) {
                        mc.thePlayer.motionY = 0.00001;
                    }
                }
            } else {
                mc.thePlayer.noClip = false;
            }
        }
    }


    public static boolean jestcwelem(Entity entity) {
        boolean cwel1;
        if ((mc.theWorld.getBlockState(new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ)).getBlock() != Blocks.web && mc.theWorld.getBlockState(new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ)).getBlock() == Blocks.water && mc.theWorld.getBlockState(new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ)).getBlock() == Blocks.lava && mc.theWorld.getBlockState(new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ)).getBlock() == Blocks.air)) {
            cwel1 = true;
        } else {
            cwel1 = false;
        }
        return cwel1;

    }



}
