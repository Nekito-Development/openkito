package wtf.norma.nekito.module.impl;

import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import me.zero.alpine.listener.Subscriber;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.Nekito;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.newevent.impl.update.EventUpdate;
import wtf.norma.nekito.settings.impl.NumberSetting;


public class TriggerBot extends Module implements Subscriber {


    public NumberSetting cwel = new NumberSetting("Reach", 3.0f, 1f, 5.0f, 0.1f);

    public TriggerBot() {
        super("TriggerBot", Category.COMBAT, Keyboard.KEY_NONE);
        addSettings(cwel);
    }

    public static boolean sprawdzWyjebkenablik(EntityLivingBase player) {

        if (player instanceof EntityPlayer || player instanceof EntityAnimal || player instanceof EntityMob || player instanceof EntityVillager) {
            if (player instanceof EntityAnimal) {
                return false;
            }
            if (player.isInvisible()) {
                return false;
            }
            if (player instanceof EntityMob) {
                return false;
            }
            if (player instanceof EntityVillager) {
                return false;
            }
        }
        return player != mc.thePlayer;
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
        Entity entity = mc.objectMouseOver.entityHit;
        if (entity == null || mc.thePlayer.getDistanceToEntity(entity) > cwel.getValue() || entity.isDead || ((EntityLivingBase) entity).getHealth() <= 0.0f)
            return;
        if (sprawdzWyjebkenablik((EntityLivingBase) entity)) {
            mc.playerController.attackEntity(mc.thePlayer, entity);
            mc.thePlayer.swingItem();
        }
    });

//    public void onEvent(Event event) {
//        if (event instanceof EventUpdate) {
//            Entity entity = mc.objectMouseOver.entityHit;
//            if (entity == null || mc.thePlayer.getDistanceToEntity(entity) > cwel.getValue() || entity.isDead || ((EntityLivingBase) entity).getHealth() <= 0.0f) {
//                return;
//            }
//            if (sprawdzWyjebkenablik((EntityLivingBase) entity)) {
//                mc.playerController.attackEntity(mc.thePlayer, entity);
//                mc.thePlayer.swingItem();
//            }
//        }
//    }

}
