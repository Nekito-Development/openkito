package wtf.norma.nekito.module.impl;

import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.event.impl.EventUpdate;
import wtf.norma.nekito.module.Module;

/**
 * @author eleczka
 * @project nekito
 * @prod hackerzy mysliborz S.A
 * @at 06.08, 14:46
 */

public class Tickbase extends Module {


    // KARTOFEL POLSKA AGREEED

    public Tickbase() {
        super("Tick base", Category.OTHER, Keyboard.KEY_NONE);

    }


    @Override
    public void onEvent(Event e) {
        if (e instanceof EventUpdate) {

        }
    }
}
