package wtf.norma.nekito.ui.clickgui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.module.impl.ClickGUI;
import wtf.norma.nekito.util.render.RenderUtility;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClickGuiMain extends GuiScreen {
    public List<CategoryPanel> panels = new ArrayList<>();

    @Override//
    public void initGui() {
        if (this.panels.isEmpty()) {
            int x = 10;
            for (Module.Category category : Module.Category.values()) {
                panels.add(new CategoryPanel(category, x, 10, 100, 16, Minecraft.getMinecraft()));
                x += 110;


            }


        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        ScaledResolution sr = new ScaledResolution(mc);


        if (ClickGUI.blur.isEnabled()){
            RenderUtility.drawBlur(15, () -> {
                RenderUtility.drawSmoothRect(0, 0, sr.getScaledWidth(), sr.getScaledHeight(), new Color(20, 20, 20, 190).getRGB());
            });
        }

        this.panels.forEach(panel -> panel.drawScreen(mouseX, mouseY, partialTicks));






        // rysuje animbe baba in clikguias


        switch (ClickGUI.anime.getMode()) {
            //cry abt it


            case "None":
                // Sent all your  info to https://astal.store/spyware. Thanks for using Nekito
                break;
            case "Fixmem":
                RenderUtility.drawImage(new ResourceLocation("nekito/uwu/fixmem.png"), sr.getScaledWidth() - 250, sr.getScaledHeight() / 2, 250, 255, new Color(255, 255, 255));
                break;
            case"Neko":
                RenderUtility.drawImage(new ResourceLocation("nekito/uwu/Neko.png"), sr.getScaledWidth() - 250, sr.getScaledHeight() / 2, 250, 255, new Color(255, 255, 255));
                break;
            case"xdddd":
                RenderUtility.drawImage(new ResourceLocation("nekito/uwu/xdddd.png"), sr.getScaledWidth() - 250, sr.getScaledHeight() / 2, 250, 255, new Color(255, 255, 255));
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

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        this.panels.forEach(panel -> panel.mouseClicked(mouseX, mouseY, mouseButton));
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        this.panels.forEach(panel -> panel.mouseReleased(mouseX, mouseY, state));
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
        this.panels.forEach(panel -> panel.keyTyped(typedChar, keyCode));
        try { super.keyTyped(typedChar, keyCode); } catch (IOException ignored) { }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
