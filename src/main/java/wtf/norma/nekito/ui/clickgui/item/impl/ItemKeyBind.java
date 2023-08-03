package wtf.norma.nekito.ui.clickgui.item.impl;

import net.minecraft.client.gui.Gui;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.ui.clickgui.item.Item;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.util.font.Fonts;

public class ItemKeyBind extends Item<Module> {
    private boolean pendingKey;

    public ItemKeyBind(Module module, int x, int y, int width, int height) {
        super(module, x, y, width, height);
    }

    @Override
    public int drawScreen(int mouseX, int mouseY, float partialTicks, int offset) {
        this.offset = offset;
        float y = this.y + offset;

        Gui.drawRect(x, (int) y, x + width, (int) (y + height), 0x80000000);
        Fonts.SEMI_BOLD_18.drawString(pendingKey ? "..." : "Bind [" + Keyboard.getKeyName(getObject().keybind) + "]",
                x + 5,
                y + height / 2f - mc.fontRendererObj.FONT_HEIGHT / 2f + 1,
                -1);

        return height;
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (bounding(mouseX, mouseY)) {
            if (mouseButton == 0)
                pendingKey = !pendingKey;
        } else
            pendingKey = false;
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
        if (pendingKey) {
            if (keyCode == Keyboard.KEY_DELETE)
                getObject().keybind = Keyboard.KEY_NONE;
            else
                getObject().keybind = keyCode;
            pendingKey = false;
        }
    }
}
