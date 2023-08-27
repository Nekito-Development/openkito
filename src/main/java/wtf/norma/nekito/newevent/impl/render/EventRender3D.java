package wtf.norma.nekito.newevent.impl.render;

import wtf.norma.nekito.newevent.Event;

public class EventRender3D extends Event {
    private final float partialTicks;

    //not with lombok to include code optimization with final
    public EventRender3D(final float partialTicks) {
        this.partialTicks = partialTicks;
    }
}
