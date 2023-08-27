package wtf.norma.nekito.module.impl;

import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.Nekito;
import wtf.norma.nekito.settings.impl.BooleanSetting;
import wtf.norma.nekito.settings.impl.ModeSetting;

public class CrashGUI extends Module {

    public static ModeSetting anime = new ModeSetting("Anime", "Fixmem", "Fixmem", "Astolfo", "Astolfo2", "BabaWithPlecak",
            "Hideri", "Felix", "Cot", "ten rekin z ikea", "None");
    public static BooleanSetting blur = new BooleanSetting("Blur", false);

    public CrashGUI() {
        super("CrashGUI", Category.HUD, Keyboard.KEY_INSERT);
        this.addSettings(anime, blur);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        mc.displayGuiScreen(Nekito.INSTANCE.getCrashGuiMain());

        toggle();
    }

}
