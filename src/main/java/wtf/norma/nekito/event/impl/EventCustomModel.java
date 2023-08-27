package wtf.norma.nekito.event.impl;

import net.minecraft.client.entity.AbstractClientPlayer;
import wtf.norma.nekito.event.Event;

public class EventCustomModel extends Event {
    private AbstractClientPlayer player;

    public EventCustomModel(AbstractClientPlayer player) {
        this.player = player;
    }

    public void setPlayer(AbstractClientPlayer player) {
        this.player = player;
    }

    public AbstractClientPlayer getPlayer() {
        return this.player;
    }
}