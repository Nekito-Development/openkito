package wtf.norma.nekito.newevent.impl.movement;

import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EventMotion {
    //Get position of player
    public double x, y, z;
    //get view angle of player
    public float yaw, pitch;
    //check if player in on ground
    public boolean onGround;

}
