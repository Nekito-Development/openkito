package wtf.norma.nekito.event.impl.packet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.network.Packet;
import wtf.norma.nekito.event.Event;
@Getter
@Setter
@AllArgsConstructor
public class PacketEvent extends Event {
    private Packet packet;
}
