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

public class Watermark2 extends AbstractDraggable {

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

        String serverip = mc.isSingleplayer() ? "localhost:25565" : !mc.getCurrentServerData().serverIP.contains(":") ? mc.getCurrentServerData().serverIP + ":25565" : mc.getCurrentServerData().serverIP;
        String infox = "nekito | " + mc.getSession().getUsername() + " | "+ Minecraft.getDebugFPS() + " fps | " + serverip + " | " + ddos.format(new Date());
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_BLEND);

        // it looks better with verdana font but it is bugged af aka letter "k"
       RenderUtility.renderShadow(5, 5, Fonts.sans_13.getStringWidth(infox) + 9, 15, ColorUtility.getColor(0, 0), 7);

        int colorx = 0;


        RenderUtility.drawRect(5, 5, Fonts.sans_13.getStringWidth(infox) + 4, 12, new java.awt.Color(40, 40, 40));
        RenderUtility.drawRoundedRect(5, 5, Fonts.sans_13.getStringWidth(infox) + 4, 2, 1, colorx, 1, colorx);
        Fonts.sans_13.drawStringWithShadow(infox, 7, 9.5f, ColorUtils.WHITE.cwel);



        GlStateManager.popMatrix();
        return new Vector2f(Fonts.sans_13.getStringWidth(infox) + 7, 9.5f);
    }
}
