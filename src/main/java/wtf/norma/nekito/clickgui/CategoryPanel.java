package wtf.norma.nekito.clickgui;

import net.minecraft.client.Minecraft;
import wtf.norma.nekito.Nekito;
import wtf.norma.nekito.clickgui.button.Button;
import wtf.norma.nekito.clickgui.button.impl.ExploitButton;
import wtf.norma.nekito.clickgui.button.impl.ModuleButton;
import wtf.norma.nekito.exploit.Exploit;
import wtf.norma.nekito.exploit.ExploitManager;
import wtf.norma.nekito.helper.font.FontHelper;
import wtf.norma.nekito.helper.render.RenderHelper;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.module.ModuleCategory;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CategoryPanel {

    private final List<Button<?>> buttons = new ArrayList<>();

    private final ModuleCategory category;
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private boolean open = true;

    public CategoryPanel(ModuleCategory category, int x, int y, int width, int height, Minecraft mc) {
        this.category = category;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        if (category == ModuleCategory.CRASHERS) {
            ExploitManager exploitManager = Nekito.INSTANCE.getExploitManager();
            for (Exploit<?> exploit : exploitManager.getExploits()) {
                buttons.add(new ExploitButton(exploit, x, y, width, height));
            }
        } else {
            for (Module module : Nekito.INSTANCE.getModuleManager().getModules()) {
                if (module.getCategory() == this.category) {
                    buttons.add(new ModuleButton(module, x, y, width, height));
                }
            }
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        if (this.open) {
            AtomicInteger offset = new AtomicInteger(height);
            buttons.stream()
                    .filter(button -> category != ModuleCategory.CRASHERS || button instanceof ExploitButton)
                    .forEach(button -> offset.addAndGet(button.drawScreen(mouseX, mouseY, partialTicks, offset.get())));
        }

        RenderHelper.drawRound(x - 1, y, width + 2, height - 1, 4, new Color(43, 92, 255));
        FontHelper.SEMI_BOLD_18.drawString(category.getName(), x + 5, y + 5, -1);
        FontHelper.SEMI_BOLD_18.drawString(open ? "-" : "+", x + 90, y + 5, -1);
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        buttons.forEach(moduleButton -> moduleButton.mouseClicked(mouseX, mouseY, mouseButton));
        if (bounding(mouseX, mouseY) && mouseButton == 1)
            this.open = !this.open;
    }

    public void keyTyped(char typedChar, int keyCode) {
        if (this.open)
            buttons.forEach(moduleButton -> moduleButton.keyTyped(typedChar, keyCode));
    }


    public void mouseReleased(int mouseX, int mouseY, int state) {
        if (this.open)
            buttons.forEach(moduleButton -> moduleButton.mouseReleased(mouseX, mouseY, state));
    }

    public boolean bounding(int mouseX, int mouseY) {
        if (mouseX < this.x) return false;
        if (mouseX > this.x + this.width) return false;
        if (mouseY < this.y) return false;
        return mouseY <= this.y + this.height;
    }
}
