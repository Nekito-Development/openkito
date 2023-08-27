package wtf.norma.nekito.module.impl;

import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import me.zero.alpine.listener.Subscriber;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.Nekito;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.newevent.impl.movement.EventPreMotion;
import wtf.norma.nekito.util.Util;


public class AirJump extends Module implements Subscriber {

    public AirJump() {
        super("AirJump", Category.MOVEMENT, Keyboard.KEY_NONE);

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
    private final Listener<EventPreMotion> listener = new Listener<>(event -> {
        System.out.println("DEBUG");
            // IDK HOW BUT IT BYPASSES VULCAN AND VERUS AND OTHER SHIT LUB
            if (mc.thePlayer.onGround) {
                mc.thePlayer.jump();
            }
            if ((!Util.mc.theWorld.getCollidingBoundingBoxes(mc.thePlayer, mc.thePlayer.getEntityBoundingBox().offset(0, -1, 0).expand(0.5, 0, 0.5)).isEmpty() && mc.thePlayer.ticksExisted % 2 == 0)) {
                mc.gameSettings.keyBindJump.pressed = true;
                mc.thePlayer.jumpTicks = 0;
                mc.thePlayer.fallDistance = 0;
                event.setOnGround(true);
                mc.thePlayer.onGround = true;
            }
        });

//    @Override
//    public void onEvent(Event e) {
//        if (e instanceof EventPreMotion) {
//            // IDK HOW BUT IT BYPASSES VULCAN AND VERUS AND OTHER SHIT LUB
//            if (mc.thePlayer.onGround) {
//                mc.thePlayer.jump();
//            }
//            if ((!Util.mc.theWorld.getCollidingBoundingBoxes(mc.thePlayer, mc.thePlayer.getEntityBoundingBox().offset(0, -1, 0).expand(0.5, 0, 0.5)).isEmpty() && mc.thePlayer.ticksExisted % 2 == 0)) {
//                    mc.gameSettings.keyBindJump.pressed = true;
//                    mc.thePlayer.jumpTicks = 0;
//                    mc.thePlayer.fallDistance = 0;
//                    ((EventPreMotion) e).setOnGround(true);
//                    mc.thePlayer.onGround = true;
//            }
//        }
//    }
}
