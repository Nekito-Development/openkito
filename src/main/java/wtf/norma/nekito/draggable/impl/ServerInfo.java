package wtf.norma.nekito.draggable.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.util.vector.Vector2f;
import wtf.norma.nekito.draggable.AbstractDraggable;
import wtf.norma.nekito.helper.ChatHelper;
import wtf.norma.nekito.helper.OpenGlHelper;
import wtf.norma.nekito.helper.TimeHelper;
import wtf.norma.nekito.holder.Holder;
import wtf.norma.nekito.util.render.RenderUtil;
import wtf.norma.nekito.util.render.RenderUtility;

import java.awt.*;
import java.util.ArrayList;


/*
   @Override
    public void onEvent(Event e) {
        if (e instanceof EventRender2D) {
            if (!mc.isSingleplayer()) {
                ArrayList<String> info = new ArrayList<>();

                int x = (int) mc.thePlayer.posX, y = (int) mc.thePlayer.posY, z = (int) mc.thePlayer.posZ;

                long lastPacketMS = TimeHelper.getCurrentTime() - Holder.getLastPacketMS();
                if (lastPacketMS > 1000 && Holder.getTPS() > 0.00) //idk xd, imagine doesn't know how to gix -0.00
                {
                    Holder.setTPS(Holder.getTPS() - 0.01);
                }

                info.add(ChatHelper.fix("&fSession: &d" + mc.session.getUsername()));
                info.add(ChatHelper.fix("&fServer: &d" + mc.getCurrentServerData().serverIP));

                if (mc.thePlayer.getClientBrand() != null) {
                    String brand = mc.thePlayer.getClientBrand().contains("<- ") ?
                            mc.thePlayer.getClientBrand().split(" ")[0] + " -> " + mc.thePlayer.getClientBrand()
                                    .split("<- ")[1] : mc.thePlayer.getClientBrand().split(" ")[0];

                    info.add(ChatHelper.fix("&fServer brand: &d" + brand));
                }

                info.add(ChatHelper.fix(String.format("&fX, Y, Z: &d%s, %s, %s", x, y, z)));

                if (Holder.getTPS() != -1) {
                    String tps = String.format((Holder.getTPS() > 15
                            ? "&a%.2f" : (Holder.getTPS() > 10
                            ? "&e%.2f" : (Holder.getTPS() > 5
                            ? "&6%.2f" : "&c%.2f"))), Holder.getTPS());

                    info.add(ChatHelper.fix(String.format("&fTPS: &d%s", tps)));
                }

                if (Holder.getLastPacketMS() != -1) {
                    String packetMs = (lastPacketMS < 1000
                            ? "&a" + lastPacketMS : (lastPacketMS < 7000
                            ? "&e" + lastPacketMS : (lastPacketMS < 15000
                            ? "&6" + lastPacketMS : "&c" + lastPacketMS)));
                    info.add(ChatHelper.fix(
                            lastPacketMS > 30500 ? "&fLast packet: &dBroken pipe"
                                    : String.format("&fLast packet: &d%s&7ms &fago", packetMs)));
                }

                int offset = 0;

                for (String s : info) {
                    mc.fontRendererObj.drawStringWithShadow(s, 5, 20 + offset, 0);
                    offset += mc.fontRendererObj.FONT_HEIGHT + 2;
                }
            }
        }
    }
}

 */


public class ServerInfo extends AbstractDraggable {

    @Override
    public void Init() {
        //Initable position
        X = 5;
        Y = 20;
    }

    //<inheritdoc /> (From AbstractDraggable)
    @Override
    public Vector2f Render() {
        //Apply position (not everything work with this)
        GlStateManager.pushMatrix();
        GlStateManager.translate(X, Y, 1);

        ArrayList<String> info = new ArrayList<>();

        int x = (int) mc.thePlayer.posX, y = (int) mc.thePlayer.posY, z = (int) mc.thePlayer.posZ;

        long lastPacketMS = TimeHelper.getCurrentTime() - Holder.getLastPacketMS();
        if (lastPacketMS > 1000 && Holder.getTPS() > 0.00) //idk xd, imagine doesn't know how to gix -0.00
        {
            Holder.setTPS(Holder.getTPS() - 0.01);
        }

        info.add(ChatHelper.fix("&fSession: &d" + mc.session.getUsername()));
        info.add(ChatHelper.fix("&fServer: &d" + mc.getCurrentServerData().serverIP));

        if (mc.thePlayer.getClientBrand() != null) {
            String brand = mc.thePlayer.getClientBrand().contains("<- ") ?
                    mc.thePlayer.getClientBrand().split(" ")[0] + " -> " + mc.thePlayer.getClientBrand()
                            .split("<- ")[1] : mc.thePlayer.getClientBrand().split(" ")[0];

            info.add(ChatHelper.fix("&fServer brand: &d" + brand));
        }

        info.add(ChatHelper.fix(String.format("&fX, Y, Z: &d%s, %s, %s", x, y, z)));

        if (Holder.getTPS() != -1) {
            String tps = String.format((Holder.getTPS() > 15
                    ? "&a%.2f" : (Holder.getTPS() > 10
                    ? "&e%.2f" : (Holder.getTPS() > 5
                    ? "&6%.2f" : "&c%.2f"))), Holder.getTPS());

            info.add(ChatHelper.fix(String.format("&fTPS: &d%s", tps)));
        }

        if (Holder.getLastPacketMS() != -1) {
            String packetMs = (lastPacketMS < 1000
                    ? "&a" + lastPacketMS : (lastPacketMS < 7000
                    ? "&e" + lastPacketMS : (lastPacketMS < 15000
                    ? "&6" + lastPacketMS : "&c" + lastPacketMS)));
            info.add(ChatHelper.fix(
                    lastPacketMS > 30500 ? "&fLast packet: &dBroken pipe"
                            : String.format("&fLast packet: &d%s&7ms &fago", packetMs)));
        }

        int offset = 0;

        for (String s : info) {
            mc.fontRendererObj.drawStringWithShadow(s, 5, 20 + offset, 0);
            offset += mc.fontRendererObj.FONT_HEIGHT + 2;
        }

        //Returning size
        GlStateManager.popMatrix();
        offset += mc.fontRendererObj.FONT_HEIGHT + 2;
        return new Vector2f(mc.fontRendererObj.getStringWidth(String.valueOf(info)) + 4, 20 + offset);
    }
}


