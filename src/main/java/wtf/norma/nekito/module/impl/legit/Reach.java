package wtf.norma.nekito.module.impl.legit;

import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.settings.impl.NumberSetting;


public class Reach extends Module {

    public static NumberSetting reachValue = new NumberSetting("Expand", 3.2f, 3.0f, 5.0f, 0.01f);

    public Reach() {
        super("Reach", Category.LEGIT, Keyboard.KEY_NONE);
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
