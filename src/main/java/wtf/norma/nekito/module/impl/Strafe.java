package wtf.norma.nekito.module.impl;

import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.event.impl.EventUpdate;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.util.Time.TimerUtility;
import wtf.norma.nekito.util.player.MovementUtil;

/**
 * @author eleczka
 * @project nekito
 * @prod hackerzy mysliborz S.A
 * @at 08.08, 20:40
 */

public class Strafe extends Module {


    public static boolean cipendejs;
    TimerUtility CWEL = new TimerUtility();
    private int ostatnitick;
    private boolean timerichuj;

    public Strafe() {
        super("Strafe", Category.MOVEMENT, Keyboard.KEY_NONE);

    }

    @Override
    public void onEvent(Event e) {
        if (e instanceof EventUpdate) {
            if (MovementUtil.isMoving()) {
                MovementUtil.strafe((double) MovementUtil.getSpeed());
            }
        }
    }
}