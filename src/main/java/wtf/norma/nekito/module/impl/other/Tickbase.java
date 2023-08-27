package wtf.norma.nekito.module.impl.other;

import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import me.zero.alpine.listener.Subscriber;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.Nekito;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.event.impl.update.EventUpdate;

/**
 * @author eleczka
 * @project nekito
 * @prod hackerzy mysliborz S.A
 * @at 06.08, 14:46
 */

public class Tickbase extends Module implements Subscriber {

    // KARTOFEL POLSKA AGREEED

    public Tickbase() {
        super("Tick base", Category.OTHER, Keyboard.KEY_NONE);
    }

    @Override
    public void onEnable() {
        Nekito.EVENT_BUS.subscribe(this);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        Nekito.EVENT_BUS.unsubscribe(this);
        super.onDisable();
    }

    @Subscribe
    private final Listener<EventUpdate> listener = new Listener<>(event ->{
        //TODO: Implement something idk
    });
}
