package wtf.norma.nekito.newevent.impl.movement;

import wtf.norma.nekito.newevent.Event;

public class EventStrafe extends Event {
    private final float strafe,  forward, friction;

    //code optimization with final blabla
    public EventStrafe(final float strafe, final float forward, final float friction) {
        this.strafe = strafe;
        this.forward = forward;
        this.friction = friction;
    }
}
