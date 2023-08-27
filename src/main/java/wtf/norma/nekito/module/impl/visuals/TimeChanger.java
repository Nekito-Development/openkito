package wtf.norma.nekito.module.impl.visuals;

import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import me.zero.alpine.listener.Subscriber;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.Nekito;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.event.impl.update.EventUpdate;
import wtf.norma.nekito.settings.impl.ModeSetting;
import wtf.norma.nekito.settings.impl.NumberSetting;


public class TimeChanger extends Module implements Subscriber {

    public ModeSetting time = new ModeSetting("Mode", "Day", "Day", "Night", "Custom");
    public NumberSetting customtime = new NumberSetting("Custom Time", 100, 100, 24000, 100);

    public TimeChanger() {
        super("Time Changer", Category.VISUALS, Keyboard.KEY_NONE);
        addSettings(time, customtime);
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
        switch (time.getMode()) {
            case "Day":
                mc.theWorld.setWorldTime(1000);
                break;
            case "Night":
                mc.theWorld.setWorldTime(18000);
                break;
            case "Custom":
                mc.theWorld.setWorldTime((long) customtime.getValue());
                break;
        }
    });

//    @Override
//    public void onEvent(Event e) {
//        if (e instanceof EventUpdate) {
//            switch (time.getMode()) {
//                case "Day":
//                    mc.theWorld.setWorldTime(1000);
//                    break;
//                case "Night":
//                    mc.theWorld.setWorldTime(18000);
//                    break;
//                case "Custom":
//                    mc.theWorld.setWorldTime((long) customtime.getValue());
//                    break;
//            }
//        }
//    }
}
