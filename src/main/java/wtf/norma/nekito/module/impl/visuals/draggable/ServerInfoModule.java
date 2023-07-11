package wtf.norma.nekito.module.impl.visuals.draggable;

import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.util.vector.Vector2f;
import rip.hippo.lwjeb.annotation.Handler;
import wtf.norma.nekito.draggable.Draggable;
import wtf.norma.nekito.event.impl.EventRender2D;
import wtf.norma.nekito.helper.ChatHelper;
import wtf.norma.nekito.helper.TimeHelper;
import wtf.norma.nekito.holder.Holder;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.module.ModuleCategory;
import wtf.norma.nekito.module.ModuleInfo;

import java.util.ArrayList;

@ModuleInfo(
        name = "ServerInfo",
        moduleCategory = ModuleCategory.VISUALS
)
public class ServerInfoModule extends Module implements Draggable {

    private int x = 5, y = 20;
    private Vector2f size = Draggable.EMPTY_SIZE;

    @Handler
    public void onRender(EventRender2D event) {
        //Apply position (not everything work with this)
        GlStateManager.pushMatrix();
        GlStateManager.translate(getDraggableX(), getDraggableY(), 1);

        ArrayList<String> info = new ArrayList<>();

        int x = (int) mc.thePlayer.posX, y = (int) mc.thePlayer.posY, z = (int) mc.thePlayer.posZ;

        long lastPacketMS = TimeHelper.getCurrentTime() - Holder.getLastPacketMS();
        if (lastPacketMS > 1000 && Holder.getTPS() > 0.00) {
            Holder.setTPS(Holder.getTPS() - 0.01);
        }

        info.add(ChatHelper.fix("&fSession: &d" + mc.session.getUsername()));
        if (!mc.isSingleplayer()) {
            info.add(ChatHelper.fix("&fServer: &d" + mc.getCurrentServerData().serverIP));
        }

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
            String.valueOf(mc.fontRendererObj.drawStringWithShadow(s, 5, 20 + offset, 0));
            offset += mc.fontRendererObj.FONT_HEIGHT + 2;
        }

        //Returning size
        GlStateManager.popMatrix();
        offset += mc.fontRendererObj.FONT_HEIGHT + 2;
        setDraggableSize(new Vector2f(mc.fontRendererObj.getStringWidth(String.valueOf(info)) + 4, 20 + offset));
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


