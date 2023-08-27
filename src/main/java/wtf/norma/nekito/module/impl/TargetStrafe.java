package wtf.norma.nekito.module.impl;

import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import me.zero.alpine.listener.Subscriber;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.Nekito;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.newevent.impl.update.EventUpdate;
import wtf.norma.nekito.settings.impl.BooleanSetting;
import wtf.norma.nekito.settings.impl.NumberSetting;

public class TargetStrafe extends Module implements Subscriber {

    public static NumberSetting range = new NumberSetting("Range", 2, 0.1f, 5, 0.5f);
    public static BooleanSetting jump = new BooleanSetting("On Jump", false);
    public static int dyrektor = 1;


    public TargetStrafe() {
        super("Target Strafe", Category.COMBAT, Keyboard.KEY_NONE);
        addSettings(range, jump);
    }

    @Override
    public void onEnable() {
        Nekito.EVENT_BUS.subscribe(this);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        Nekito.EVENT_BUS.unsubscribe(this);
        super.onDisable();
    }

    @Subscribe
    private final Listener<EventUpdate> listener = new Listener<>(event ->{
        if (KillAura.target != null) {
            if (mc.thePlayer.isCollidedHorizontally)
                dyrektor = -dyrektor;
            else {
                if (mc.gameSettings.keyBindLeft.isKeyDown()) dyrektor = 1;
                if (mc.gameSettings.keyBindRight.isKeyDown()) dyrektor = -1;
            }
        }
    });

//    public void onEvent(Event e) {
//        if (e instanceof EventUpdate) {
//            if (KillAura.target != null) {
//                if (mc.thePlayer.isCollidedHorizontally) {
//                    dyrektor = -dyrektor;
//                } else {
//                    if (mc.gameSettings.keyBindLeft.isKeyDown()) dyrektor = 1;
//                    if (mc.gameSettings.keyBindRight.isKeyDown()) dyrektor = -1;
//                }
//            }
//        }
//    }


}
