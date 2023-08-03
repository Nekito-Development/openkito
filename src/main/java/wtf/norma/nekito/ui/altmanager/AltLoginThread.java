package wtf.norma.nekito.ui.altmanager;

import com.mojang.authlib.Agent;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import fr.litarvan.openauth.microsoft.MicrosoftAuthResult;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticationException;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticator;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Session;

import java.net.Proxy;

public class AltLoginThread extends Thread {

    private final String password;
    private String status;
    private final String username;
    private final Minecraft mc = Minecraft.getMinecraft();

    public AltLoginThread(String username, String password) {
        super("Alt Login Thread");
        this.username = username;
        this.password = password;
        status = EnumChatFormatting.GRAY + "Waiting";
    }

    private Session createSession(String username, String password) {
        YggdrasilAuthenticationService service = new YggdrasilAuthenticationService(Proxy.NO_PROXY, "");
        YggdrasilUserAuthentication auth = (YggdrasilUserAuthentication) service.createUserAuthentication(Agent.MINECRAFT);
        auth.setUsername(username);
        auth.setPassword(password);
        try {
            auth.logIn();
            return new Session(auth.getSelectedProfile().getName(), auth.getSelectedProfile().getId().toString(), auth.getAuthenticatedToken(), "mojang");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public void run() {
        if (password.equals("")) {
            mc.session = new Session(username.replace("&", "\u00a7"), "", "", "mojang");
            status = EnumChatFormatting.GREEN + "Set username to " + username;
            return;
        } else {
            MicrosoftAuthenticator authenticator = new MicrosoftAuthenticator();
            MicrosoftAuthResult result = null;
            try {
                result = authenticator.loginWithCredentials(username, password);
                status = EnumChatFormatting.GREEN + "Logged into " + result.getProfile().getName();
            } catch (MicrosoftAuthenticationException e) {
                status = EnumChatFormatting.RED + "Failed";
            }
            assert result != null;
            mc.session = new Session(result.getProfile().getName(), result.getProfile().getId(), result.getAccessToken(), "legacy");
            return;
        }
//        status = EnumChatFormatting.AQUA + "Authenticating...";
//        Session auth = createSession(username, password);
//        if (auth == null) {
//            status = EnumChatFormatting.RED + "Failed";
//        } else {
//            status = EnumChatFormatting.GREEN + "Logged into " + auth.getUsername();
//            mc.session = auth;
//        }
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
