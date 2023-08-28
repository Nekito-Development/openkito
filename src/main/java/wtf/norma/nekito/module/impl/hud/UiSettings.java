package wtf.norma.nekito.module.impl.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.Nekito;
import wtf.norma.nekito.settings.impl.BooleanSetting;
import wtf.norma.nekito.settings.impl.ModeSetting;
import wtf.norma.nekito.util.color.ColorUtility;
import wtf.norma.nekito.util.font.Fonts;
import wtf.norma.nekito.util.player.MovementUtil;

import java.awt.*;


public class UiSettings extends Module {
    public UiSettings() {
        super("Ui Settings", Category.HUD, Keyboard.KEY_NONE);
        addSettings(colorMode,fpseating,cwel,normamode);
    }


    public static BooleanSetting cwel = new BooleanSetting("Player Info", false);


    public static BooleanSetting fpseating = new BooleanSetting("Render Glow", true);


    public static ModeSetting colorMode = new ModeSetting("Color", "Nekito", "Nekito", "Rainbow", "Purple", "Pink");


    public static BooleanSetting normamode = new BooleanSetting("Norma 0.1 hud", false);





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
        }//cwel

        if (normamode.isEnabled()){

            // it is from norma 0.1 i was really bad at coding that time lol

            // norma 0.1 was made like 2/3 years ago 😋




            //watermark
            mc.fontRendererObj.drawStringWithShadow("N" + EnumChatFormatting.GRAY + "ekito" + " "  + EnumChatFormatting.GOLD + "[" + Nekito.version + "]", 3, 3, ColorUtility.getColor(0));


            // xyz
            mc.fontRendererObj.drawStringWithShadow("XYZ§7 : " + (int)mc.thePlayer.posX + ", " + (int)mc.thePlayer.posY + ", " + (int)mc.thePlayer.posZ, 2, sr.getScaledHeight() - 10, ColorUtility.getColor(0));


            // fps
            mc.fontRendererObj.drawStringWithShadow("FPS §7: " + Minecraft.debugFPS, 2, sr.getScaledHeight() - 20, ColorUtility.getColor(0));





            //arraylist
            int y = 13;


            for (final Module mod : Nekito.INSTANCE.getModuleManager().getModules()) {
                if (mod.isToggled()) {
                    mc.fontRendererObj.drawStringWithShadow(mod.getName(), 3, y, ColorUtility.getColor(0));
                    y += mc.fontRendererObj.FONT_HEIGHT;
                }
            }
        }


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
