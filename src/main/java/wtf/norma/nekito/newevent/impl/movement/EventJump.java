package wtf.norma.nekito.newevent.impl.movement;

import wtf.norma.nekito.newevent.Event;

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
