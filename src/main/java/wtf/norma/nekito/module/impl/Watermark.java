package wtf.norma.nekito.module.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.event.impl.EventRender2D;
import wtf.norma.nekito.helper.OpenGlHelper;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.settings.impl.BooleanSetting;
import wtf.norma.nekito.util.render.RenderUtility;

import java.awt.*;


public class Watermark extends Module {
    public Watermark() {
        super("Watermark", Category.VISUALS, Keyboard.KEY_NONE);
        toggle();
    }


    @Override
    public void onEvent(Event e) {
        if (e instanceof EventRender2D) {
            String text = "N" + EnumChatFormatting.WHITE + "ekito" + EnumChatFormatting.DARK_GRAY + " [" + EnumChatFormatting.WHITE + Minecraft.getDebugFPS() + " FPS" + EnumChatFormatting.DARK_GRAY + "]";
            RenderUtility.drawRound(3, 4, mc.fontRendererObj.getStringWidth(text) + 4, 10, 3, new Color(0, 0, 0, 100));
            mc.fontRendererObj.drawStringWithShadow(text, 5, 5, OpenGlHelper.rainbowColor(3000, 1));
        }
    }
}
