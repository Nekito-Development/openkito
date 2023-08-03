package wtf.norma.nekito.module.impl;

import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.event.impl.EventMotion;
import wtf.norma.nekito.event.impl.EventPreMotion;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.settings.impl.BooleanSetting;
import wtf.norma.nekito.settings.impl.ModeSetting;
import wtf.norma.nekito.settings.impl.NumberSetting;
import wtf.norma.nekito.util.Util;
import wtf.norma.nekito.util.player.MovementUtil;


public class AirJump extends Module {

    public AirJump() {
        super("AirJump", Category.MOVEMENT, Keyboard.KEY_NONE);

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
        if (e instanceof EventPreMotion) {
            if ((!Util.mc.theWorld.getCollidingBoundingBoxes(Minecraft.getMinecraft().thePlayer, Minecraft.getMinecraft().thePlayer.getEntityBoundingBox().offset(0, -1, 0).expand(0.5, 0, 0.5)).isEmpty() && Minecraft.getMinecraft().thePlayer.ticksExisted % 2 == 0)) {
                if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
                    Minecraft.getMinecraft().thePlayer.jumpTicks = 0;
                    Minecraft.getMinecraft().thePlayer.fallDistance = 0;
                 ((EventPreMotion) e).setOnGround(true);
                    Minecraft.getMinecraft().thePlayer.onGround = true;
                }
            }
        }
    }
}
