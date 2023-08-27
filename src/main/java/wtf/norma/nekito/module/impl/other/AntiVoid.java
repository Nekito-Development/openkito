package wtf.norma.nekito.module.impl.other;

import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import me.zero.alpine.listener.Subscriber;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.Nekito;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.event.impl.update.EventUpdate;
import wtf.norma.nekito.settings.impl.NumberSetting;
import wtf.norma.nekito.util.player.MovementUtil;


public class AntiVoid extends Module implements Subscriber {


    public NumberSetting falldisc = new NumberSetting("Fall Distance", 3, 1, 10, 1);

    public AntiVoid() {
        super("Anti Void", Category.OTHER, Keyboard.KEY_NONE);
        addSettings(falldisc);
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
        if (czyjestwvoid() && mc.thePlayer.fallDistance > falldisc.getValue())
            mc.thePlayer.setVelocity(0, 0, 0);
    });

//    @Override
//    public void onEvent(Event e) {
//        if (e instanceof EventUpdate) {
//            if (czyjestwvoid() && mc.thePlayer.fallDistance > falldisc.getValue()) {
//                mc.thePlayer.setVelocity(0, 0, 0);
//            }
//        }
//    }
}
