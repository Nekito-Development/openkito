package wtf.norma.nekito.module.impl.movement;

import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import me.zero.alpine.listener.Subscriber;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.Nekito;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.event.impl.update.EventUpdate;
import wtf.norma.nekito.util.player.MovementUtil;


public class NoClip extends Module implements Subscriber {

    public NoClip() {
        super("No Clip", Category.MOVEMENT, Keyboard.KEY_NONE);
    }

    public static boolean jestcwelem(Entity entity) {
        boolean cwel1;
        cwel1 = mc.theWorld.getBlockState(new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ)).getBlock() != Blocks.web && mc.theWorld.getBlockState(new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ)).getBlock() == Blocks.water && mc.theWorld.getBlockState(new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ)).getBlock() == Blocks.lava && mc.theWorld.getBlockState(new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ)).getBlock() == Blocks.air;
        return cwel1;

    }

    @Override
    public void onEnable() {
        Nekito.EVENT_BUS.subscribe(this);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        Nekito.EVENT_BUS.unsubscribe(this);
        super.onDisable();
    }

    @Subscribe
    private final Listener<EventUpdate> listener = new Listener<>(event ->{
        if (mc.thePlayer != null && !jestcwelem(mc.thePlayer)) {
            if (mc.thePlayer.isCollidedHorizontally)
                mc.thePlayer.isCollidedHorizontally = false;
            if (mc.thePlayer.isCollidedVertically)
                mc.thePlayer.isCollidedVertically = false;
            mc.thePlayer.noClip = true;
            mc.thePlayer.onGround = false;
            MovementUtil.setMotion(MovementUtil.getSpeed());
            if (mc.gameSettings.keyBindJump.isKeyDown() && mc.thePlayer.ticksExisted % 10 == 0) {
                mc.thePlayer.jump();
            }
            if (!(mc.gameSettings.keyBindSneak.pressed && mc.thePlayer.ticksExisted % 10 < 5)) {
                if (mc.thePlayer.motionY < 0) {
                    mc.thePlayer.motionY = 0.00001;
                }
            }
        } else {
            mc.thePlayer.noClip = false;
        }
    });

//    @Override
//    public void onEvent(Event e) {
//        if (e instanceof EventUpdate) {
//            if (mc.thePlayer != null && !jestcwelem(mc.thePlayer)) {
//                if (mc.thePlayer.isCollidedHorizontally)
//                    mc.thePlayer.isCollidedHorizontally = false;
//                if (mc.thePlayer.isCollidedVertically)
//                    mc.thePlayer.isCollidedVertically = false;
//                mc.thePlayer.noClip = true;
//                mc.thePlayer.onGround = false;
//                MovementUtil.setMotion(MovementUtil.getSpeed());
//                if (mc.gameSettings.keyBindJump.isKeyDown() && mc.thePlayer.ticksExisted % 10 == 0) {
//                    mc.thePlayer.jump();
//                }
//                if (!(mc.gameSettings.keyBindSneak.pressed && mc.thePlayer.ticksExisted % 10 < 5)) {
//                    if (mc.thePlayer.motionY < 0) {
//                        mc.thePlayer.motionY = 0.00001;
//                    }
//                }
//            } else {
//                mc.thePlayer.noClip = false;
//            }
//        }
//    }


}
