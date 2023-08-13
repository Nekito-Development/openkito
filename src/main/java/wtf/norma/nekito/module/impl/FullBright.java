package wtf.norma.nekito.module.impl;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.event.impl.EventUpdate;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.settings.impl.ModeSetting;

public class FullBright extends Module {

    private final ModeSetting mode = new ModeSetting("Mode", "Gamma", "Gamma", "Potion");
    private float oldGamma;
    public FullBright() {
        super("FullBright", Category.VISUALS, Keyboard.KEY_NONE);
        //    toggle();
        this.addSettings(mode);
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        switch (mode.getMode()) {
            case "Gamma":
                mc.gameSettings.gammaSetting = oldGamma;
                break;

            case "Potion":
                mc.thePlayer.removePotionEffect(Potion.nightVision.getId());
                break;
        }
    }

    @Override
    public void onEvent(Event e) {
        if (e instanceof EventUpdate) {

            switch (mode.getMode()) {
                case "Gamma":
                    mc.gameSettings.gammaSetting = 100;
                    break;
                case "Potion":
                    mc.thePlayer.addPotionEffect(new PotionEffect(Potion.nightVision.getId(), Integer.MAX_VALUE));
                    break;
            }

        }
    }
}
