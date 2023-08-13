package wtf.norma.nekito.module.impl;

import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.settings.impl.ModeSetting;

public class Wings extends Module {
    public static ModeSetting selectedWing = new ModeSetting("Wing type", "Wing1", "Wing1", "Wing2", "Wing3", "Wing4");

    public Wings() {
        super("Wings", Category.VISUALS, Keyboard.KEY_NONE);
        this.addSettings(selectedWing);
    }
}
