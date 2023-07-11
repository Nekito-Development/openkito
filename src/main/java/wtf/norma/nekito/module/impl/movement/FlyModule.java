package wtf.norma.nekito.module.impl.movement;

import rip.hippo.lwjeb.annotation.Handler;
import wtf.norma.nekito.event.EventState;
import wtf.norma.nekito.event.impl.MotionUpdateEvent;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.module.ModuleCategory;
import wtf.norma.nekito.module.ModuleInfo;
import wtf.norma.nekito.module.value.impl.BooleanValue;
import wtf.norma.nekito.module.value.impl.ModeValue;
import wtf.norma.nekito.module.value.impl.RangeNumberValue;

@ModuleInfo(
        name = "Fly",
        moduleCategory = ModuleCategory.MOVEMENT,
        key = 33 //not cute and funny
)
public class FlyModule extends Module {

    public final ModeValue mode = new ModeValue("Mode", "Capabilities", "Capabilities", "Motion");
    public final RangeNumberValue speed = new RangeNumberValue("Speed", 0.05f, 0f, 10f, 0.1f);
    public final BooleanValue stopOnDisable = new BooleanValue("Stop on disable", true);

    @Override
    public void onDisable() {
        mc.thePlayer.capabilities.isFlying = false;
        mc.thePlayer.capabilities.setFlySpeed(0.05f);

        if (stopOnDisable.get())
            mc.thePlayer.strafe(0);
    }

    @Handler
    public void onMotionEvent(MotionUpdateEvent event) {
        if (event.getState() != EventState.PRE)
            return;

        switch (mode.get()) {
            case "Capabilities":
                mc.thePlayer.capabilities.isFlying = true;
                mc.thePlayer.capabilities.setFlySpeed(speed.get());
                break;
            case "Motion":
                mc.thePlayer.motionX = 0;
                mc.thePlayer.motionY = 0;
                mc.thePlayer.motionZ = 0;

                if (mc.gameSettings.keyBindSneak.isKeyDown())
                    mc.thePlayer.motionY -= speed.get() / 2;
                if (mc.gameSettings.keyBindJump.isKeyDown())
                    mc.thePlayer.motionY += speed.get() / 2;

                mc.thePlayer.strafe(speed.get());
                break;
        }
    }
}
