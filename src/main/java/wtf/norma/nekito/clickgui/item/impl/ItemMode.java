package wtf.norma.nekito.clickgui.item.impl;

import net.minecraft.client.gui.Gui;
import wtf.norma.nekito.clickgui.item.Item;
import wtf.norma.nekito.settings.impl.ModeSetting;

public class ItemMode extends Item<ModeSetting> {
    public ItemMode(ModeSetting modeSetting, int x, int y, int width, int height) {
        super(modeSetting, x, y, width, height);
    }

    @Override
    public int drawScreen(int mouseX, int mouseY, float partialTicks, int offset) {
        this.offset = offset;
        float y = this.y + offset;

        Gui.drawRect(x, (int) y, x + width, (int) (y + height), 0x80000000);
        mc.fontRendererObj.drawStringWithShadow(getObject().getName() + ": " + getObject().getMode(), x + 5, y + height / 2f - mc.fontRendererObj.FONT_HEIGHT / 2f, -1);

        return height;
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (bounding(mouseX, mouseY)) {
            if (mouseButton == 0)
                getObject().cycle();
        }
    }
}
