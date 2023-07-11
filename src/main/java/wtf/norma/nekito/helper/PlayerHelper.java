package wtf.norma.nekito.helper;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetworkPlayerInfo;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PlayerHelper {

    private static final Pattern validUserPattern = Pattern.compile("^[a-zA-Z0-9_]{3,16}$");
    private static final Minecraft mc = Minecraft.getMinecraft();

    public static List<GameProfile> getOnlinePlayer() {
        return mc.thePlayer.sendQueue.getPlayerInfoMap().stream()
                .map(NetworkPlayerInfo::getGameProfile)
                .filter(profile -> validUserPattern.matcher(profile.getName()).matches())
                .collect(Collectors.toList());
    }
}
