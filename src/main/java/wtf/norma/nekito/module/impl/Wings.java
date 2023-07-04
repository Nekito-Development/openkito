package wtf.norma.nekito.module.impl;

import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.settings.impl.ModeSetting;
import wtf.norma.nekito.settings.impl.NumberSetting;

import java.util.ArrayList;

public class Wings extends Module {

    public static NumberSetting selectedWing = new NumberSetting("wing type", 0, 0, 1, 1);
    public Wings() {
        super("Wings", Category.VISUALS, Keyboard.KEY_NONE);
        this.addSettings(selectedWing);
    }
}
