package wtf.norma.nekito.module.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.event.impl.EventRender2D;
import wtf.norma.nekito.helper.ChatHelper;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.settings.impl.ModeSetting;
import wtf.norma.nekito.util.color.ColorUtility;
import wtf.norma.nekito.util.color.ColorUtils;
import wtf.norma.nekito.util.font.Fonts;
import wtf.norma.nekito.util.render.RenderUtility;

import java.awt.*;


public class bandytakamera extends Module {


    // nudzilo mi sie ok?


    public bandytakamera() {
        super("bandyta kamera", Category.VISUALS, Keyboard.KEY_NONE);
    }

    @Override
    public void onEnable() {
        ChatHelper.printMessage("this module is in beta(you can have issues with it)");
        ChatHelper.printMessage("If its bugged or dark wait 8secs");
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
               if(mc.gameSettings.thirdPersonView <= 0 ) {
                   RenderUtility.drawImage(new ResourceLocation("images/cwelowate/bandicam.png"), sr.getScaledWidth() / 2 - 200, 1, 400, 100, new Color(255, 255, 255));
                 }
        }
    }



}
