package wtf.norma.nekito.clickgui.item;

import net.minecraft.client.Minecraft;

public abstract class Item<T> {

    protected static final Minecraft mc = Minecraft.getMinecraft();

    protected final int x, y, width, height;
    private final T item;
    protected int offset;

    public Item(T item, int x, int y, int width, int height) {
        this.item = item;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int drawScreen(int mouseX, int mouseY, float partialTicks, int offset) {
        return 0;
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
    }

    public void mouseReleased(int mouseX, int mouseY, int state) {
    }

    public void keyTyped(char typedChar, int keyCode) {
    }

    public boolean bounding(int mouseX, int mouseY) {
        if (mouseX < this.x) return false;
        if (mouseX > this.x + this.width) return false;
        if (mouseY < this.y + this.offset) return false;
        return mouseY <= this.y + this.offset + this.height;
    }

    public T getItem() {
        return item;
    }
}
