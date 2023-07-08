package wtf.norma.nekito.module.impl;

import net.minecraft.network.play.client.C03PacketPlayer;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.event.impl.EventUpdate;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.settings.impl.BooleanSetting;
import wtf.norma.nekito.settings.impl.ModeSetting;
import wtf.norma.nekito.settings.impl.NumberSetting;
import wtf.norma.nekito.util.player.MovementUtil;

public class Fly extends Module {
    public ModeSetting mode = new ModeSetting("Mode", "Capabilities", "Capabilities", "Motion");
    public NumberSetting speed = new NumberSetting("Speed", 0.05f, 0, 10, 0.1f);
    public BooleanSetting stopOnDisable = new BooleanSetting("Stop on disable", true);
    double startY = 0;
    public Fly() {
        super("Fly", Category.MOVEMENT, Keyboard.KEY_F);
        this.addSettings(mode, speed, stopOnDisable);
    }

    @Override
    public void onEnable() {
        startY = mc.thePlayer.posY;
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
        mc.thePlayer.capabilities.isFlying = false;
        mc.thePlayer.capabilities.setFlySpeed(0.05f);
        if (stopOnDisable.isEnabled()) new MovementUtil().strafe(0);
    }

    @Override
    public void onEvent(Event e) {
        if (e instanceof EventUpdate) {
            if (e.isPre()) {

                switch (mode.getMode().toUpperCase()) {
                    case "CAPABILITIES":
                        mc.thePlayer.capabilities.isFlying = true;
                        mc.thePlayer.capabilities.setFlySpeed(speed.getValue());
                        break;
                    case "MOTION":
                        mc.thePlayer.motionX = 0;
                        mc.thePlayer.motionY = 0;
                        mc.thePlayer.motionZ = 0;

                        if (mc.gameSettings.keyBindSneak.isKeyDown()) mc.thePlayer.motionY -= speed.getValue() / 2;
                        if (mc.gameSettings.keyBindJump.isKeyDown()) mc.thePlayer.motionY += speed.getValue() / 2;

                        new MovementUtil().strafe(speed.getValue());
                        break;
                }
            }
        }
    }
}
