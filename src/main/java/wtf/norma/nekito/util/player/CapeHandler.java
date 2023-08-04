package wtf.norma.nekito.util.player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;

/**
 * Handles giving players capes.
 */
public class CapeHandler {
    private static final Logger logger = LogManager.getLogger();

    /**
     * Map of player names to the skins to give the players.
     *
     * You can convert a player name to a unique ID and vice versa using
     * <a href="https://namemc.com/">namemc.com</a>.
     */
    private static final Map<UUID, ResourceLocation> capes = new HashMap<UUID, ResourceLocation>();

    private static final Set<EntityPlayer> handledPlayers = new HashSet<EntityPlayer>();

    private static final Map<EntityPlayer, Integer> playerFailures = new HashMap<EntityPlayer, Integer>();

    private static int totalFailures = 0;

    private static final int MAX_PLAYER_FAILURES = 40;

    private static final int MAX_TOTAL_FAILURES = 40;


    static {
        //eleczka (pedal)
        capes.put(UUID.fromString("6524d9a8-a17a-4152-ae03-41a00ec79e6f"),
                new ResourceLocation("cwel", "images/cape/admin.png"));
        //besterror
        capes.put(UUID.fromString("78f7f2af-2948-4f30-a522-feb03b10d572"),
                new ResourceLocation("cwel", "images/cape/admin.png"));

        //husuku (ne jest dev ale nie hc ma!)
        capes.put(UUID.fromString("4c710d3b-508c-4b79-a0ee-7edb082639af"),
                new ResourceLocation("cwel", "images/cape/admin.png"));

        //groszus
           capes.put(UUID.fromString("2e2ce546-78b1-406c-824f-406b3704a39d"),
                new ResourceLocation("cwel", "images/cape/admin.png"));
           ///lekoz
        capes.put(UUID.fromString("5a9662e0-c77a-42ef-90e7-b61e45ffa61a"),
                new ResourceLocation("cwel", "images/cape/admin.png"));


    }

    public static void onWorldTick(List<EntityPlayer> players) {
        if (totalFailures > MAX_TOTAL_FAILURES) {
            return;
        }

        try {
            handledPlayers.retainAll(players);

            for (EntityPlayer player : players) {
                if (handledPlayers.contains(player)) {
                    continue;
                }

                if (player instanceof AbstractClientPlayer) {
                    setupPlayer((AbstractClientPlayer)player);
                }
            }
        } catch (Exception e) {
            logger.warn("Failed to tick cape setup", e);
            totalFailures++;

            if (totalFailures > MAX_TOTAL_FAILURES) {
                logger.warn("neid ziala nwm czemu)");
            }
        }
    }

    private static void setupPlayer(AbstractClientPlayer player) {
        try {
            NetworkPlayerInfo info = ReflectionUtility.stealAndGetField(player, AbstractClientPlayer.class, NetworkPlayerInfo.class);
            if (info == null) {
                incrementFailure(player);
                return;
            }
            GameProfile profile = info.getGameProfile();
            if (capes.containsKey(profile.getId())) {
                setPlayerCape(info, capes.get(profile.getId()));
            }
            handledPlayers.add(player);
        } catch (Exception e) {
            logger.warn("[ERROR] cape byla zamala na  " + player + "(grubycwel)", e);
            incrementFailure(player);
        }
    }

    private static void setPlayerCape(NetworkPlayerInfo info,
                                      ResourceLocation cape) throws Exception {
        @SuppressWarnings("unchecked")
        Map<MinecraftProfileTexture.Type, ResourceLocation> map = ReflectionUtility.stealAndGetField(info, Map.class);
        if (!map.containsKey(MinecraftProfileTexture.Type.CAPE)) {
            map.put(MinecraftProfileTexture.Type.CAPE, cape);
        }
    }

    /**
     * Increment the number of times a player has failed to get a cape.
     */
    private static void incrementFailure(EntityPlayer player) {
        if (playerFailures.containsKey(player)) {
            int numFailures = playerFailures.get(player) + 1;
            playerFailures.put(player, numFailures);

            if (numFailures > MAX_PLAYER_FAILURES) {
                handledPlayers.add(player);
                playerFailures.remove(player);
                logger.warn("[Error] cape byla zamala na " + player
                        + " too many times (" + numFailures + "); skipping");
            }
        } else {
            playerFailures.put(player, 1);
        }
    }
}
