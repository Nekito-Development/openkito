package wtf.norma.nekito.ui.Tools;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import wtf.norma.nekito.ui.checkhost.GuiCheckHost;

import java.io.IOException;


public class UiTools extends GuiScreen {
    private final Minecraft mc = Minecraft.getMinecraft();

    private final GuiScreen before;

    public UiTools(GuiScreen before) {
        this.before = before;
    }

    public void initGui() {




        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 3 - 40, "Checkhost"));
        this.buttonList.add(new GuiButton(42, this.width / 2 - 100, this.height / 3 - 15,  "Spoofer"));
        this.buttonList.add(new GuiButton(0, this.width - (this.width / (this.width / 2) + this.width / 6) - 6, this.height - 26, this.width / (this.width / 2) + this.width / 6, 20, "Back"));
    }

    protected void actionPerformed(GuiButton button) throws IOException {
        int id = button.id;

        if (id == 1) {
            mc.displayGuiScreen(new GuiCheckHost(this));
        }
        if (id == 42) {
            mc.displayGuiScreen(new UiSpoofer(this));
        }


        if (id == 0) {
            mc.displayGuiScreen(before);
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