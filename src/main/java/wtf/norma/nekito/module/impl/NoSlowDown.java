package wtf.norma.nekito.module.impl;

import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import me.zero.alpine.listener.Subscriber;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.Nekito;
import wtf.norma.nekito.helper.ChatHelper;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.event.impl.update.EventUpdate;


public class NoSlowDown extends Module implements Subscriber {


    Minecraft mc = Minecraft.getMinecraft();

    public NoSlowDown() {
        super("No Slow Down", Category.MOVEMENT, Keyboard.KEY_NONE);
    }

    @Override
    public void onEnable() {
        ChatHelper.printMessage("This shit is not implemented lmao", true);
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

    });
}
