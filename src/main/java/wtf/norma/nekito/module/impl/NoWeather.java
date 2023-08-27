package wtf.norma.nekito.module.impl;

import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import me.zero.alpine.listener.Subscriber;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.Nekito;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.event.impl.update.EventUpdate;


public class NoWeather extends Module implements Subscriber {

    public NoWeather() {
        super("No Weather", Category.VISUALS, Keyboard.KEY_NONE);
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
        mc.theWorld.setThunderStrength(0);
        mc.theWorld.setRainStrength(0);
    });


//    @Override
//    public void onEvent(Event e) {
//        if (e instanceof EventUpdate) {
//            mc.theWorld.setThunderStrength(0);
//            mc.theWorld.setRainStrength(0);
//        }
//    }


}
