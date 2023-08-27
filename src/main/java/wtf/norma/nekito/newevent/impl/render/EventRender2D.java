package wtf.norma.nekito.newevent.impl.render;

import wtf.norma.nekito.newevent.Event;

public class EventRender2D extends Event {
    private final float partialTicks;

    //not with lombok to include code optimization with final
    public EventRender2D(final float partialTicks) {
        this.partialTicks = partialTicks;
    }
}
