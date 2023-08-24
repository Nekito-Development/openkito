package wtf.norma.nekito.draggable.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import wtf.norma.nekito.draggable.AbstractDraggable;
import wtf.norma.nekito.util.color.ColorUtility;
import wtf.norma.nekito.util.color.ColorUtils;
import wtf.norma.nekito.util.font.Fonts;
import wtf.norma.nekito.util.render.RenderUtility;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class NevaLose extends AbstractDraggable {

    @Override
    public void Init() {
        //Initable position
        X = 0;
        Y = 0;
    }

    public SimpleDateFormat ddos = new SimpleDateFormat("HH:mm:ss");
    @Override

    public Vector2f Render() {



        // BTW THAT FUCKING WATERMARK FROM CFXMAFIA BY XARIES LOOKS LIKE NORMA OLD WATERMARK XDDDDDDDDDDDDDDDDDD
        // 1:1

        //NORMA: https://media.discordapp.net/attachments/1115513650011193376/1136706875941982309/brave_bQdfTxfgRb.png  from 8 april 2023
        //CFXMAFIA : https://media.discordapp.net/attachments/1115513650011193376/1136706892832452649/brave_qEAdCbfVPR.png from 26 july 2023




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
