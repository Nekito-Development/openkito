package wtf.norma.nekito.module.impl.combat;

import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import me.zero.alpine.listener.Subscriber;
import net.minecraft.item.ItemAppleGold;
import net.minecraft.item.ItemStack;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.Nekito;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.event.impl.update.EventUpdate;
import wtf.norma.nekito.settings.impl.NumberSetting;


public class Autogapple extends Module implements Subscriber {

    public Autogapple() {
        super("Autogapple", Category.COMBAT, Keyboard.KEY_NONE);
        addSettings(zycko);
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

    public NumberSetting zycko = new NumberSetting("Health", 15, 1, 20, 1);

    private boolean ratted;

    @Subscribe
    private final Listener<EventUpdate> listener = new Listener<>(event ->{
        if (isGoldenApple(mc.thePlayer.getHeldItem()) && mc.thePlayer.getHealth() <= zycko.getValue()) {
            ratted = true;
            mc.gameSettings.keyBindUseItem.pressed = true;
        } else if (ratted) {
            mc.gameSettings.keyBindUseItem.pressed = false;
            ratted = false;
        }
    });

//    @Override
//    public void onEvent(Event e) {
//        if (e instanceof EventUpdate) {
//            if (isGoldenApple(mc.thePlayer.getHeldItem()) && mc.thePlayer.getHealth() <= zycko.getValue()) {
//                ratted = true;
//                mc.gameSettings.keyBindUseItem.pressed = true;
//            } else if (ratted) {
//                mc.gameSettings.keyBindUseItem.pressed = false;
//                ratted = false;
//            }
//        }
//    }


    private boolean isGoldenApple(ItemStack itemStack) {
        return (itemStack != null && !itemStack.isEmpty() && itemStack.getItem() instanceof ItemAppleGold);
    }

}
