package wtf.norma.nekito.event.impl.action;

import net.minecraft.entity.Entity;
import wtf.norma.nekito.event.Event;

public class EventAttack extends Event {
    public final Entity targetEntity;

    public EventAttack(final Entity targetEntity) {
        this.targetEntity = targetEntity;
    }
}
