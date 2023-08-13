package wtf.norma.nekito.module.impl;

import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.event.impl.EventUpdate;
import wtf.norma.nekito.module.Module;


public class NoSlowDown extends Module {


    Minecraft mc = Minecraft.getMinecraft();

    public NoSlowDown() {
        super("No Slow Down", Category.MOVEMENT, Keyboard.KEY_NONE);
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Override
    public void onEvent(Event e) {
        if (e instanceof EventUpdate) {
            // cum


        }
    }
}
