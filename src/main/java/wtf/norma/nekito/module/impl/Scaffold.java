package wtf.norma.nekito.module.impl;

import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.event.impl.EventUpdate;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.settings.impl.NumberSetting;


public class Scaffold extends Module {

    public static NumberSetting reachValue = new NumberSetting("Expand", 3.2f, 3.0f, 5.0f, 0.01f);

    public Scaffold() {
        super("Scaffold", Category.MOVEMENT, Keyboard.KEY_NONE);
        addSettings(reachValue);
    }





    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }


}
