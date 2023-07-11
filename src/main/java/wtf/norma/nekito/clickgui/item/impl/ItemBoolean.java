package wtf.norma.nekito.clickgui.item.impl;

import net.minecraft.client.gui.Gui;
import wtf.norma.nekito.clickgui.item.Item;
import wtf.norma.nekito.helper.font.FontHelper;
import wtf.norma.nekito.module.value.impl.BooleanValue;

public class ItemBoolean extends Item<BooleanValue> {
    public ItemBoolean(BooleanValue boolSetting, int x, int y, int width, int height) {
        super(boolSetting, x, y, width, height);
    }

    @Override
    public int drawScreen(int mouseX, int mouseY, float partialTicks, int offset) {
        this.offset = offset;
        float y = this.y + offset;

        Gui.drawRect(x, (int) y, x + width, (int) (y + height), 0x80000000);
        FontHelper.SEMI_BOLD_18.drawString(getItem().getName(), x + 5, y + height / 2f - mc.fontRendererObj.FONT_HEIGHT / 2f + 1, getItem().get() ? 0xFF2B71F3 : -1);

        return height;
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (bounding(mouseX, mouseY)) {
            if (mouseButton == 0)
                getItem().toggle();
        }
    }
}
