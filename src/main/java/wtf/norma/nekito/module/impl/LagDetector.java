package wtf.norma.nekito.module.impl;

import me.zero.alpine.listener.Subscribe;
import me.zero.alpine.listener.Subscriber;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.Nekito;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.newevent.Event;
import wtf.norma.nekito.newevent.impl.render.EventRender2D;
import wtf.norma.nekito.newevent.impl.update.EventUpdate;
import wtf.norma.nekito.settings.impl.NumberSetting;
import wtf.norma.nekito.util.Time.TimerUtility;
import wtf.norma.nekito.util.render.RenderUtility;

import java.awt.*;

public class LagDetector extends Module implements Subscriber {

    public NumberSetting ping = new NumberSetting("Ping", 80, 50, 1000, 10);
    TimerUtility timer = new TimerUtility();

    public LagDetector() {
        super("Lag Detector", Category.VISUALS, Keyboard.KEY_NONE);
        addSettings(ping);
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

    // images are not mine
    @Subscribe
    private final void onEvent(Event event) {
//        System.out.println("DEBUG");
        if (event instanceof EventRender2D) {
//            System.out.println("DEBUG2");
            ScaledResolution sr = new ScaledResolution(mc);
            if (timer.hasReached(ping.getValue())) {
                if (timer.hasReached(ping.getValue() + 10)) {
                    RenderUtility.drawImage(new ResourceLocation("images/cwelowate/lag2.png"), sr.getScaledWidth() / 2 - 20, sr.getScaledHeight() / 2 - 130, 40, 40, new Color(255, 255, 255));
                } else {
                    RenderUtility.drawImage(new ResourceLocation("images/cwelowate/lag.png"), sr.getScaledWidth() / 2 - 20, sr.getScaledHeight() / 2 - 130, 40, 40, new Color(255, 255, 255));
                }
            }
        }
        if (event instanceof EventUpdate) {
            if (!(((EventUpdate) event).getPacket() instanceof S02PacketChat)) {
                this.timer.reset();
            }
        }
    }

    //Doesn't work for some fucking reason
//    @Subscribe
//    private final Listener<EventRender2D> listener1 = new Listener<>(event -> {
//            ScaledResolution sr = new ScaledResolution(mc);
//            if (timer.hasReached(ping.getValue())) {
//                if (timer.hasReached(ping.getValue() + 10)) {
//                    RenderUtility.drawImage(new ResourceLocation("images/cwelowate/lag2.png"), sr.getScaledWidth() / 2 - 20, sr.getScaledHeight() / 2 - 130, 40, 40, new Color(255, 255, 255));
//                } else {
//                    RenderUtility.drawImage(new ResourceLocation("images/cwelowate/lag.png"), sr.getScaledWidth() / 2 - 20, sr.getScaledHeight() / 2 - 130, 40, 40, new Color(255, 255, 255));
//                }
//            }
//    });
//
//    @Subscribe
//    private final Listener<EventUpdate> listener2 = new Listener<>(event ->{
//        if (event.getPacket() instanceof S02PacketChat) this.timer.reset();
//    });

    // images are not mine
//    @Override
//    public void onEvent(Event e) {
//        if (e instanceof EventRender2D) {
//            ScaledResolution sr = new ScaledResolution(mc);
//            if (timer.hasReached(ping.getValue())) {
//                if (timer.hasReached(ping.getValue() + 10)) {
//                    RenderUtility.drawImage(new ResourceLocation("images/cwelowate/lag2.png"), sr.getScaledWidth() / 2 - 20, sr.getScaledHeight() / 2 - 130, 40, 40, new Color(255, 255, 255));
//                } else {
//                    RenderUtility.drawImage(new ResourceLocation("images/cwelowate/lag.png"), sr.getScaledWidth() / 2 - 20, sr.getScaledHeight() / 2 - 130, 40, 40, new Color(255, 255, 255));
//                }
//            }
//        }
//        if (e instanceof EventUpdate) {
//            if (!(((EventUpdate) e).getPacket() instanceof S02PacketChat)) {
//                this.timer.reset();
//            }
//        }
//    }





}


