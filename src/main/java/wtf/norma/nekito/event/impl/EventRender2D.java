package wtf.norma.nekito.event.impl;

import net.minecraft.client.gui.ScaledResolution;
import wtf.norma.nekito.event.Event;

public class EventRender2D extends Event {

    private final ScaledResolution scaledResolution;
    private final float partialTicks;

    public EventRender2D(ScaledResolution scaledResolution, float partialTicks) {
        super();
        this.scaledResolution = scaledResolution;
        this.partialTicks = partialTicks;
    }

    public ScaledResolution getScaledResolution() {
        return scaledResolution;
    }

    public float getPartialTicks() {
        return partialTicks;
    }
}
