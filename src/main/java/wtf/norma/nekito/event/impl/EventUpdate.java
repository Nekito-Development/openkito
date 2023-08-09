package wtf.norma.nekito.event.impl;

import net.minecraft.network.Packet;
import wtf.norma.nekito.event.Event;

public class EventUpdate extends Event {

    private Packet<?> packet;
    public Packet<?> getPacket() {
        return this.packet;
    }
}
