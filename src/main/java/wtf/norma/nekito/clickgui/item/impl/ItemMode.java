package wtf.norma.nekito.clickgui.item.impl;

import net.minecraft.client.gui.Gui;
import wtf.norma.nekito.clickgui.item.Item;
import wtf.norma.nekito.helper.font.FontHelper;
import wtf.norma.nekito.module.value.impl.ModeValue;

public class ItemMode extends Item<ModeValue> {
    public ItemMode(ModeValue modeSetting, int x, int y, int width, int height) {
        super(modeSetting, x, y, width, height);
    }

    @Override
    public int drawScreen(int mouseX, int mouseY, float partialTicks, int offset) {
        this.offset = offset;
        float y = this.y + offset;

        Gui.drawRect(x, (int) y, x + width, (int) (y + height), 0x80000000);
        FontHelper.SEMI_BOLD_18.drawString(getItem().getName() + ": " + getItem().get(), x + 5, y + height / 2f - mc.fontRendererObj.FONT_HEIGHT / 2f + 1, -1);

        return height;
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (bounding(mouseX, mouseY) && mouseButton == 0) {
            getItem().next();
        }
    }
}
