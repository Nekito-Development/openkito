package wtf.norma.nekito.newevent.impl.movement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import wtf.norma.nekito.newevent.Event;

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
