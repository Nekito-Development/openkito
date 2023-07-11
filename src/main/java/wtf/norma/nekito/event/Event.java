package wtf.norma.nekito.event;

import wtf.norma.nekito.Nekito;

public abstract class Event {

    private final EventState state;
    private boolean cancelled;

    public Event(EventState state) {
        this.state = state;
    }

    public Event() {
        this(EventState.NONE);
    }

    public void post() {
        Nekito.INSTANCE.getEventBus().post(this).dispatch();
    }

    public void cancel() {
        cancelled = true;
    }

    public EventState getState() {
        return state;
    }

    public boolean isCancelled() {
        return cancelled;
    }
}