package wtf.norma.nekito.module.impl;

import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.event.impl.EventUpdate;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.settings.impl.NumberSetting;
import wtf.norma.nekito.util.player.MovementUtil;


public class AntiVoid extends Module {


    public NumberSetting falldisc = new NumberSetting("Fall Distance",3,1,10,1);
    public AntiVoid() {

        super("Anti Void", Category.OTHER, Keyboard.KEY_NONE);
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();

    }
    public static boolean czyjestwvoid() {
        for (int i = 0; i <= 128; i++) {
            // tak wiem shitcode a i cry abt it
            if (MovementUtil.isOnGround(i)) {
                return false;
            }
        }
        return true;
    }
    @Override
    public void onEvent(Event e) {
        if (e instanceof EventUpdate) {
            if (czyjestwvoid() && mc.thePlayer.fallDistance > falldisc.getValue()) {
                mc.thePlayer.setVelocity(0, 0, 0);
            }
        }
    }
}
