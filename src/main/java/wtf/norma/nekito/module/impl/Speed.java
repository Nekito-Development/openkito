package wtf.norma.nekito.module.impl;

import net.minecraft.block.*;
import net.minecraft.potion.Potion;
import net.minecraft.util.MathHelper;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.event.impl.EventUpdate;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.settings.impl.ModeSetting;
import wtf.norma.nekito.settings.impl.NumberSetting;
import wtf.norma.nekito.util.player.MovementUtil;

import java.util.Random;


public class Speed extends Module {

    public Speed() {
        super("Player Speed", Category.MOVEMENT, Keyboard.KEY_G);
        addSettings(mode);
    }

    public ModeSetting mode = new ModeSetting("Mode", "Vulcan", "Vulcan", "Ground","Matrix Timer");



    public static boolean isOnGround() {
        if (!mc.thePlayer.onGround) return false;
        return mc.thePlayer.isCollidedVertically;
    }

    public void onDisable() {
        super.onDisable();
        mc.timer.timerSpeed = 1F;
        mc.thePlayer.jumpMovementFactor = 0.02F;
    }
    @Override
    public void onEvent(Event e) {
        if (e instanceof EventUpdate) {
            switch (mode.getMode()){
                case "Vulcan":
                    if (mc.thePlayer.onGround) {
                        mc.thePlayer.jump();
                        mc.thePlayer.motionY = 0.44;
                        MovementUtil.strafe((double) MovementUtil.getSpeed());
                    } else if (!mc.thePlayer.onGround && mc.thePlayer.ticksExisted % 15 == 0) {
                        mc.thePlayer.motionY = -0.44;
                    }

                    break;
                case "Ground":
                    if (MovementUtil.isMoving()) {
                        if (mc.thePlayer.onGround) {
                            MovementUtil.setMotion((float) MovementUtil.getSpeed() + 0.3f);
                        }
                    }
                    break;
                case"Matrix Timer":
                    float timerValue = mc.thePlayer.fallDistance <= 0.22f ? 2f :
                            (float) (mc.thePlayer.fallDistance < 1.25f ? 0.67 : 1f);
                    if (MovementUtil.isMoving()) {
                        mc.timer.timerSpeed = timerValue;
                        mc.thePlayer.jumpMovementFactor = 0.026423f; //slay 🎅🎅🎅🎅🎅🎅🎅
                        if (isOnGround()) {
                            mc.thePlayer.jump();
                        }
                    } else {
                        mc.timer.timerSpeed = 1.0f;
                    }
                    break;

            }
        }
    }
}

