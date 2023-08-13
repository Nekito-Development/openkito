package wtf.norma.nekito.module.impl;

import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.event.impl.EventPreMotion;
import wtf.norma.nekito.event.impl.EventUpdate;
import wtf.norma.nekito.helper.ChatHelper;
import wtf.norma.nekito.module.Module;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class AntiBot extends Module {

    public AntiBot() {
        super("AntiBot", Category.COMBAT, Keyboard.KEY_NONE);
    }
    public static List<Entity> isBotPlayer = new ArrayList<>();
    public void onEvent(Event e) {
        if (e instanceof EventPreMotion) {
            for (Entity entity : mc.theWorld.playerEntities) {
                if (!entity.getUniqueID().equals(UUID.nameUUIDFromBytes(("OfflinePlayer:" + entity.getName()).getBytes(StandardCharsets.UTF_8))) && entity instanceof EntityOtherPlayerMP) {
                    isBotPlayer.add(entity);
                }
                if (!entity.getUniqueID().equals(UUID.nameUUIDFromBytes(("OfflinePlayer:" + entity.getName()).getBytes(StandardCharsets.UTF_8))) && entity.isInvisible() && entity instanceof EntityOtherPlayerMP) {
                    mc.theWorld.removeEntity(entity);
                    ChatHelper.printMessage("Removed " + entity.getName());
                }
            }
        }
    }

}