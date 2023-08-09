package wtf.norma.nekito.module.impl;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.event.impl.EventMotion;
import wtf.norma.nekito.event.impl.EventRender2D;
import wtf.norma.nekito.event.impl.EventUpdate;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.settings.impl.BooleanSetting;
import wtf.norma.nekito.settings.impl.ModeSetting;
import wtf.norma.nekito.settings.impl.NumberSetting;

import wtf.norma.nekito.util.Time.TimerUtility;
import wtf.norma.nekito.util.color.ColorUtils;
import wtf.norma.nekito.util.font.Fonts;
import wtf.norma.nekito.util.player.MovementUtil;

import java.math.BigDecimal;
import java.util.Random;

public class Fly extends Module {
    public ModeSetting mode = new ModeSetting("Mode", "Capabilities", "Capabilities", "Motion");
    public NumberSetting speed = new NumberSetting("Speed", 0.5f, 0, 10, 0.1f);
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
        mc.timer.timerSpeed = 1f;
        mc.thePlayer.capabilities.isFlying = false;
        mc.thePlayer.motionY = 0;
        mc.thePlayer.capabilities.setFlySpeed(0.05f);
        if (stopOnDisable.isEnabled()) new MovementUtil().strafe(0);
    }

    private final TimerUtility cwel = new TimerUtility();

    @Override
    public void onEvent(Event e) {
        if (e instanceof EventMotion) {
            if (e.isPre()) {
                switch (mode.getMode().toUpperCase()) {
                    case "CAPABILITIES":
                        mc.thePlayer.capabilities.isFlying = true;
                        mc.thePlayer.capabilities.setFlySpeed((float) speed.getValue());
                        break;
                    case "MOTION":
                        MovementUtil.setMotion(speed.getValue());
                        if (mc.gameSettings.keyBindSneak.isKeyDown()) mc.thePlayer.motionY -= speed.getValue() / 2;
                        if (mc.gameSettings.keyBindJump.isKeyDown()) mc.thePlayer.motionY += speed.getValue() / 2;
                        if (mc.thePlayer.movementInput.jump) {
                            mc.thePlayer.motionY = 0.5f;
                        }
                        // i was drunk ok?
                        else if (mc.thePlayer.movementInput.sneak) {
                            mc.thePlayer.motionY = -0.5f;
                        } else {
                            mc.thePlayer.motionY = 0;
                        }
                        break;


                }
            }
        }

    }
}