package wtf.norma.nekito.module.impl;

import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import me.zero.alpine.listener.Subscriber;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.Nekito;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.newevent.impl.packet.PacketEvent;


public class NoServerRotations extends Module implements Subscriber {


    @Subscribe
    private final Listener<PacketEvent> listener = new Listener<>(event -> {
        if (!event.isInbound()) return;
        if (!(event.getPacket() instanceof S08PacketPlayerPosLook)) return;
        S08PacketPlayerPosLook packet = (S08PacketPlayerPosLook) event.getPacket();
        packet.yaw = mc.thePlayer.rotationYaw;
        packet.pitch = mc.thePlayer.rotationPitch;
    });

    public NoServerRotations() {
        super("No Server Rotations", Category.OTHER, Keyboard.KEY_NONE);
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

//    @Override
//    public void onEvent(Event e) {
//        if (e instanceof PacketEvent) {
//            // cum
//            if (((PacketEvent) e).getPacket() instanceof S08PacketPlayerPosLook) {
//                S08PacketPlayerPosLook packet = (S08PacketPlayerPosLook) ((PacketEvent) e).getPacket();
//                packet.yaw = mc.thePlayer.rotationYaw;
//                packet.pitch = mc.thePlayer.rotationPitch;
//            }
//        }
//    }
}
