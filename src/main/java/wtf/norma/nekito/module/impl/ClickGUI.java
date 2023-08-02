package wtf.norma.nekito.module.impl;

import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.nekito;
import wtf.norma.nekito.settings.impl.ModeSetting;

public class ClickGUI extends Module {

    public ClickGUI() {
        super("ClickGUI", Category.VISUALS, Keyboard.KEY_RSHIFT);
        this.addSettings(anime);
    }

    public static ModeSetting anime = new ModeSetting("Anime", "Fixmem", "Fixmem", "Astolfo", "Astolfo2","BabaWithPlecak",
            "Hideri","Felix","Cot","ten rekin z ikea","None");


    @Override
    public void onEnable() {
        super.onEnable();
        mc.displayGuiScreen(nekito.INSTANCE.getClickGui());
        toggle();
    }

}
