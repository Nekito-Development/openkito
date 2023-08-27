package wtf.norma.nekito.module.impl;

import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import me.zero.alpine.listener.Subscriber;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.Nekito;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.helper.ChatHelper;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.newevent.impl.movement.EventPreMotion;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class AntiBot extends Module implements Subscriber {

    public AntiBot() {
        super("AntiBot", Category.COMBAT, Keyboard.KEY_NONE);
    }
    public static List<Entity> isBotPlayer = new ArrayList<>();

    @Override
    public void onEnable() {
        super.onEnable();
        Nekito.EVENT_BUS.subscribe(this);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        Nekito.EVENT_BUS.subscribe(this);
    }

    @Subscribe
    private final Listener<EventPreMotion> listener = new Listener<>(event ->{
        mc.theWorld.playerEntities.stream().forEach(entity ->{
            if (!entity.getUniqueID().equals(UUID.nameUUIDFromBytes(("OfflinePlayer:" + entity.getName()).getBytes(StandardCharsets.UTF_8))) && entity instanceof EntityOtherPlayerMP)
                isBotPlayer.add(entity);

            if (!entity.getUniqueID().equals(UUID.nameUUIDFromBytes(("OfflinePlayer:" + entity.getName()).getBytes(StandardCharsets.UTF_8))) && entity.isInvisible() && entity instanceof EntityOtherPlayerMP) {
                mc.theWorld.removeEntity(entity);
                ChatHelper.printMessage("Removed " + entity.getName());
            }
        });
    });

    //    public void onEvent(Event e) {
//        if (e instanceof EventPreMotion) {
//            for (Entity entity : mc.theWorld.playerEntities) {
//                if (!entity.getUniqueID().equals(UUID.nameUUIDFromBytes(("OfflinePlayer:" + entity.getName()).getBytes(StandardCharsets.UTF_8))) && entity instanceof EntityOtherPlayerMP) {
//                    isBotPlayer.add(entity);
//                }
//                if (!entity.getUniqueID().equals(UUID.nameUUIDFromBytes(("OfflinePlayer:" + entity.getName()).getBytes(StandardCharsets.UTF_8))) && entity.isInvisible() && entity instanceof EntityOtherPlayerMP) {
//                    mc.theWorld.removeEntity(entity);
//                    ChatHelper.printMessage("Removed " + entity.getName());
//                }
//            }
//        }
//    }

}