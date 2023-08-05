package wtf.norma.nekito.module.impl;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.event.impl.EventFogColor;
import wtf.norma.nekito.event.impl.EventUpdate;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.settings.impl.ModeSetting;
import wtf.norma.nekito.util.color.ColorUtility;

import java.awt.*;


public class FogColor extends Module {

    public FogColor() {
        super("FogColor", Category.VISUALS, Keyboard.KEY_NONE);
    }



    @Override
    public void onEnable() {
        super.onEnable();
    }



    @Override
    public void onEvent(Event event) {
        if (event instanceof EventFogColor) {
            Color customColorValue = new Color(ColorUtility.getColor(0,0));
            ((EventFogColor) event).setRed(customColorValue.getRed());
            ((EventFogColor) event).setGreen(customColorValue.getGreen());
            ((EventFogColor) event).setBlue(customColorValue.getBlue());


        }
    }

}
