package wtf.norma.nekito.module.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.event.impl.EventRender2D;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.nekito;
import wtf.norma.nekito.settings.impl.BooleanSetting;
import wtf.norma.nekito.settings.impl.ModeSetting;
import wtf.norma.nekito.util.color.ColorUtility;
import wtf.norma.nekito.util.font.Fonts;
import wtf.norma.nekito.util.player.MovementUtil;

import java.awt.*;
import java.util.Map;


public class UiSettings extends Module {
    public UiSettings() {
        super("Ui Settings", Category.VISUALS, Keyboard.KEY_NONE);
        addSettings(colorMode,cwel);
    }


    public static BooleanSetting cwel = new BooleanSetting("Player Info", false);

    public static ModeSetting colorMode = new ModeSetting("Color", "Nekito", "Nekito", "Rainbow", "Purple", "Pink");






    public void draw(ScaledResolution sr) {
        if(cwel.isEnabled()) {
            String xyz = (int) mc.thePlayer.posX + ", " + (int) mc.thePlayer.posY + ", " + (int) mc.thePlayer.posZ;
            Color clr = new Color(ColorUtility.getColor(0));
            Fonts.gay2.drawString("FPS: ", 9 + Fonts.gay2.getStringWidth("Speed: ") + Fonts.gay2.getStringWidth("XYZ: ") + Fonts.gay2.getStringWidth(xyz + String.format("%.1f", MovementUtil.getSpeed()) + " b/s"), sr.getScaledHeight() - 9, clr.getRGB(), false);
            Fonts.gay2.drawString(String.valueOf(Minecraft.getDebugFPS()), 9 + Fonts.gay2.getStringWidth("FPS: ") + Fonts.gay2.getStringWidth("XYZ: " + xyz) + Fonts.gay2.getStringWidth("Speed: " + String.format("%.1f", MovementUtil.getSpeed()) + " b/s"), sr.getScaledHeight() - 9, -1, false);

            Fonts.gay2.drawString("Speed: ", 8 + Fonts.gay2.getStringWidth("XYZ: ") + Fonts.gay2.getStringWidth(xyz), sr.getScaledHeight() - 9, clr.getRGB(), false);
            Fonts.gay2.drawString(String.format("%.1f", MovementUtil.getSpeed()) + " b/s", 6 + Fonts.gay2.getStringWidth("Speed: ") + Fonts.gay2.getStringWidth("XYZ: ") + Fonts.gay2.getStringWidth(xyz), sr.getScaledHeight() - 9, -1, false);
            Fonts.gay2.drawString("XYZ: ", 2, sr.getScaledHeight() - 9, clr.getRGB(), false);
            Fonts.gay2.drawString(xyz, 2 + Fonts.gay2.getStringWidth("XYZ: "), sr.getScaledHeight() - 9, -1, false);
        }//


    }





    @Override
    public void onEnable() {
        super.onEnable();
        toggle();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
