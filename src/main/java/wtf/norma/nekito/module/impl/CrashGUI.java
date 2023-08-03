package wtf.norma.nekito.module.impl;

import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.LWJGLUtil;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.nekito;
import wtf.norma.nekito.settings.impl.BooleanSetting;
import wtf.norma.nekito.settings.impl.ModeSetting;

public class CrashGUI extends Module {

    public CrashGUI() {
        super("CrashGUI", Category.VISUALS, Keyboard.KEY_INSERT);
        this.addSettings(anime,blur);
    }





    public static ModeSetting anime = new ModeSetting("Anime", "Fixmem", "Fixmem", "Astolfo", "Astolfo2","BabaWithPlecak",
            "Hideri","Felix","Cot","ten rekin z ikea","None");

    public static BooleanSetting blur = new BooleanSetting("Blur",false);


    @Override
    public void onEnable() {
        super.onEnable();
       mc.displayGuiScreen(nekito.INSTANCE.getCrashGui());

        toggle();
    }

}
