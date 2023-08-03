package wtf.norma.nekito.module.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.event.impl.EventRender2D;
import wtf.norma.nekito.helper.OpenGlHelper;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.nekito;
import wtf.norma.nekito.settings.impl.BooleanSetting;
import wtf.norma.nekito.settings.impl.ModeSetting;
import wtf.norma.nekito.util.render.RenderUtility;

import java.awt.*;


public class Watermark extends Module {
    public Watermark() {
        super("Watermark", Category.VISUALS, Keyboard.KEY_NONE);
        addSettings(colorMode);
    }




    public static ModeSetting colorMode = new ModeSetting("Color", "Rainbow", "Rainbow","Nekito", "Purple","Pink");



    @Override
    public void onEvent(Event e) {

    }

    @Override
    public void onEnable() {
            nekito.INSTANCE.getDraggableManager().<wtf.norma.nekito.draggable.impl.Watermark>Get("Watermark").AllowRender = true;
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
        nekito.INSTANCE.getDraggableManager().<wtf.norma.nekito.draggable.impl.Watermark>Get("Watermark").AllowRender = false;
    }
}
