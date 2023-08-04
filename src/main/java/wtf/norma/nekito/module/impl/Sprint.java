package wtf.norma.nekito.module.impl;

import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.event.impl.EventUpdate;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.nekito;

public class Sprint extends Module {

    public Sprint() {
        super("Sprint", Category.MOVEMENT, Keyboard.KEY_NONE);
    }



    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
        mc.gameSettings.keyBindSprint.pressed = false;
    }

    @Override
    public void onEvent(Event e) {
        if (e instanceof EventUpdate) {
            // SLAYYYYYYYYYYYYYYY 🙄🙄🙄🙄🙄🙄🙄🙄🙄🙄🙄😴🥱😫😪😪
                mc.gameSettings.keyBindSprint.pressed = true;

        }
    }
}
