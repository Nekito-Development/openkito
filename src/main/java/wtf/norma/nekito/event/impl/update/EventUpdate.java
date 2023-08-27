package wtf.norma.nekito.event.impl.update;

import net.minecraft.network.Packet;
import wtf.norma.nekito.event.Event;

public class EventUpdate extends Event {
    private Packet<?> packet;

    public final Packet<?> getPacket() {
        return packet;
    }
}
