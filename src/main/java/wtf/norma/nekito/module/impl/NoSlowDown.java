package wtf.norma.nekito.module.impl;

import de.gerrygames.viarewind.utils.PacketUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.settings.impl.ModeSetting;
import wtf.norma.nekito.settings.impl.NumberSetting;
import wtf.norma.nekito.util.packet.PacketUtility;

import java.util.Comparator;


public class NoSlowDown extends Module {

    public static ModeSetting mode = new ModeSetting("Mode", "Normal", "Normal", "Intave");
    public NoSlowDown() {
        super("NoSlowDown", Category.MOVEMENT, Keyboard.KEY_NONE);
        addSettings(mode);
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    Minecraft mc = Minecraft.getMinecraft();
    @Override
    public void onEvent(Event e) {
        // CWEL POLSKA AGREED?
        if (mc.thePlayer.isUsingItem()) {
        switch (mode.getMode()) {
            case"Normal":

            mc.thePlayer.movementInput.moveForward *= 5F * 98;
            mc.thePlayer.movementInput.moveStrafe *= 5F * 98;
            mc.thePlayer.setSprinting(true);

            if (mc.thePlayer.onGround && !mc.gameSettings.keyBindJump.isKeyDown()) {
                if (mc.thePlayer.ticksExisted % 2 == 0) {
                    mc.thePlayer.motionX *= 0.46;
                    mc.thePlayer.motionZ *= 0.46;
                }
            } else if (mc.thePlayer.fallDistance > 0.2) {
                mc.thePlayer.motionX *= 0.98;
                mc.thePlayer.motionZ *= 0.98;
            }
            break;
            case"Intave":
                if (mc.thePlayer.ticksExisted % 5 == 0) {
                    PacketUtility.sendPacket(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
                    PacketUtility.sendPacket(new C08PacketPlayerBlockPlacement(mc.thePlayer.inventory.getCurrentItem()));
                }
                break;
        }

        }
    }
}
