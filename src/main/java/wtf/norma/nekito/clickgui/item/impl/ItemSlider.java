package wtf.norma.nekito.clickgui.item.impl;

import net.minecraft.client.gui.Gui;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.clickgui.item.Item;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.settings.impl.NumberSetting;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ItemSlider extends Item<NumberSetting> {
    private boolean sliding = false;
    private double renderWidth;

    public ItemSlider(NumberSetting numberSetting, int x, int y, int width, int height) {
        super(numberSetting, x, y, width, height);
    }

    @Override
    public int drawScreen(int mouseX, int mouseY, float partialTicks, int offset) {
        this.offset = offset;
        float y = this.y + offset;
        double min = getObject().getMin();
        double max = getObject().getMax();
        double l = 100;

        renderWidth = (l) * (getObject().getValue() - min) / (max - min);

        double diff = Math.min(l, Math.max(0, mouseX - x));
        if (sliding) {
            if (diff == 0) {
                getObject().setValue(getObject().getMin());
            }
            else {
                double newValue = roundToPlace(((diff / l) * (max - min) + min), 1);
                getObject().setValue((float) newValue);
            }
        }

        Gui.drawRect(x, (int) y, x + width, (int) (y + height), 0x80000000);
        Gui.drawRect(x, (int) (y), (int) (x + renderWidth), (int) (y + height), 0xFF2B71F3);
        mc.fontRendererObj.drawStringWithShadow(getObject().getName() + ": " + getObject().getValue(), x + 5, y + height / 2f - mc.fontRendererObj.FONT_HEIGHT / 2f, -1);
        return height;
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (bounding((int) (mouseX), mouseY)) {
            sliding = true;
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
        sliding = false;
    }

    private double roundToPlace(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
