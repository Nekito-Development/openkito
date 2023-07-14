package wtf.norma.nekito.module.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import org.apache.commons.lang3.RandomUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.event.impl.EventMotion;
import wtf.norma.nekito.event.impl.EventUpdate;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.settings.impl.NumberSetting;
import wtf.norma.nekito.util.Time.Timer;

import java.util.Comparator;


public class AimBot extends Module {

    public NumberSetting ZASIEGCHUJA = new NumberSetting("Range", 3, 1, 6, 0.5f);

    public AimBot() {
        super("AimBot", Category.LEGIT, Keyboard.KEY_NONE);
        this.addSettings(ZASIEGCHUJA);
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public float[] rotations(EntityPlayer entity) {
        double x = entity.posX - mc.thePlayer.posX;
        double y = entity.posY - (mc.thePlayer.posY + mc.thePlayer.getEyeHeight()) + 1.5;
        double z = entity.posZ - mc.thePlayer.posZ;

        double u = MathHelper.sqrt(x * x + z * z);

        float u2 = (float) (MathHelper.atan2(z, x) * (180D / Math.PI) - 90.0F);
        float u3 = (float) (-MathHelper.atan2(y, u) * (180D / Math.PI));

        return new float[]{u2, u3};
    }


    @Override
    public void onEvent(Event e) {
        if (e instanceof EventMotion) {
            EntityPlayer target = mc.theWorld.playerEntities.stream().filter(entityPlayer -> entityPlayer != mc.thePlayer).min(Comparator.comparing(entityPlayer ->
                    entityPlayer.getDistanceToEntity(mc.thePlayer))).filter(entityPlayer -> entityPlayer.getDistanceToEntity(mc.thePlayer) <= ZASIEGCHUJA.getValue()).orElse(null);

            if (target != null && mc.thePlayer.canEntityBeSeen(target)) {
                mc.thePlayer.rotationYaw = rotations(target)[0];
                mc.thePlayer.rotationPitch = rotations(target)[1];
            }
        }
    }
}
