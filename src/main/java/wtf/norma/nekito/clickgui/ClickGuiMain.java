package wtf.norma.nekito.clickgui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import wtf.norma.nekito.module.ModuleCategory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClickGuiMain extends GuiScreen {
    public List<CategoryPanel> panels = new ArrayList<>();

    @Override//
    public void initGui() {
        if (this.panels.isEmpty()) {
            int x = 10;
            for (ModuleCategory category : ModuleCategory.values()) {
                panels.add(new CategoryPanel(category, x, 10, 100, 16, Minecraft.getMinecraft()));
                x += 110;
            }
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.panels.forEach(panel -> panel.drawScreen(mouseX, mouseY, partialTicks));
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
        try {
            super.keyTyped(typedChar, keyCode);
        } catch (IOException ignored) {
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
