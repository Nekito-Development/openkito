package wtf.norma.nekito.module.impl.legit;

import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.settings.impl.BooleanSetting;
import wtf.norma.nekito.settings.impl.NumberSetting;

public class KeepSprint extends Module {

    public KeepSprint() {
        super("KeepSprint", Category.LEGIT, Keyboard.KEY_NONE);
        addSettings(motionX,motionZ,setSprinting);
    }

    public static NumberSetting motionX = new NumberSetting("Motion X", 0.6f, 0.0f, 1.0f, 0.1f);
    public static NumberSetting motionZ = new NumberSetting("Motion Z", 0.6f, 0.0f, 1.0f, 0.1f);
    public static BooleanSetting setSprinting = new BooleanSetting("Set Sprinting", false);




    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }


}
