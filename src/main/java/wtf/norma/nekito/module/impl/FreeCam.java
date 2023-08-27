package wtf.norma.nekito.module.impl;

import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import me.zero.alpine.listener.Subscriber;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.Nekito;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.event.impl.packet.PacketEvent;
import wtf.norma.nekito.event.impl.update.EventUpdate;
import wtf.norma.nekito.util.player.MovementUtil;


public class FreeCam extends Module implements Subscriber {

    double oldX, oldY, oldZ;
    private float old;
    private float oldGamma;


    public FreeCam() {
        super("FreeCam", Category.VISUALS, Keyboard.KEY_NONE);

    }

    @Override
    public void onEnable() {
        Nekito.EVENT_BUS.subscribe(this);
        oldX = mc.thePlayer.posX;
        oldY = mc.thePlayer.posY;
        oldZ = mc.thePlayer.posZ;
        oldY = (int) mc.thePlayer.posY;
        EntityOtherPlayerMP fakePlayer = new EntityOtherPlayerMP(mc.theWorld, mc.thePlayer.getGameProfile());
        fakePlayer.copyLocationAndAnglesFrom(mc.thePlayer);
        fakePlayer.posY -= 0.0;
        fakePlayer.rotationYawHead = mc.thePlayer.rotationYawHead;
        mc.theWorld.addEntityToWorld(-69, fakePlayer);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        Nekito.EVENT_BUS.unsubscribe(this);
        mc.thePlayer.capabilities.isFlying = false;
        mc.theWorld.removeEntityFromWorld(-69);
        mc.thePlayer.motionZ = 0.0;
        mc.thePlayer.motionX = 0.0;
        mc.thePlayer.noClip = false;
        mc.thePlayer.setPositionAndRotation(oldX, oldY, oldZ, mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch);
    }

    @Subscribe
    private final Listener<EventUpdate> listener1 = new Listener<>(event ->{
        if (mc.thePlayer == null || mc.theWorld == null) {
            return;
        }
        mc.thePlayer.noClip = true;
        mc.thePlayer.onGround = false;
        if (mc.gameSettings.keyBindJump.isKeyDown()) {
            mc.thePlayer.motionY = 0.5f / 1.5f;
        }
        if (mc.gameSettings.keyBindSneak.isKeyDown()) {
            mc.thePlayer.motionY = -0.5f / 1.5f;
        }
        MovementUtil.setMotion(0.5f);
        mc.thePlayer.capabilities.isFlying = true;
    });

    @Subscribe
    private final Listener<PacketEvent> listener2 = new Listener<>(event ->{
            if (mc.thePlayer == null || mc.theWorld == null || mc.thePlayer.ticksExisted < 1)
                return;
            if (event.getPacket() instanceof S08PacketPlayerPosLook)
                event.setCancelled(true);
    });

//    @Override
//    public void onEvent(Event e) {
//        if (e instanceof PacketEvent) {
//            if (mc.thePlayer == null || mc.theWorld == null || mc.thePlayer.ticksExisted < 1) {
//                return;
//            }
//            if (((PacketEvent) e).getPacket() instanceof S08PacketPlayerPosLook) {
//                e.setCancelled(true);
//            }
//        }
//        if (e instanceof EventUpdate) {
//
//            if (mc.thePlayer == null || mc.theWorld == null) {
//                return;
//            }
//            mc.thePlayer.noClip = true;
//            mc.thePlayer.onGround = false;
//            if (mc.gameSettings.keyBindJump.isKeyDown()) {
//                mc.thePlayer.motionY = 0.5f / 1.5f;
//            }
//            if (mc.gameSettings.keyBindSneak.isKeyDown()) {
//                mc.thePlayer.motionY = -0.5f / 1.5f;
//            }
//            MovementUtil.setMotion(0.5f);
//            mc.thePlayer.capabilities.isFlying = true;
//
//
//        }
//    }
}
