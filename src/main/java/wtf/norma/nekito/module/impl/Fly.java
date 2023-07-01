package wtf.norma.nekito.module.impl;

import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.event.impl.EventUpdate;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.settings.impl.BooleanSetting;
import wtf.norma.nekito.settings.impl.ModeSetting;
import wtf.norma.nekito.settings.impl.NumberSetting;

public class Fly extends Module {

    public BooleanSetting widzejegokule = new BooleanSetting("widze jego kule", false);
    public ModeSetting nachujnapierdalasz = new ModeSetting("Mode", "na chuj", "na chuj", "napierdalasz", "xd");
    public NumberSetting xd = new NumberSetting("xdddd", 10, 0, 100, 1);
    public Fly() {
        super("Fly", Category.MOVEMENT, Keyboard.KEY_F);
        toggle();
        this.addSettings(widzejegokule, nachujnapierdalasz, xd);
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
        mc.thePlayer.capabilities.isFlying = false;
    }

    @Override
    public void onEvent(Event e) {
        if (e instanceof EventUpdate) {
            if (e.isPre()) {
                mc.thePlayer.capabilities.isFlying = true;
            }
        }
    }
}
