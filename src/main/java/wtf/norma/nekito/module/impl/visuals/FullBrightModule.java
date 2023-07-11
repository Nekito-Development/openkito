package wtf.norma.nekito.module.impl.visuals;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.module.ModuleCategory;
import wtf.norma.nekito.module.ModuleInfo;
import wtf.norma.nekito.module.value.impl.ModeValue;

@ModuleInfo(
        name = "FullBright",
        moduleCategory = ModuleCategory.VISUALS
)
public class FullBrightModule extends Module {

    public final ModeValue mode = new ModeValue("Mode", "Gamma", "Gamma", "Potion");
    private float oldGamma;

    @Override
    public void onEnable() {
        oldGamma = mc.gameSettings.gammaSetting;
        switch (mode.get()) {
            case "Gamma": {
                mc.gameSettings.gammaSetting = 100;
                break;
            }
            case "Potion": {
                mc.thePlayer.addPotionEffect(new PotionEffect(Potion.nightVision.getId(), Integer.MAX_VALUE, 1, false, false));
                break;
            }
        }
    }

    @Override
    public void onDisable() {
        switch (mode.get()) {
            case "Gamma":
                mc.gameSettings.gammaSetting = oldGamma;
                break;

            case "Potion":
                mc.thePlayer.removePotionEffect(Potion.nightVision.getId());
                break;
        }
    }
}
