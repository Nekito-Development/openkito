package wtf.norma.nekito.module.impl;

import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.event.impl.EventUpdate;
import wtf.norma.nekito.helper.ChatHelper;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.settings.impl.ModeSetting;

import java.util.Random;


// OSZUSTWO W GRA KOMPUTEROWA KLOC I.N.C
// CWEL.PL COPYRIGHT

public class Eagle extends Module {


    public Eagle() {
        super("Eagle", Category.LEGIT, Keyboard.KEY_NONE);
    }

    @Override
    public void onEnable() {
        ChatHelper.printMessage("this module is in beta(you can have issues with it)");
        super.onEnable();
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onEvent(Event e) {

        if (e instanceof EventUpdate) {
            mc.thePlayer.setSprinting(false);
            mc.gameSettings.keyBindSprint.pressed = false;
            BlockPos blockPos = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - 1.0, mc.thePlayer.posZ);
            mc.gameSettings.keyBindSneak.pressed = mc.theWorld.getBlockState(blockPos).getBlock() == Blocks.air;
        }
    }
}
