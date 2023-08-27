package wtf.norma.nekito.module.impl.hud;

import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.settings.impl.BooleanSetting;


public class InventorySettings extends Module {


    public static BooleanSetting shader = new BooleanSetting("Shader in Inventory", true);
    public static BooleanSetting anime = new BooleanSetting("Anime in Inventory", true);

    public InventorySettings() {
        super("Gui Settings", Category.HUD, Keyboard.KEY_NONE);
        addSettings(shader, anime);
    }

    @Override
    public void onEnable() {
        this.setToggled(false);
        super.onEnable();
    }

}


