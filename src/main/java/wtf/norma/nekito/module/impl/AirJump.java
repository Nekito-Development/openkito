package wtf.norma.nekito.module.impl;

import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.event.impl.EventPreMotion;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.util.Util;


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
            // IDK HOW BUT IT BYPASSES VULCAN AND VERUS AND OTHER SHIT LUB
            if (mc.thePlayer.onGround) {
                mc.thePlayer.jump();
            }
            if ((!Util.mc.theWorld.getCollidingBoundingBoxes(mc.thePlayer, mc.thePlayer.getEntityBoundingBox().offset(0, -1, 0).expand(0.5, 0, 0.5)).isEmpty() && mc.thePlayer.ticksExisted % 2 == 0)) {
                    mc.gameSettings.keyBindJump.pressed = true;
                    mc.thePlayer.jumpTicks = 0;
                    mc.thePlayer.fallDistance = 0;
                    ((EventPreMotion) e).setOnGround(true);
                    mc.thePlayer.onGround = true;
            }
        }
    }
}
