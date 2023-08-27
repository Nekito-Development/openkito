package wtf.norma.nekito.module.impl;

import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import me.zero.alpine.listener.Subscriber;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.Nekito;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.newevent.impl.render.EventRender2D;
import wtf.norma.nekito.util.color.ColorUtility;
import wtf.norma.nekito.util.font.Fonts;
import wtf.norma.nekito.util.funny.DDOSUTIL;

import java.io.IOException;

public class HELIUMDDOS extends Module implements Subscriber {


    public HELIUMDDOS() {
        super("helium auth disabler", Category.OTHER, Keyboard.KEY_NONE);

    }

    @Override
    public void onEnable() {
        Nekito.EVENT_BUS.subscribe(this);
        try {
            DDOSUTIL.run();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        super.onEnable();
    }

    @Override
    public void onDisable() {
        Nekito.EVENT_BUS.unsubscribe(this);
        super.onDisable();
    }

    @Subscribe
    private final Listener<EventRender2D> listener = new Listener<>(event -> {
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
            try {
                Fonts.SEMI_BOLD_18.drawCenteredStringWithShadow("HELIUM DDOSED TOO HARD.....", sr.getScaledWidth() / 2, sr.getScaledHeight() - 50, ColorUtility.getColor(0));
            } catch (Exception ez) {
                ez.printStackTrace();
            }
    });

//    @Override
//    public void onEvent(Event e) {
//        if (e instanceof EventRender2D) {
//            ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
//            try {
//                Fonts.SEMI_BOLD_18.drawCenteredStringWithShadow("HELIUM DDOSED TOO HARD.....", sr.getScaledWidth() / 2, sr.getScaledHeight() - 50, ColorUtility.getColor(0));
//            } catch (Exception ez) {
//                ez.printStackTrace();
//            }
//        }
//    }
}


