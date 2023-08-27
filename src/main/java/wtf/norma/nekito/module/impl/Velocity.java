package wtf.norma.nekito.module.impl;

import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import me.zero.alpine.listener.Subscriber;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.Nekito;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.newevent.impl.packet.PacketEvent;
import wtf.norma.nekito.settings.impl.NumberSetting;


public class Velocity extends Module implements Subscriber {


    public NumberSetting horizontal = new NumberSetting("Horizontal", 0F, 0F, 1F, 0.01F);

    // yes it is float
    public NumberSetting vertical = new NumberSetting("Vertical", 0F, 0F, 1F, 0.01F);
    @Subscribe
    private final Listener<PacketEvent> listener = new Listener<>(event -> {
        if (!event.isInbound()) return;
        if (!(event.getPacket() instanceof S12PacketEntityVelocity)) return;
        S12PacketEntityVelocity sv = (S12PacketEntityVelocity) event.getPacket();
        if (mc.thePlayer.getEntityId() == sv.getEntityID()) {
            if (horizontal.getValue() == 0F && vertical.getValue() == 0F)
                event.setCancelled(true);
            sv.motionX = (int) (sv.motionX * horizontal.getValue());
            sv.motionY = (int) (sv.motionY * vertical.getValue());
            sv.motionZ = (int) (sv.motionZ * horizontal.getValue());
        }
    });

    public Velocity() {

        super("Velocity", Category.COMBAT, Keyboard.KEY_NONE);
        addSettings(horizontal, vertical);
    }

    @Override
    public void onEnable() {
        Nekito.EVENT_BUS.subscribe(this);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        Nekito.EVENT_BUS.unsubscribe(this);
        super.onDisable();
    }

//    public void onEvent(Event event) {
//        if (event instanceof PacketEvent) {
//            if (((PacketEvent) event).getPacket() instanceof S12PacketEntityVelocity) {
//                S12PacketEntityVelocity sv = (S12PacketEntityVelocity) ((PacketEvent) event).getPacket();
//                if (mc.thePlayer.getEntityId() == sv.getEntityID()) {
//                    if (horizontal.getValue() == 0F && vertical.getValue() == 0F)
//                        event.setCancelled(true);
//                    sv.motionX = (int) (sv.motionX * horizontal.getValue());
//                    sv.motionY = (int) (sv.motionY * vertical.getValue());
//                    sv.motionZ = (int) (sv.motionZ * horizontal.getValue());
//                }
//            }
//        }
//    }
}





