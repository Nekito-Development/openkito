package wtf.norma.nekito.ui.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;

import java.io.IOException;


public class GuiTools extends GuiScreen {
    private static final Minecraft mc = Minecraft.getMinecraft();

    private final GuiScreen before;

    public GuiTools(GuiScreen before) {
        this.before = before;
    }

    public void initGui() {
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 3 - 40, "CheckHost"));
        this.buttonList.add(new GuiButton(42, this.width / 2 - 100, this.height / 3 - 15, "Spoofer"));
        this.buttonList.add(new GuiButton(0, this.width - (this.width / (this.width / 2) + this.width / 6) - 6, this.height - 26, this.width / (this.width / 2) + this.width / 6, 20, "Back"));
    }

    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 1:
                mc.displayGuiScreen(new GuiCheckHost(this));
                break;
            case 42:
                mc.displayGuiScreen(new GuiSpoofer(this));
                break;
            case 0:
                mc.displayGuiScreen(before);
                break;
        }
    }

    public boolean doesGuiPauseGame() {
        return true;
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        GlStateManager.scale(4, 4, 1);
        GlStateManager.scale(0.5, 0.5, 1);
        GlStateManager.scale(0.5, 0.5, 1);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}