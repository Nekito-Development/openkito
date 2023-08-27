package wtf.norma.nekito.module.impl;

import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import me.zero.alpine.listener.Subscriber;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.Nekito;
import wtf.norma.nekito.helper.ChatHelper;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.newevent.impl.update.EventUpdate;


// OSZUSTWO W GRA KOMPUTEROWA KLOC I.N.C
// CWEL.PL

public class Eagle extends Module implements Subscriber {

    // orzel called

    public Eagle() {
        super("Eagle", Category.LEGIT, Keyboard.KEY_NONE);
    }

    @Override
    public void onEnable() {
        Nekito.EVENT_BUS.subscribe(this);
        ChatHelper.printMessage("this module is in beta(you can have issues with it)");
        super.onEnable();
    }


    @Override
    public void onDisable() {
        Nekito.EVENT_BUS.unsubscribe(this);
        int key = mc.gameSettings.keyBindUseItem.getKeyCode();
        // orzel utopil sie w swetrze
        KeyBinding.setKeyBindState(key, false);
        mc.gameSettings.keyBindSneak.pressed = false;
        super.onDisable();
    }

    @Subscribe
    private final Listener<EventUpdate> listener = new Listener<>(event ->{
        mc.thePlayer.setSprinting(false);
        mc.gameSettings.keyBindSprint.pressed = false;
        BlockPos blockPos = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - 1.0, mc.thePlayer.posZ);
        mc.gameSettings.keyBindSneak.pressed = mc.theWorld.getBlockState(blockPos).getBlock() == Blocks.air;
        int key = mc.gameSettings.keyBindUseItem.getKeyCode();
        KeyBinding.setKeyBindState(key, true);
        KeyBinding.onTick(key);
    });

//    @Override
//    public void onEvent(Event e) {
//        if (e instanceof EventUpdate) {
//            mc.thePlayer.setSprinting(false);
//            mc.gameSettings.keyBindSprint.pressed = false;
//            BlockPos blockPos = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - 1.0, mc.thePlayer.posZ);
//            mc.gameSettings.keyBindSneak.pressed = mc.theWorld.getBlockState(blockPos).getBlock() == Blocks.air;
//            int key = mc.gameSettings.keyBindUseItem.getKeyCode();
//            KeyBinding.setKeyBindState(key, true);
//            KeyBinding.onTick(key);
//        }
//    }
}
