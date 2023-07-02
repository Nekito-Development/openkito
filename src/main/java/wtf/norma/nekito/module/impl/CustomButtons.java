package wtf.norma.nekito.module.impl;

import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.settings.impl.ModeSetting;

public class CustomButtons extends Module {
    public static ModeSetting rect = new ModeSetting("Rect", "Rounded", "Rounded", "Texture");
    public static ModeSetting font = new ModeSetting("Font", "Custom", "Custom", "Minecraft");

    public CustomButtons() {
        super("CustomButtons", Category.VISUALS, Keyboard.KEY_NONE);
        this.addSettings(rect, font);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        toggle();
    }
}
