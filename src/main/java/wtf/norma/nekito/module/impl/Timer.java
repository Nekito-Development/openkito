package wtf.norma.nekito.module.impl;

import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.settings.impl.NumberSetting;


public class Timer extends Module {


    public NumberSetting nobardmud = new NumberSetting("Speed", 1.0f, 1f, 10.0f, 0.1f);

    public Timer() {

        super("Timer", Category.OTHER, Keyboard.KEY_NONE);
        addSettings(nobardmud);
    }

    public void onEvent(Event event) {
        mc.timer.timerSpeed = nobardmud.getValue();
    }


    @Override
    public void onEnable() {
        mc.timer.timerSpeed = 1;
        super.onEnable();
    }


    @Override
    public void onDisable() {
        mc.timer.timerSpeed = 1;
        super.onDisable();
    }


}
