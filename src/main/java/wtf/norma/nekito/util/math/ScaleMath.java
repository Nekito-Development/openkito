package wtf.norma.nekito.util.math;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;

public class ScaleMath {

    private int scale;

    public ScaleMath(int scale) {
        this.scale = scale;
    }

    public void pushScale() {
        ScaledResolution rs = new ScaledResolution(Minecraft.getMinecraft());
        double scale = rs.getScaleFactor() / Math.pow(rs.getScaleFactor(), 2);
        GlStateManager.pushMatrix();
        GlStateManager.scale(scale * this.scale, scale * this.scale, scale * this.scale);
    }

    public int calc(int value) {
        ScaledResolution rs = new ScaledResolution(Minecraft.getMinecraft());
        return value * rs.getScaleFactor() / this.scale;
    }

    public void popScale() {
        GlStateManager.scale(this.scale, this.scale, this.scale);
        GlStateManager.popMatrix();
    }

    public Vec2i getMouse(int mouseX, int mouseY, ScaledResolution rs) {
        return new Vec2i(mouseX * rs.getScaleFactor() / this.scale, mouseY * rs.getScaleFactor() / this.scale);
    }

    public int getScale() {
        return this.scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }
}
