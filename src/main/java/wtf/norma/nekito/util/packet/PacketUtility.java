package wtf.norma.nekito.util.packet;

import net.minecraft.client.Minecraft;
import net.minecraft.network.Packet;

public class PacketUtility  {

    private static final Minecraft mc = Minecraft.getMinecraft();



    public static void send(final Packet<?> packet) {
        mc.getNetHandler().addToSendQueue(packet);
    }

    public static void sendPacket(Packet packet) {
        mc.getNetHandler().getNetworkManager().sendPacket(packet);
    }



}
