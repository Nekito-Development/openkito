package wtf.norma.nekito.newevent.impl.movement;

import lombok.Getter;
import lombok.Setter;
import wtf.norma.nekito.newevent.Event;

@Getter
@Setter
public class EventPreMotion extends Event {
    //rotation[0] == yaw
    //rotation[1] == pitch
    private float yaw, pitch;
    //position[0] == posX
    //position[1] == posY
    //position[2] == posZ
    private double posX, posY, posZ;
    private boolean onGround;

    public EventPreMotion(float[] rotation, double[] position, boolean onGround) {
        this.yaw = rotation[0];
        this.pitch = rotation[1];
        this.posX = position[0];
        this.posY = rotation[1];
        this.posZ = rotation[2];
        this.onGround = onGround;
    }
    public void setRotation(float yaw, float pitch) {
        this.yaw = yaw;
        this.pitch = pitch;
    }
}
