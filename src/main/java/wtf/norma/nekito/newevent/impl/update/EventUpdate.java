package wtf.norma.nekito.newevent.impl.update;

import net.minecraft.network.Packet;
import wtf.norma.nekito.newevent.Event;

public class EventUpdate extends Event {
    private Packet<?> packet;

    public final Packet<?> getPacket() {
        return packet;
    }
}
