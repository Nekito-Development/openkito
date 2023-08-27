package wtf.norma.nekito.module.impl.hud;

import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.Nekito;
import wtf.norma.nekito.settings.impl.BooleanSetting;
import wtf.norma.nekito.settings.impl.ModeSetting;

public class ClickGUI extends Module {

    public static ModeSetting anime = new ModeSetting("Anime", "Fixmem", "Fixmem", "Astolfo", "Astolfo2", "Neko", "xdddd", "BabaWithPlecak",
            "Hideri", "Felix", "Cot", "ten rekin z ikea", "None");
    public static BooleanSetting blur = new BooleanSetting("Blur", true);

    public ClickGUI() {
        super("ClickGUI", Category.HUD, Keyboard.KEY_RSHIFT);
        this.addSettings(anime, blur);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        mc.displayGuiScreen(Nekito.INSTANCE.getClickGuiMain());
        toggle();
    }

}
