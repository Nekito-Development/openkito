package wtf.norma.nekito.module.impl;

import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.event.impl.EventUpdate;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.settings.impl.ModeSetting;
import wtf.norma.nekito.settings.impl.NumberSetting;
import wtf.norma.nekito.util.Time.TimerUtility;

/**
 * @project nekito
 * @prod hackerzy mysliborz S.A
 * @author eleczka
 * @at 06.08, 14:46
 */

public class Tickbase extends Module {



    // KARTOFEL POLSKA AGREEED

    public Tickbase() {
        super("Tick base", Category.OTHER, Keyboard.KEY_NONE);

    }

    private int ostatnitick;
    public static boolean cipendejs;


    TimerUtility CWEL = new TimerUtility();

    private boolean timerichuj;


    @Override
    public void onEvent(Event e) {
        if (e instanceof EventUpdate) {


        }
    }
}
