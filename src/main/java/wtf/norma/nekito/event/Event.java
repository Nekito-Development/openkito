package wtf.norma.nekito.event;

import lombok.Getter;
import lombok.Setter;
import me.zero.alpine.event.EventPhase;

//Removes ugly boilerplate code
@Getter
@Setter
public class Event {

    //Sets if the event is cancelled
    public boolean cancelled;
    //previously EventType, now EventPhase. Sets the phase the event is in
    public EventPhase eventPhase;
    //Sets the flow of the event. Either inbound or outbound
    public EventFlow eventFlow;


    public boolean isPre() {
        if (eventPhase == null) return false;
        return eventPhase == EventPhase.PRE;
    }

    public boolean isOn() {
        if (eventPhase == null) return false;
        return eventPhase == EventPhase.ON;
    }

    public boolean isPost() {
        if (eventPhase == null) return false;
        return eventPhase == EventPhase.POST;
    }

    public boolean isInbound() {
        if (eventFlow == null) return false;
        return eventFlow == EventFlow.INBOUND;
    }

    public boolean isOutbound() {
        if (eventFlow == null) return false;
        return eventFlow == EventFlow.OUTBOUND;
    }
}
