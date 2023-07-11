package wtf.norma.nekito.event.impl;

import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.event.EventState;

public class MotionUpdateEvent extends Event {

    private double x, y, z;
    private float yaw, pitch;
    private boolean ground;

    public MotionUpdateEvent(EventState state, double x, double y, double z, float yaw, float pitch, boolean ground) {
        super(state);
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.ground = ground;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public boolean isGround() {
        return ground;
    }

    public void setGround(boolean ground) {
        this.ground = ground;
    }
}
