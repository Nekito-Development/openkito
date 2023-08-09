package wtf.norma.nekito.module.impl;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.event.impl.EventUpdate;
import wtf.norma.nekito.event.impl.PacketEvent;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.settings.impl.BooleanSetting;
import wtf.norma.nekito.settings.impl.NumberSetting;
import wtf.norma.nekito.util.Time.TimerUtility;


public class Timer extends Module {



    public Timer() {

        super("Timer", Category.OTHER, Keyboard.KEY_NONE);
        addSettings(nobardmud);
    }

    public NumberSetting nobardmud = new NumberSetting("Speed",1.0f,1f,10.0f,0.1f);


    public void onEvent(Event event) {
        mc.timer.timerSpeed = (float) nobardmud.getValue();
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
