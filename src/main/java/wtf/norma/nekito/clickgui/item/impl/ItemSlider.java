package wtf.norma.nekito.clickgui.item.impl;

import net.minecraft.client.gui.Gui;
import wtf.norma.nekito.clickgui.item.Item;
import wtf.norma.nekito.helper.font.FontHelper;
import wtf.norma.nekito.helper.math.MathHelper;
import wtf.norma.nekito.module.value.impl.RangeNumberValue;

public class ItemSlider extends Item<RangeNumberValue> {
    private boolean sliding;

    public ItemSlider(RangeNumberValue numberSetting, int x, int y, int width, int height) {
        super(numberSetting, x, y, width, height);
    }

    @Override
    public int drawScreen(int mouseX, int mouseY, float partialTicks, int offset) {
        this.offset = offset;
        float y = this.y + offset;
        double min = getItem().getMin();
        double max = getItem().getMax();
        double l = 100;

        double renderWidth = (l) * (getItem().get() - min) / (max - min);

        double diff = Math.min(l, Math.max(0, mouseX - x));
        if (sliding) {
            if (diff == 0) {
                getItem().set(getItem().getMin());
            } else {
                double newValue = roundToPlace(((diff / l) * (max - min) + min), 1);
                getItem().set((float) newValue);
            }
        }

        Gui.drawRect(x, (int) y, x + width, (int) (y + height), 0x80000000);
        Gui.drawRect(x, (int) (y), (int) (x + renderWidth), (int) (y + height), 0xFF2B71F3);
        FontHelper.SEMI_BOLD_18.drawString(getItem().getName() + ": " + getItem().get(), x + 5, y + height / 2f - mc.fontRendererObj.FONT_HEIGHT / 2f + 1, -1);
        return height;
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (bounding(mouseX, mouseY)) {
            sliding = true;
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
        sliding = false;
    }

    private double roundToPlace(double value, int times) {
        if (times < 0) {
            throw new IllegalArgumentException();
        }
        return MathHelper.round(value, times);
    }
}
