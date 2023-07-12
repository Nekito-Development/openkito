package wtf.norma.nekito.module.impl;

import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.settings.impl.NumberSetting;


public class HitBox extends Module {

    public static NumberSetting size = new NumberSetting("Size", 0.2f, 0.0f, 3.0f, 0.05f);

    public HitBox() {
        super("HitBox", Category.LEGIT, Keyboard.KEY_NONE);
        addSettings(size);
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
