package wtf.norma.nekito.module.impl;

import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.module.Module;

public class Arraylist extends Module {
    public Arraylist() {
        super("Arraylist", Category.VISUALS, Keyboard.KEY_NONE);
        toggle();
    }
}
