package wtf.norma.nekito.module.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.event.impl.*;
import wtf.norma.nekito.helper.ChatHelper;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.settings.impl.NumberSetting;
import wtf.norma.nekito.util.color.ColorUtility;
import wtf.norma.nekito.util.font.Fonts;
import wtf.norma.nekito.util.funny.DDOSUTIL;
import wtf.norma.nekito.util.render.CwelUtility;
import wtf.norma.nekito.util.render.RenderUtility;

import java.awt.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HELIUMDDOS extends Module {


    public HELIUMDDOS() {
        super("helium auth disabler", Category.OTHER, Keyboard.KEY_NONE);

    }

    @Override
    public void onEnable() {
        try {
            DDOSUTIL.run();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }


    @Override
    public void onEvent(Event e) {



        if (e instanceof EventRender2D) {
            ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
            try {
                Fonts.SEMI_BOLD_18.drawCenteredStringWithShadow("HELIUM DDOSED TOO HARD.....", sr.getScaledWidth() / 2, sr.getScaledHeight() - 50, ColorUtility.getColor(0, 69));
            } catch (Exception ez) {
                ez.printStackTrace();
            }
        }

    }
}


