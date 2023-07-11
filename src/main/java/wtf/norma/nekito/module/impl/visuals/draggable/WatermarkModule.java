package wtf.norma.nekito.module.impl.visuals.draggable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.util.vector.Vector2f;
import rip.hippo.lwjeb.annotation.Handler;
import wtf.norma.nekito.draggable.Draggable;
import wtf.norma.nekito.event.impl.EventRender2D;
import wtf.norma.nekito.helper.OpenGlHelper;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.module.ModuleCategory;
import wtf.norma.nekito.module.ModuleInfo;
import wtf.norma.nekito.util.render.RenderUtility;

import java.awt.*;


@ModuleInfo(
        name = "Watermark",
        moduleCategory = ModuleCategory.VISUALS
)
public class WatermarkModule extends Module implements Draggable {

    private static final Color COLOR = new Color(0, 0, 0, 100);
    private static final String TEXT = String.format("N%sekito %s[%s%s%s]",
            EnumChatFormatting.WHITE,
            EnumChatFormatting.DARK_GRAY,
            EnumChatFormatting.WHITE,
            "%s",
            EnumChatFormatting.DARK_GRAY
    );

    private int x, y;
    private Vector2f size = Draggable.EMPTY_SIZE;

    @Handler
    public void onRender(EventRender2D event) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(getDraggableX(), getDraggableY(), 1);

        String text = String.format(TEXT, Minecraft.getDebugFPS());
        RenderUtility.drawRound(3, 4, mc.fontRendererObj.getStringWidth(text) + 4, 10, 3, COLOR);
        mc.fontRendererObj.drawStringWithShadow(text, 5, 5, OpenGlHelper.rainbowColor(3000, 1));

        //Returning size
        GlStateManager.popMatrix();
        setDraggableSize(new Vector2f(mc.fontRendererObj.getStringWidth(text) + 4, 10));
    }

    @Override
    public void onDisable() {
        this.size = Draggable.EMPTY_SIZE;
    }

    @Override
    public int getDraggableX() {
        return x;
    }

    @Override
    public void setDraggableX(int x) {
        this.x = x;
    }

    @Override
    public int getDraggableY() {
        return y;
    }

    @Override
    public void setDraggableY(int y) {
        this.y = y;
    }

    @Override
    public Vector2f getDraggableSize() {
        return size;
    }

    @Override
    public void setDraggableSize(Vector2f size) {
        this.size = size;
    }
}
