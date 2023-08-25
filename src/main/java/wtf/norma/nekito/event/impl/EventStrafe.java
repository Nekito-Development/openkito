package wtf.norma.nekito.event.impl;

import wtf.norma.nekito.event.Event;

public class EventStrafe extends Event {

    public float strafe,  forward, friction;

    public EventStrafe(float strafe,  float forward, float friction) {
        this.strafe = strafe;
        this.forward = forward;
        this.friction = friction;
    }
}
