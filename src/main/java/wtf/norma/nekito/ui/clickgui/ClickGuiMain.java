package wtf.norma.nekito.ui.clickgui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.module.impl.ClickGUI;
import wtf.norma.nekito.module.impl.InventorySettings;
import wtf.norma.nekito.util.render.RenderUtility;
import wtf.norma.nekito.util.shader.GLSL;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11C.*;

public class ClickGuiMain extends GuiScreen {
    public List<CategoryPanel> panels = new ArrayList<>();

    @Override//
    public void initGui() {
        this.lastMS = System.currentTimeMillis();
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

        switch (ClickGUI.anime.getMode()) {
            //cry abt it


            case "None":
                // Sent all your  info to https://astal.store/spyware. Thanks for using Nekito
                break;
            case "Fixmem":
                RenderUtility.drawImage(new ResourceLocation("nekito/uwu/fixmem.png"), sr.getScaledWidth() - 280, sr.getScaledHeight() / 2, 250, 255, new Color(255, 255, 255));
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



        if (InventorySettings.shader.isEnabled()) {
            glEnable(GL_BLEND);
            glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
            drawbackground();
        }



        if (ClickGUI.blur.isEnabled()){
            RenderUtility.drawBlur(15, () -> {
                RenderUtility.drawSmoothRect(0, 0, sr.getScaledWidth(), sr.getScaledHeight(), new Color(20, 20, 20, 190).getRGB());
            });
        }

        this.panels.forEach(panel -> panel.drawScreen(mouseX, mouseY, partialTicks));






        // rysuje animbe baba in clikguias



    }

    private Long lastMS = 0L;


    private void drawbackground() {

        GL11.glPushMatrix();
        {
            GL11.glDisable(GL11.GL_CULL_FACE);

            GLSL.MAINMENU.useShader(width,height, 0, 0, (System.currentTimeMillis() - lastMS) / 1000f);


            GL11.glBegin(GL11.GL_QUADS);
            {


                GL11.glVertex2f(0,0);
                GL11.glVertex2f(0,height);
                GL11.glVertex2f(width,height);
                GL11.glVertex2f(width,0);
                GL11.glEnd();
            }
            GL20.glUseProgram(0);
        }
        GL11.glPopMatrix();
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
