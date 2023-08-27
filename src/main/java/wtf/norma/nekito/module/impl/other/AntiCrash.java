package wtf.norma.nekito.module.impl.other;

import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import me.zero.alpine.listener.Subscriber;
import net.minecraft.entity.Entity;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.Nekito;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.event.impl.update.EventUpdate;
import wtf.norma.nekito.settings.impl.BooleanSetting;
import wtf.norma.nekito.util.other.LoggingUtil;


public class AntiCrash extends Module implements Subscriber {

  public BooleanSetting armorstand = new BooleanSetting("Anti ArmorStand", true);

    public BooleanSetting antiparticle = new BooleanSetting("Anti Particle", true);
    public BooleanSetting memorywyczyszczenie = new BooleanSetting("Clean Memory", true);




    public AntiCrash() {
        super("Optimizator", Category.OTHER, Keyboard.KEY_NONE);  // rofl kartofel
        addSettings(armorstand, antiparticle, memorywyczyszczenie);
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
        if (mc.thePlayer == null || mc.theWorld == null) {
            return;
        }

        //anti armorstand
        if (armorstand.isEnabled()) {

            for (Entity cwel : mc.theWorld.loadedEntityList) {

                // CUZ OF  SOME  SERVERS "PROTECTION" $$$$$$$$$$$$$$

                if (cwel == null || !(cwel instanceof net.minecraft.entity.item.EntityArmorStand))
                    continue;

                mc.theWorld.removeEntity(cwel);
                LoggingUtil.log("Removed " + cwel.getName());
            }
        }

        //clean memory

        if (memorywyczyszczenie.isEnabled()){
            System.gc(); // ok ok ok?
        }

        if (antiparticle.isEnabled()) {
            mc.gameSettings.ofVoidParticles = false;
        }
    });

//    public void onEvent(Event e) {
//        if (e instanceof EventUpdate) {
//
//            if (mc.thePlayer == null || mc.theWorld == null) {
//                return;
//            }
//
//            //anti armorstand
//            if (armorstand.isEnabled()) {
//
//                for (Entity cwel : mc.theWorld.loadedEntityList) {
//
//                    // CUZ OF  SOME  SERVERS "PROTECTION" $$$$$$$$$$$$$$
//
//                    if (cwel == null || !(cwel instanceof net.minecraft.entity.item.EntityArmorStand))
//                        continue;
//
//                    mc.theWorld.removeEntity(cwel);
//                    LoggingUtil.log("Removed " + cwel.getName());
//                }
//            }
//
//            //clean memory
//
//            if (memorywyczyszczenie.isEnabled()){
//                System.gc(); // ok ok ok?
//            }
//
//
//
//
//            if (antiparticle.isEnabled()) {
//                mc.gameSettings.ofVoidParticles = false;
//            }
//
//
//
//
//
//
//        }
//    }

}