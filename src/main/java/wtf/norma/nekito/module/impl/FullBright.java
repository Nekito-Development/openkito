package wtf.norma.nekito.module.impl;

import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import me.zero.alpine.listener.Subscriber;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.Nekito;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.newevent.impl.update.EventUpdate;
import wtf.norma.nekito.settings.impl.ModeSetting;

public class FullBright extends Module implements Subscriber {

    private final ModeSetting mode = new ModeSetting("Mode", "Gamma", "Gamma", "Potion");
    private float oldGamma;
    public FullBright() {
        super("FullBright", Category.VISUALS, Keyboard.KEY_NONE);
        //    toggle();
        this.addSettings(mode);
    }

    @Override
    public void onEnable() {
        Nekito.EVENT_BUS.subscribe(this);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        Nekito.EVENT_BUS.unsubscribe(this);
        switch (mode.getMode()) {
            case "Gamma":
                mc.gameSettings.gammaSetting = oldGamma;
                break;

            case "Potion":
                mc.thePlayer.removePotionEffect(Potion.nightVision.getId());
                break;
        }
    }

    @Subscribe
    private final Listener<EventUpdate> listener = new Listener<>(event ->{
        switch (mode.getMode()) {
            case "Gamma":
                mc.gameSettings.gammaSetting = 100;
                break;
            case "Potion":
                mc.thePlayer.addPotionEffect(new PotionEffect(Potion.nightVision.getId(), Integer.MAX_VALUE));
                break;
        }
    });

//    @Override
//    public void onEvent(Event e) {
//        if (e instanceof EventUpdate) {
//            switch (mode.getMode()) {
//                case "Gamma":
//                    mc.gameSettings.gammaSetting = 100;
//                    break;
//                case "Potion":
//                    mc.thePlayer.addPotionEffect(new PotionEffect(Potion.nightVision.getId(), Integer.MAX_VALUE));
//                    break;
//            }
//        }
//    }
}
