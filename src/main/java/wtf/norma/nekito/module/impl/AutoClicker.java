package wtf.norma.nekito.module.impl;


import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import org.apache.commons.lang3.RandomUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.event.impl.EventUpdate;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.settings.impl.NumberSetting;
import wtf.norma.nekito.util.Time.Timer;


public class AutoClicker extends Module {

    public NumberSetting cwelMIN = new NumberSetting("Min APS",12,1,20,1);
    public NumberSetting cwelMAX = new NumberSetting("Max APS",15,1,20,1);
    private final Timer timer = new Timer();
    public AutoClicker() {
        super("AutoClicker", Category.LEGIT, Keyboard.KEY_NONE);
        this.addSettings(cwelMIN,cwelMAX);
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
        if (Minecraft.getMinecraft().currentScreen == null && Mouse.isButtonDown(0)) {
            if (timer.hasReached(1000 / RandomUtils.nextInt((int) cwelMIN.getValue(), (int) cwelMAX.getValue()))) {
                KeyBinding.setKeyBindState(-100, true);
                KeyBinding.onTick(-100);
                timer.reset();
            } else {
                KeyBinding.setKeyBindState(-100, false);
            }
        }
    }
}
