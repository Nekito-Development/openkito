package wtf.norma.nekito.newevent.impl.packet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.network.Packet;
import wtf.norma.nekito.newevent.Event;
@Getter
@Setter
@AllArgsConstructor
public class PacketEvent extends Event {
    private Packet packet;
}
