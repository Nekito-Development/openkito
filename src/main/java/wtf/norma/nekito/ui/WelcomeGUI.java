package wtf.norma.nekito.ui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.EnumChatFormatting;
import wtf.norma.nekito.util.font.Fonts;
import wtf.norma.nekito.util.render.CwelUtility;
import wtf.norma.nekito.util.render.RenderUtility;

import java.awt.*;
import java.io.IOException;

public class WelcomeGUI extends GuiScreen {

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        try {
            RenderUtility.drawRect(0, 0, mc.displayWidth, mc.displayHeight, new Color(90, 90, 90, 100).getRGB());
            //CwelUtility.renderGray();

            Fonts.SEMI_BOLD_18.drawCenteredStringWithShadow("Welcome in Nekito!", sr.getScaledWidth() / 2, sr.getScaledHeight() / 2 - 30, -1);
            Fonts.SEMI_BOLD_16.drawCenteredStringWithShadow("Join our Discord: https://discord.gg/PXUm6zydUv ", sr.getScaledWidth() / 2, sr.getScaledHeight() / 2 - 14, -1);
            Fonts.SEMI_BOLD_16.drawCenteredStringWithShadow("Client made by: Intexpression, groszus, lekoz and kacorvixon", sr.getScaledWidth() / 2, sr.getScaledHeight() / 2, -1);
            Fonts.SEMI_BOLD_16.drawCenteredStringWithShadow(EnumChatFormatting.UNDERLINE + "Have Fun Crashing Servers! ", sr.getScaledWidth() / 2 + 1, sr.getScaledHeight() / 2 + 30, -1);

            Fonts.SEMI_BOLD_16.drawCenteredStringWithShadow("To leave this screen click 'ESC'", sr.getScaledWidth() / 2, sr.getScaledHeight() - 15, -1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void keyTyped(char par1, int par2) throws IOException {
        super.keyTyped(par1, par2);

    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}