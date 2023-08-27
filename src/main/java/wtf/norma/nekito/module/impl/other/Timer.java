package wtf.norma.nekito.module.impl.other;

import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import me.zero.alpine.listener.Subscriber;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.Nekito;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.event.impl.update.EventUpdate;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.settings.impl.NumberSetting;


public class Timer extends Module implements Subscriber {


    public NumberSetting nobardmud = new NumberSetting("Speed", 1.0f, 1f, 10.0f, 0.1f);

    public Timer() {

        super("Timer", Category.OTHER, Keyboard.KEY_NONE);
        addSettings(nobardmud);
    }



    @Override
    public void onEnable() {
        Nekito.EVENT_BUS.subscribe(this);
        mc.timer.timerSpeed = 1;
        super.onEnable();
    }


    @Override
    public void onDisable() {
        Nekito.EVENT_BUS.unsubscribe(this);
        mc.timer.timerSpeed = 1;
        super.onDisable();
    }

    @Subscribe
    private final Listener<EventUpdate> listener = new Listener<>(event ->{
        mc.timer.timerSpeed = nobardmud.getValue();
    });

}
