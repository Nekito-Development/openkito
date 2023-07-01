package wtf.norma.nekito.hook;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.opengl.GL11;
import wtf.norma.nekito.helper.ChatHelper;
import wtf.norma.nekito.helper.OpenGlHelper;
import wtf.norma.nekito.helper.SystemHelper;
import wtf.norma.nekito.helper.TimeHelper;
import wtf.norma.nekito.holder.Holder;
import wtf.norma.nekito.util.render.RenderUtility;

import java.awt.*;
import java.util.ArrayList;

public class GuiInGameHook extends GuiIngame {

    private static final Minecraft mc = Minecraft.getMinecraft();

    public GuiInGameHook(Minecraft mcIn) {
        super(mcIn);
    }

    @Override
    public void renderGameOverlay(float partialTicks) {
        super.renderGameOverlay(partialTicks);
        String text = "N" + EnumChatFormatting.WHITE + "ekito" + EnumChatFormatting.DARK_GRAY + " [" + EnumChatFormatting.WHITE + Minecraft.getDebugFPS() + " FPS" + EnumChatFormatting.DARK_GRAY + "]";
        RenderUtility.drawRound(3, 4, mc.fontRendererObj.getStringWidth(text) + 4, 10, 3, new Color(0, 0, 0, 100));
        mc.fontRendererObj.drawStringWithShadow(text, 5, 5, OpenGlHelper.rainbowColor(3000, 1));

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
