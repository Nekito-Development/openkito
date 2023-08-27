package wtf.norma.nekito.newevent.impl.action;

import net.minecraft.entity.Entity;
import wtf.norma.nekito.newevent.Event;

public class EventAttack extends Event {
    public final Entity targetEntity;

    public EventAttack(final Entity targetEntity) {
        this.targetEntity = targetEntity;
    }
}
