package wtf.norma.nekito.module.impl;

import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.event.impl.EventUpdate;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.settings.impl.BooleanSetting;
import wtf.norma.nekito.settings.impl.NumberSetting;

public class TargetStrafe extends Module {

    public static NumberSetting range = new NumberSetting("Range", 2, 0.1f, 5, 0.5f);
    public static BooleanSetting jump = new BooleanSetting("On Jump", false);
    public static int dyrektor = 1;


    public TargetStrafe() {
        super("Target Strafe", Category.COMBAT, Keyboard.KEY_NONE);
        addSettings(range, jump);
    }

    public void onEvent(Event e) {
        if (e instanceof EventUpdate) {
            if (KillAura.target != null) {
                if (mc.thePlayer.isCollidedHorizontally) {
                    dyrektor = -dyrektor;
                } else {
                    if (mc.gameSettings.keyBindLeft.isKeyDown()) dyrektor = 1;
                    if (mc.gameSettings.keyBindRight.isKeyDown()) dyrektor = -1;
                }
            }
        }
    }


}
