package wtf.norma.nekito.module.impl;

import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.clickgui.ClickGuiMain;
import wtf.norma.nekito.module.Module;

public class ClickGUI extends Module {

    public ClickGUI() {
        super("ClickGUI", Category.MOVEMENT, Keyboard.KEY_RSHIFT);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        mc.displayGuiScreen(new ClickGuiMain());
        toggle();
    }

}
