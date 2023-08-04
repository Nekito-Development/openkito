package wtf.norma.nekito.module.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.event.impl.EventRender2D;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.settings.impl.BooleanSetting;
import wtf.norma.nekito.util.color.ColorUtility;
import wtf.norma.nekito.util.font.Fonts;
import wtf.norma.nekito.util.funny.DDOSUTIL;

import java.io.IOException;


public class InventorySettings extends Module {


    public InventorySettings() {
        super("Inventory Settings", Category.VISUALS, Keyboard.KEY_NONE);
        addSettings(shader,anime);
    }


    public static BooleanSetting shader = new BooleanSetting("Shader in Inventory", true);

    public static BooleanSetting anime = new BooleanSetting("Anime in Inventory", true);


    @Override
    public void onEnable() {
        this.setToggled(false);
        super.onEnable();
    }




    @Override
    public void onEvent(Event e) {


    }
}


