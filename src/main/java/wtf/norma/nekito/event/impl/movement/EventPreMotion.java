package wtf.norma.nekito.event.impl.movement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import wtf.norma.nekito.event.Event;

@Getter
@Setter
@AllArgsConstructor
public class EventPreMotion extends Event {
    private float yaw, pitch;
    private double posX, posY, posZ;
    private boolean onGround;

    public void setRotation(float yaw, float pitch) {
        this.yaw = yaw;
        this.pitch = pitch;
    }
}
