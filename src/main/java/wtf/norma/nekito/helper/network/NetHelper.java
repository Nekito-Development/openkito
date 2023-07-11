package wtf.norma.nekito.helper.network;

import com.mojang.authlib.Agent;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import net.minecraft.client.Minecraft;
import net.minecraft.network.Packet;
import net.minecraft.util.Session;

import java.net.Proxy;

public final class NetHelper {

    private static final Minecraft mc = Minecraft.getMinecraft();

    private NetHelper() {
    }

    public static void sendQueuePacket(Packet<?> packet) {
        mc.thePlayer.sendQueue.addToSendQueue(packet);
    }

    public static void sendPacket(Packet<?> packet) {
        mc.thePlayer.sendQueue.getNetworkManager().sendPacket(packet);
    }

    public static void createSession(String username, String password) {
        if (password != null) {
            YggdrasilAuthenticationService service = new YggdrasilAuthenticationService(Proxy.NO_PROXY,
                    "");
            YggdrasilUserAuthentication auth = (YggdrasilUserAuthentication) service
                    .createUserAuthentication(Agent.MINECRAFT);
            auth.setUsername(username);
            auth.setPassword(password);
            try {
                auth.logIn();
                Minecraft.getMinecraft().session = new Session(auth.getSelectedProfile().getName(),
                        auth.getSelectedProfile().getId().toString(), auth.getAuthenticatedToken(), "mojang");
            } catch (AuthenticationException localAuthenticationException) {
                localAuthenticationException.printStackTrace();
            }
        } else {
            Minecraft.getMinecraft().session = new Session(username, "", "", "mojang");
        }
    }
}
