package wtf.norma.nekito.event.impl;

import net.minecraft.network.Packet;
import wtf.norma.nekito.event.Event;

public class PacketEvent extends Event {
    private Packet packet;



    public PacketEvent(Packet packet){
        this.packet = packet;

    }

    private boolean cancelled;

    public boolean isCancelled() {
        return this.cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }



    public Packet getPacket(){
        return packet;
    }
    public void setPacket(Packet p){
        this.packet = p;
    }
}
