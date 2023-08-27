package wtf.norma.nekito.module.impl;

import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import me.zero.alpine.listener.Subscriber;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.Nekito;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.newevent.impl.render.EventFogColor;
import wtf.norma.nekito.util.color.ColorUtility;

import java.awt.*;

public class FogColor extends Module implements Subscriber {

    public FogColor() {
        super("FogColor", Category.VISUALS, Keyboard.KEY_NONE);
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
    private final Listener<EventFogColor> listener = new Listener<>(event -> {
        Color customColorValue = new Color(ColorUtility.getColor(0));
        event.setRed(customColorValue.getRed());
        event.setGreen(customColorValue.getGreen());
        event.setBlue(customColorValue.getBlue());
    });

//    @Override
//    public void onEvent(Event event) {
//        if (event instanceof EventFogColor) {
//            Color customColorValue = new Color(ColorUtility.getColor(0));
//            ((EventFogColor) event).setRed(customColorValue.getRed());
//            ((EventFogColor) event).setGreen(customColorValue.getGreen());
//            ((EventFogColor) event).setBlue(customColorValue.getBlue());
//            }
//        }

}



