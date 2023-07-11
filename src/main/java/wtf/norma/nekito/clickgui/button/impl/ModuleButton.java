package wtf.norma.nekito.clickgui.button.impl;

import net.minecraft.client.gui.Gui;
import wtf.norma.nekito.clickgui.button.Button;
import wtf.norma.nekito.clickgui.item.Item;
import wtf.norma.nekito.clickgui.item.impl.ItemBoolean;
import wtf.norma.nekito.clickgui.item.impl.ItemKeyBind;
import wtf.norma.nekito.clickgui.item.impl.ItemMode;
import wtf.norma.nekito.clickgui.item.impl.ItemSlider;
import wtf.norma.nekito.helper.font.FontHelper;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.module.value.Value;
import wtf.norma.nekito.module.value.impl.BooleanValue;
import wtf.norma.nekito.module.value.impl.ModeValue;
import wtf.norma.nekito.module.value.impl.RangeNumberValue;

import java.util.ArrayList;
import java.util.List;

public class ModuleButton extends Button<Module> {

    private final List<Item<?>> items = new ArrayList<>();
    private int offset;
    private boolean open;

    public ModuleButton(Module item, int x, int y, int width, int height) {
        super(item, x, y, width, height);

        for (Value<?> setting : item.getValues().values()) {
            if (setting instanceof BooleanValue)
                items.add(new ItemBoolean((BooleanValue) setting, x, y, width, height));
            else if (setting instanceof ModeValue)
                items.add(new ItemMode((ModeValue) setting, x, y, width, height));
            else if (setting instanceof RangeNumberValue)
                items.add(new ItemSlider((RangeNumberValue) setting, x, y, width, height));
        }

        items.add(new ItemKeyBind(item, x, y, width, height));
    }

    public int drawScreen(int mouseX, int mouseY, float partialTicks, int offset) {
        this.offset = offset;
        int y = this.y + offset;

        Gui.drawRect(x, y, x + width, y + height, 0x80000000);
        FontHelper.SEMI_BOLD_18.drawString(item.getName(), x + 3, y + (height / 2f - mc.fontRendererObj.FONT_HEIGHT / 2f) + 1, this.item.isEnabled() ? 0xFF2B71F3 : -1);

        if (item.getValues().size() > 0) {
            FontHelper.SEMI_BOLD_18.drawString(open ? "-" : "+", x + 90, y + 4, -1);
        }

        if (!open)
            return height;

        int offsets = height;
        for (Item<?> item : items) {
            offsets += item.drawScreen(mouseX, mouseY, partialTicks, offsets + offset);
        }

        return offsets;
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (bounding(mouseX, mouseY)) {
            if (mouseButton == 0) {
                this.item.toggle();
            } else if (mouseButton == 1) {
                this.open = !this.open;
            }
        }

        if (open) {
            items.forEach(item -> item.mouseClicked(mouseX, mouseY, mouseButton));
        }
    }

    public void keyTyped(char typedChar, int keyCode) {
        if (open) {
            items.forEach(item -> item.keyTyped(typedChar, keyCode));
        }
    }

    public void mouseReleased(int mouseX, int mouseY, int state) {
        if (open) {
            items.forEach(item -> item.mouseReleased(mouseX, mouseY, state));
        }
    }

    public boolean bounding(int mouseX, int mouseY) {
        if (mouseX < this.x) return false;
        if (mouseX > this.x + this.width) return false;
        if (mouseY < this.y + this.offset) return false;
        return mouseY <= this.y + this.offset + this.height;
    }
}
