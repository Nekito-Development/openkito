package wtf.norma.nekito.module.impl;

import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.event.impl.PacketEvent;
import wtf.norma.nekito.module.Module;


public class NoServerRotations extends Module {


    public NoServerRotations() {
        super("No Server Rotations", Category.OTHER, Keyboard.KEY_NONE);
    }


    @Override
    public void onEvent(Event e) {
        if (e instanceof PacketEvent) {
            // cum
            if (((PacketEvent) e).getPacket() instanceof S08PacketPlayerPosLook) {
                S08PacketPlayerPosLook packet = (S08PacketPlayerPosLook) ((PacketEvent) e).getPacket();
                packet.yaw = mc.thePlayer.rotationYaw;
                packet.pitch = mc.thePlayer.rotationPitch;
            }

        }
    }
}
