package wtf.norma.nekito.newevent.impl.movement;

import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import wtf.norma.nekito.newevent.Event;

@Getter
@Setter
@AllArgsConstructor
public class EventMotion extends Event {
    private double x, y, z;
    private float yaw, pitch;
    public boolean onGround;
}
