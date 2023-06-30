package wtf.norma.nekito.event;

import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.nekito;

public class Event {

    public EventType type;
    public boolean cancelled;

    public static void dispatch(Event e) {
        nekito.INSTANCE.getModuleManager().getModules().stream().filter(Module::isToggled).forEach(m -> m.onEvent(e));
    }

    public boolean isPre() {
        return type != null && type == EventType.PRE;
    }

    public boolean isPost() {
        return type != null && type == EventType.POST;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
