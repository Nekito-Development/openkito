package wtf.norma.nekito.util.player;

import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.network.NetworkPlayerInfo;
import wtf.norma.nekito.util.Util;

public class EntityUtility implements Util {



    public static int getPing() {
        if (mc.theWorld != null) {
            if (mc.thePlayer != null) {
                NetHandlerPlayClient connection = mc.getNetHandler();
                NetworkPlayerInfo debugInfo = connection.getPlayerInfo(mc.thePlayer.getUniqueID());
                if (debugInfo == null) {
                    return 0;
                }

                return debugInfo.getResponseTime();
            }
        }
        return 0;
    }

}
