package wtf.norma.nekito.module.impl;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.settings.impl.ModeSetting;

/*
author @intexpression
 */



public class Cape extends Module {

    public static ModeSetting selectedCape = new ModeSetting("Cape Type", "Black", "Black", "Pink","Blue","shit");

    public Cape() {
        super("Cape", Category.VISUALS, Keyboard.KEY_NONE);
        this.addSettings(selectedCape);
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
        switch (selectedCape.getMode()) {

            case "Black":
                return new ResourceLocation("images/cape/black.png");
            case "Pink":
                return new ResourceLocation("images/cape/pink.png");
            case "Blue":
                return new ResourceLocation("images/cape/blue.png");
            case"shit":
                return new ResourceLocation("images/cape/paste.png");
            default:
                return new ResourceLocation("images/cape/black.png");
        }
    }


    public boolean canRender(AbstractClientPlayer player) {
        return player == mc.thePlayer;
    }

}
