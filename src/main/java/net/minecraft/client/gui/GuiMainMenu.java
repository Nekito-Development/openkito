package net.minecraft.client.gui;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import wtf.norma.nekito.ui.gui.GuiAltManager;
import wtf.norma.nekito.helper.font.FontHelper;
import wtf.norma.nekito.helper.render.BlurHelper;
import wtf.norma.nekito.helper.shader.GLSL;
import java.awt.*;
import java.io.IOException;

public class GuiMainMenu extends GuiScreen {

    private int width;
    public float scale = 2;
    private int height;
    public long init;
    private final long initTime = System.currentTimeMillis();
    private double scrollOffset;

    public GuiMainMenu() {

    }

    int offset = 50;

    @Override
    public void initGui() {
        ScaledResolution sr = new ScaledResolution(this.mc);
        this.width = sr.getScaledWidth();
        this.height = sr.getScaledHeight();

        this.buttonList.add(new GuiButton(0, (this.width / 2) - 45, (int) (this.height / 2 + 4 / 1.4 - offset), 90, 18, "SinglePlayer"));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 45, (int) (this.height / 2 + 32 / 1.4 - offset), 90, 18, "MultiPlayer"));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 45, (int) (this.height / 2 + 60 / 1.4 - offset), 90, 18, "AltManager"));
        this.buttonList.add(new GuiButton(3, this.width / 2 - 45, (int) (this.height / 2 + 88 / 1.4 - offset), 90, 18, "Settings"));
        this.buttonList.add(new GuiButton(4, this.width / 2 - 45, (int) (this.height / 2 + 116 / 1.4 - offset), 90, 18, "Quit"));
        init = System.currentTimeMillis();
    }


    private Color gradientColor1 = Color.WHITE, gradientColor2 = Color.WHITE, gradientColor3 = Color.WHITE, gradientColor4 = Color.WHITE;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        ScaledResolution sr = new ScaledResolution(mc);

        int widthRound = 240;
        int heightRound;

        boolean small = mc.displayWidth < 900 && mc.displayHeight < 900;

        if (small) {
            heightRound = 200;

        } else {
            heightRound = 215;
        }
        int x = sr.getScaledWidth() / 2 - widthRound / 2;
        int y = sr.getScaledHeight() / 2 - 100;

        drawbackground();


        BlurHelper.drawShadow(5, 2, () -> {
            FontHelper.MONTSERRAT45.drawCenteredString("NEKITO", x + widthRound / 2 + 1, (sr.getScaledHeight() / 2) - 40 - offset, -1);
        }, Color.WHITE);
        FontHelper.MONTSERRAT45.drawCenteredString("NEKITO", x + widthRound / 2 + 1, (sr.getScaledHeight() / 2) - 40 - offset, -1);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 0:
                this.mc.displayGuiScreen(new GuiSelectWorld(this));
                break;
            case 1:
                this.mc.displayGuiScreen(new GuiMultiplayer(this));
                break;
            case 2:
                this.mc.displayGuiScreen(new GuiAltManager(this));
                break;
            case 3:
                this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
                break;
            case 4:
                System.exit(0);
                break;
        }

        super.actionPerformed(button);
    }

    public void drawbackground() {
        GL11.glPushMatrix();
        {
            GL11.glDisable(GL11.GL_CULL_FACE);
            GLSL.MAIN_MENU.useShader(width,height, 0, 0, (System.currentTimeMillis() - init) / 1000f);


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
}