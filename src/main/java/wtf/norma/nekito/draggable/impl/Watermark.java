package wtf.norma.nekito.draggable.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.util.vector.Vector2f;
import wtf.norma.nekito.draggable.AbstractDraggable;
import wtf.norma.nekito.event.impl.EventRender2D;
import wtf.norma.nekito.helper.OpenGlHelper;
import wtf.norma.nekito.util.color.ColorUtility;
import wtf.norma.nekito.util.render.RenderUtility;

import java.awt.*;

public class Watermark extends AbstractDraggable {

    @Override
    public void Init() {
        //Initable position
        X = 0;
        Y = 0;
    }

    //<inheritdoc /> (From AbstractDraggable)
    @Override
    public Vector2f Render() {
        //Apply position (not everything work with this)
        GlStateManager.pushMatrix();
        GlStateManager.translate(X,Y,1);

        String text = "N" + EnumChatFormatting.WHITE + "ekito" + EnumChatFormatting.DARK_GRAY + " [" + EnumChatFormatting.WHITE + Minecraft.getDebugFPS() + " FPS" + EnumChatFormatting.DARK_GRAY + "]";
        RenderUtility.drawRound(3, 4, mc.fontRendererObj.getStringWidth(text) + 4, 10, 3, new Color(0, 0, 0, 100));
        mc.fontRendererObj.drawStringWithShadow(text, 5, 5, ColorUtility.getColor(3000));

        //Returning size
        GlStateManager.popMatrix();
        return new Vector2f(mc.fontRendererObj.getStringWidth(text) + 4, 10);
    }
}
