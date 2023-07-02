package wtf.norma.nekito.module.impl;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.module.Module;


public class Cape extends Module {

    public Cape() {
        super("Cape", Category.VISUALS, Keyboard.KEY_NONE);
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }


    public ResourceLocation getCape() {


        return new ResourceLocation("images/cape/cape.png");
    }


    public boolean canRender(AbstractClientPlayer player) {
        return player == mc.thePlayer;
    }

}
