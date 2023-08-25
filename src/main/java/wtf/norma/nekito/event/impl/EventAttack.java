package wtf.norma.nekito.event.impl;

import net.minecraft.entity.Entity;
import wtf.norma.nekito.event.Event;

public class EventAttack extends Event {
    public Entity targetEntity;

    public EventAttack(Entity targetEntity) {
        this.targetEntity = targetEntity;
    }

}
