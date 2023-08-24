package wtf.norma.nekito.draggable.impl;

import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.util.vector.Vector2f;
import wtf.norma.nekito.draggable.AbstractDraggable;
import wtf.norma.nekito.util.color.ColorUtility;
import wtf.norma.nekito.util.font.Fonts;
import wtf.norma.nekito.util.render.RenderUtility;

import java.awt.*;


public class NevaLose extends AbstractDraggable {

    @Override
    public void Init() {
        //Initable position
        X = 0;
        Y = 0;
    }

    @Override

    public Vector2f Render() {





        GlStateManager.pushMatrix();
        GlStateManager.translate(X,Y,1);

        int pizduch = 18;
        RenderUtility.drawGlow(4.0F, 4.0F, (float) pizduch, 12.0F, 12, new Color(10, 10, 10, 200));
        RenderUtility.drawRound(4.0F, 4.0F, (float)  pizduch, 12.0F, 3, new Color(10, 10, 10, 200));
        Fonts.MONTSERRAT16.drawString("NT", 6.5F, 8.0F, ColorUtility.getColor(0));
        Fonts.MONTSERRAT16.drawString("NT", 7.0F, 8.5F, -1);

        GlStateManager.popMatrix();
        return new Vector2f(pizduch, 9.5f);
    }
}
