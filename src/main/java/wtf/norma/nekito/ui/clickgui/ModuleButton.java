package wtf.norma.nekito.ui.clickgui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.settings.Setting;
import wtf.norma.nekito.settings.impl.BooleanSetting;
import wtf.norma.nekito.settings.impl.ModeSetting;
import wtf.norma.nekito.settings.impl.NumberSetting;
import wtf.norma.nekito.ui.clickgui.item.Item;
import wtf.norma.nekito.ui.clickgui.item.impl.ItemBoolean;
import wtf.norma.nekito.ui.clickgui.item.impl.ItemKeyBind;
import wtf.norma.nekito.ui.clickgui.item.impl.ItemMode;
import wtf.norma.nekito.ui.clickgui.item.impl.ItemSlider;
import wtf.norma.nekito.util.color.ColorUtility;
import wtf.norma.nekito.util.font.Fonts;
import wtf.norma.nekito.util.render.RenderUtility;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ModuleButton {
    private final Module module;
    private final int x;//
    private final int y;
    private final int width;
    private final int height;
    private final Minecraft mc;
    private int offset;
    private boolean open;
    private final List<Item<?>> items = new ArrayList<>();

    public ModuleButton(Module module, int x, int y, int width, int height, Minecraft mc) {
        this.module = module;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.mc = mc;

        for (Setting setting : module.settings) {
            if (setting instanceof BooleanSetting)
                items.add(new ItemBoolean((BooleanSetting) setting, x, y, width, height));
            else if (setting instanceof ModeSetting)
                items.add(new ItemMode((ModeSetting) setting, x, y, width, height));
            else if (setting instanceof NumberSetting)
                items.add(new ItemSlider((NumberSetting) setting, x, y, width, height));
        }

        items.add(new ItemKeyBind(module, x, y, width, height));
    }

    public int drawScreen(int mouseX, int mouseY, float partialTicks, int offset) {
        this.offset = offset;
        int y = this.y + offset;

        Gui.drawRect(x, y, x + width, y + height, 0x80000000);

        // CWEL?

            Fonts.SEMI_BOLD_18.drawString(module.getName(), x + 3, y + (height / 2f - mc.fontRendererObj.FONT_HEIGHT / 2f) + 1, this.module.isToggled() ? 0xFF2B71F3 : -1);



        if (module.settings.size() > 0) {
            Fonts.SEMI_BOLD_18.drawString(open ? "-" : "+", x + 90, y + 4, -1);
        }

        int offsets = height;
        if (open) {
            for (Item<?> item : items) {

                offsets += item.drawScreen(mouseX, mouseY, partialTicks, offsets + offset);

            }
        }

        return offsets;
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (bounding(mouseX, mouseY)) {
            if (mouseButton == 0) {
                this.module.toggle();
            } else if (mouseButton == 1) {
                this.open = !this.open;
            }
        }

        if (open)
            items.forEach(item -> item.mouseClicked(mouseX, mouseY, mouseButton));
    }

    public void keyTyped(char typedChar, int keyCode) {
        if (open)
            items.forEach(item -> item.keyTyped(typedChar, keyCode));
    }

    public void mouseReleased(int mouseX, int mouseY, int state) {
        if (open)
            items.forEach(item -> item.mouseReleased(mouseX, mouseY, state));
    }

    public boolean bounding(int mouseX, int mouseY) {
        if (mouseX < this.x) return false;
        if (mouseX > this.x + this.width) return false;
        if (mouseY < this.y + this.offset) return false;
        return mouseY <= this.y + this.offset + this.height;
    }
}
