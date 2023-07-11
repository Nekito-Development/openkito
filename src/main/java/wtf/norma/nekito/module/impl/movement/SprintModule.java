package wtf.norma.nekito.module.impl.movement;

import rip.hippo.lwjeb.annotation.Handler;
import wtf.norma.nekito.event.impl.EventUpdate;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.module.ModuleCategory;
import wtf.norma.nekito.module.ModuleInfo;

@ModuleInfo(
        name = "Sprint",
        moduleCategory = ModuleCategory.MOVEMENT
)
public class SprintModule extends Module {

    @Handler
    public void onEvent(EventUpdate event) {
        mc.gameSettings.keyBindSprint.pressed = true;
    }

    @Override
    public void onDisable() {
        mc.gameSettings.keyBindSprint.pressed = false;
    }
}
