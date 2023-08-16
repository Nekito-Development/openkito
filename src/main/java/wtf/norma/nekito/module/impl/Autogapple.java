package wtf.norma.nekito.module.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.item.ItemAppleGold;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.event.impl.EventRender2D;
import wtf.norma.nekito.event.impl.EventUpdate;
import wtf.norma.nekito.helper.ChatHelper;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.settings.impl.NumberSetting;
import wtf.norma.nekito.util.render.RenderUtility;

import java.awt.*;


public class Autogapple extends Module {

    public Autogapple() {
        super("Autogapple", Category.VISUALS, Keyboard.KEY_NONE);
        addSettings(zycko);
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }
    @Override
    public void onDisable() {
        super.onDisable();
    }

    public NumberSetting zycko = new NumberSetting("Health", 15, 1, 20, 1);

    private boolean ratted;
    @Override
    public void onEvent(Event e) {
        if (e instanceof EventUpdate) {
            if (isGoldenApple(mc.thePlayer.getHeldItem()) && mc.thePlayer.getHealth() <= zycko.getValue()) {
                ratted = true;
                mc.gameSettings.keyBindUseItem.pressed = true;
            } else if (ratted) {
                mc.gameSettings.keyBindUseItem.pressed = false;
                ratted = false;
            }
        }
    }
    private boolean isGoldenApple(ItemStack itemStack) {
        return (itemStack != null && !itemStack.isEmpty() && itemStack.getItem() instanceof ItemAppleGold);
    }


}
