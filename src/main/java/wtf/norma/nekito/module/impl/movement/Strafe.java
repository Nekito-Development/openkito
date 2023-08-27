package wtf.norma.nekito.module.impl.movement;

import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import me.zero.alpine.listener.Subscriber;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.Nekito;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.event.impl.update.EventUpdate;
import wtf.norma.nekito.util.Time.TimerUtility;
import wtf.norma.nekito.util.player.MovementUtil;

/**
 * @author eleczka
 * @project nekito
 * @prod hackerzy mysliborz S.A
 * @at 08.08, 20:40
 */

public class Strafe extends Module implements Subscriber {


    public static boolean cipendejs;
    TimerUtility CWEL = new TimerUtility();
    private int ostatnitick;
    private boolean timerichuj;

    public Strafe() {
        super("Strafe", Category.MOVEMENT, Keyboard.KEY_NONE);
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
        if (MovementUtil.isMoving())
            MovementUtil.strafe((double) MovementUtil.getSpeed());
    });

//    @Override
//    public void onEvent(Event e) {
//        if (e instanceof EventUpdate) {
//            if (MovementUtil.isMoving()) {
//                MovementUtil.strafe((double) MovementUtil.getSpeed());
//            }
//        }
//    }
}