package wtf.norma.nekito.ui.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.EnumChatFormatting;
import wtf.norma.nekito.helper.font.FontHelper;
import wtf.norma.nekito.helper.render.RenderHelper;

import java.awt.*;
import java.io.IOException;

public class GarbageWelcomeScreen extends GuiScreen {

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        try {
            RenderHelper.drawRect(0, 0, mc.displayWidth, mc.displayHeight, new Color(0, 0, 0, 190).getRGB());
            //CwelUtility.renderGray();

            /// TYLKO MI TO USUNIESZ TO CIE KURWA ROZJEBIE

            FontHelper.SEMI_BOLD_18.drawCenteredStringWithShadow("Welcome in Nekito!", sr.getScaledWidth() / 2, sr.getScaledHeight() / 2 - 30, -1);
            FontHelper.SEMI_BOLD_16.drawCenteredStringWithShadow("Join our Discord: https://discord.gg/PXUm6zydUv ", sr.getScaledWidth() / 2, sr.getScaledHeight() / 2 - 14, -1);
            FontHelper.SEMI_BOLD_16.drawCenteredStringWithShadow("Client made by: Intexpression, groszus, lekoz and kacorvixon", sr.getScaledWidth() / 2, sr.getScaledHeight() / 2, -1);
            FontHelper.SEMI_BOLD_16.drawCenteredStringWithShadow(EnumChatFormatting.UNDERLINE + "Have Fun Crashing Servers! ", sr.getScaledWidth() / 2 + 1, sr.getScaledHeight() / 2 + 30, -1);

            FontHelper.SEMI_BOLD_16.drawCenteredStringWithShadow("To leave this screen click 'ESC'", sr.getScaledWidth() / 2, sr.getScaledHeight() - 15, -1);
        } catch (Exception ignored) {
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