package wtf.norma.nekito.module.impl;

import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.module.Module;


public class Criticals extends Module {


    public Criticals() {
        super("Criticals", Category.COMBAT, Keyboard.KEY_NONE);
    }


    @Override
    public void onEvent(Event e) {
    }


}
