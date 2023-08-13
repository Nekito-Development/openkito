package wtf.norma.nekito.module.impl;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.event.impl.EventMotion;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.settings.impl.NumberSetting;

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




            if (target != null && !target.isInvisible() && mc.thePlayer.canEntityBeSeen(target)) {
                mc.thePlayer.rotationYaw = rotations(target)[0];
                mc.thePlayer.rotationPitch = rotations(target)[1];
            }
        }
    }
}
