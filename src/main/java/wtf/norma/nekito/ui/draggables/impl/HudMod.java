package wtf.norma.nekito.ui.draggables.impl;

import wtf.norma.nekito.ui.draggables.DraggableComponent;

import java.awt.Color;

public class HudMod {

    public String name;
    public boolean enabled;
    public DraggableComponent drag;
    public int x, y;
    public int width, height;

    public HudMod(String name, int x, int y, int width, int height) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.y = height;
        drag = new DraggableComponent(x, y, width, height, new Color(0, 0, 0, 0).getRGB());
    }

    private int getHeight() {
        return 80;
    }

    private int getWidth() {
        return 80;
    }

    public void draw() {

    }

    public void renderDummy(int mouseX, int mouseY) {
        drag.draw(mouseX, mouseY);
    }

    public int getX() {
        return drag.getxPosition();
    }

    public int getY() {
        return drag.getyPosition();
    }
}
