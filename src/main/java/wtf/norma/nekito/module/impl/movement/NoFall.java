package wtf.norma.nekito.module.impl.movement;

import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import me.zero.alpine.listener.Subscriber;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.Nekito;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.event.impl.movement.EventPreMotion;
import wtf.norma.nekito.settings.impl.ModeSetting;
import wtf.norma.nekito.util.player.MovementUtil;


public class NoFall extends Module implements Subscriber {


    public ModeSetting mode = new ModeSetting("Mode", "Vanilla", "Vanilla", "");


    public NoFall() {
        super("No Fall", Category.MOVEMENT, Keyboard.KEY_NONE);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        Nekito.EVENT_BUS.subscribe(this);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        Nekito.EVENT_BUS.unsubscribe(this);
    }

    @Subscribe
    private final Listener<EventPreMotion> listener = new Listener<>(event -> {
        if (mode.getMode().equals("Vanilla")) {
            if (!MovementUtil.isOnGround(0.001) && mc.thePlayer.motionY < 0)
                event.setOnGround(true);
        }
    });

//    @Override
//    public void onEvent(Event e) {
//        if (e instanceof EventPreMotion) {
//            if (mode.getMode().equals("Vanilla")) {
//                if (!MovementUtil.isOnGround(0.001) && mc.thePlayer.motionY < 0) {
//                    ((EventPreMotion) e).setOnGround(true);
//                }
//            }
//        }
//    }
}
