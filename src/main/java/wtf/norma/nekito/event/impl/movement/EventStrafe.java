package wtf.norma.nekito.event.impl.movement;

import wtf.norma.nekito.event.Event;

public class EventStrafe extends Event {
    private final float strafe,  forward, friction;

    //code optimization with final blabla
    public EventStrafe(final float strafe, final float forward, final float friction) {
        this.strafe = strafe;
        this.forward = forward;
        this.friction = friction;
    }
}
