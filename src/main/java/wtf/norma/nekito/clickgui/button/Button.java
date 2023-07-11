package wtf.norma.nekito.clickgui.button;


import net.minecraft.client.Minecraft;

public abstract class Button<T> {

    protected static final Minecraft mc = Minecraft.getMinecraft();

    protected final T item;

    protected final int x;
    protected final int y;
    protected final int width;
    protected final int height;

    public Button(T item, int x, int y, int width, int height) {
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

    public void keyTyped(char typedChar, int keyCode) {

    }

    public void mouseReleased(int mouseX, int mouseY, int state) {

    }

    public boolean bounding(int mouseX, int mouseY) {
        return false;
    }

    public T getItem() {
        return item;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
