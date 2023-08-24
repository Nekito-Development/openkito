package wtf.norma.nekito.ui.crashgui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import wtf.norma.nekito.module.impl.CrashGUI;
import wtf.norma.nekito.util.render.RenderUtility;

import java.awt.*;

public class CrashGuiMain  extends GuiScreen {





    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        ScaledResolution sr = new ScaledResolution(mc);
        int colorx = 0;




        // rysuje animbe baba in clikguias


        switch (CrashGUI.anime.getMode()) {
            //cry abt it
            case "None":
                // Sent all your  info to https://astal.store/spyware. Thanks for using Nekito
                break;
            case "Fixmem":
                RenderUtility.drawImage(new ResourceLocation("nekito/uwu/fixmem.png"), sr.getScaledWidth() - 250, sr.getScaledHeight() / 2, 250, 255, new Color(255, 255, 255));
                break;
            case "Astolfo":
                RenderUtility.drawImage(new ResourceLocation("nekito/uwu/asstolfo.png"), sr.getScaledWidth() - 250, sr.getScaledHeight() / 2, 250, 255, new Color(255, 255, 255));
                break;
            case "Astolfo2":
                RenderUtility.drawImage(new ResourceLocation("nekito/uwu/astoflo4.png"), sr.getScaledWidth() - 250, sr.getScaledHeight() / 2, 250, 255, new Color(255, 255, 255));
                break;
            case "BabaWithPlecak":
                RenderUtility.drawImage(new ResourceLocation("nekito/uwu/baba.png"), sr.getScaledWidth() - 250, sr.getScaledHeight() / 2, 250, 255, new Color(255, 255, 255));
                break;
            case "Hideri":
                RenderUtility.drawImage(new ResourceLocation("nekito/uwu/hideri.png"), sr.getScaledWidth() - 250, sr.getScaledHeight() / 2, 250, 255, new Color(255, 255, 255));
                break;
            case "Shiina":
                RenderUtility.drawImage(new ResourceLocation("nekito/uwu/Shiina.png"), sr.getScaledWidth() - 250, sr.getScaledHeight() / 2, 250, 255, new Color(255, 255, 255));
                break;
            case "Felix":
                RenderUtility.drawImage(new ResourceLocation("nekito/uwu/felix.png"), sr.getScaledWidth() - 250, sr.getScaledHeight() / 2, 250, 255, new Color(255, 255, 255));
                break;
            case "Cot":
                RenderUtility.drawImage(new ResourceLocation("nekito/uwu/cot.png"), sr.getScaledWidth() - 250, sr.getScaledHeight() / 2, 250, 255, new Color(255, 255, 255));
                break;
            case "ten rekin z ikea":
                //
                RenderUtility.drawImage(new ResourceLocation("nekito/uwu/tenvtuber.png"), sr.getScaledWidth() - 250, sr.getScaledHeight() / 2, 250, 255, new Color(255, 255, 255));
                break;


        }
    }






}
