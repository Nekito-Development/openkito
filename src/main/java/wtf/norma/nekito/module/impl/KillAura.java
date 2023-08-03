package wtf.norma.nekito.module.impl;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.util.MathHelper;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.event.impl.EventMotion;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.settings.impl.BooleanSetting;
import wtf.norma.nekito.settings.impl.ModeSetting;
import wtf.norma.nekito.settings.impl.NumberSetting;
import wtf.norma.nekito.util.Time.TimerUtility;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class KillAura extends Module {

    public static EntityLivingBase target;

    public TimerUtility t = new TimerUtility();
    public NumberSetting ZASIEGCHUJA = new NumberSetting("Range", 3, 1, 6, 0.5f);
    public NumberSetting discord = new NumberSetting("APS", 12, 1, 20, 0.1f);
    public ModeSetting rotations = new ModeSetting("Rotations", "Basic", "Basic", "None");
    public BooleanSetting onlyPlayers = new BooleanSetting("Only players", true);

    //
    public KillAura() {
        super("KillAura", Category.COMBAT, Keyboard.KEY_R);
        this.addSettings(rotations, ZASIEGCHUJA, discord, onlyPlayers);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        target = null;
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public float[] rotations(EntityLivingBase entity) {
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
            if (e.isPre()) {
                target = getTarget(ZASIEGCHUJA.getValue());

                if (target != null) {
                    if (rotate(target, (EventMotion) e)) {
                        if (t.hasReached((long) (1000 / discord.getValue()))) {
                            mc.thePlayer.swingItem();
                            mc.getNetHandler().getNetworkManager().sendPacketNoEvent(new C02PacketUseEntity(target, C02PacketUseEntity.Action.ATTACK));
                            t.reset();
                        }
                    }
                }
            }
        }
    }

    public boolean rotate(EntityLivingBase target, EventMotion event) {
        switch (rotations.getMode()) {
            case "Basic":
                float[] rots = rotations(target);
                event.setYaw(rots[0]);
                event.setPitch(rots[0]);
                mc.thePlayer.rotationYawHead = rots[0];
                mc.thePlayer.renderYawOffset = rots[0];
                break;
            case "None":
                break;
        }
        return true;
    }

    public EntityLivingBase getTarget(double range) {
        List<Entity> targets = mc.theWorld.getLoadedEntityList().stream().filter(entity -> entity instanceof EntityLivingBase).filter(entity -> entity != mc.thePlayer).filter(entity -> !entity.isDead).filter(entity -> mc.thePlayer.getDistanceToEntity(entity) <= range).sorted(Comparator.comparingDouble(entity -> mc.thePlayer.getDistanceToEntity(entity))).collect(Collectors.toList());

        if (onlyPlayers.isEnabled()) targets = targets.stream().filter(EntityPlayer.class::isInstance).collect(Collectors.toList());

        if (!targets.isEmpty()) {
            EntityLivingBase target = (EntityLivingBase) targets.get(0);
            return target;
        }
        return null;
    }

}
