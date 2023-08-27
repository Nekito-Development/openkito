package wtf.norma.nekito.event.impl.movement;

import wtf.norma.nekito.event.Event;

public class EventJump extends Event {
    private double motionY;

    public EventJump(double motionY) {
        this.motionY = motionY;
    }

    public double getMotionY() {
        return motionY;
    }
    public void setMotionY(double motiony) {
        this.motionY = motiony;
    }

}
