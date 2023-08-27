package wtf.norma.nekito.event.impl.movement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import wtf.norma.nekito.event.Event;

@Getter
@Setter
@AllArgsConstructor
public class EventMotion extends Event {
    private double x, y, z;
    private float yaw, pitch;
    public boolean onGround;
}
