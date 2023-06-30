package wtf.norma.nekito.module.impl;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.module.Module;


public class ItemPhysics extends Module {

    public ItemPhysics() {
        super("ItemPhysics", Category.VISUALS, Keyboard.KEY_NONE);
        toggle();
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }


}
