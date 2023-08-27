package wtf.norma.nekito.module.impl.movement;

import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import me.zero.alpine.listener.Subscriber;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.Nekito;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.event.impl.update.EventPreUpdate;

public class Sprint extends Module implements Subscriber {

    public Sprint() {
        super("Sprint", Category.MOVEMENT, Keyboard.KEY_NONE);
    }


    @Override
    public void onEnable() {
        Nekito.EVENT_BUS.subscribe(this);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        Nekito.EVENT_BUS.unsubscribe(this);
        mc.thePlayer.setSprinting(false);
        super.onDisable();
    }

    @Subscribe
    private final Listener<EventPreUpdate> listener = new Listener<>(event ->{
        KeyBinding.setKeyBindState(mc.gameSettings.keyBindSprint.getKeyCode(), true);
    });

//    @Override
//    public void onEvent(Event e) {
//        if (e instanceof EventUpdate) {
//            // SLAYYYYYYYYYYYYYYY 🙄🙄🙄🙄🙄🙄🙄🙄🙄🙄🙄😴🥱😫😪😪
//            mc.gameSettings.keyBindSprint.pressed = true;
//
//        }
//    }
}
