package wtf.norma.nekito.module.impl.visuals;

import rip.hippo.lwjeb.annotation.Handler;
import wtf.norma.nekito.event.impl.EventUpdate;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.module.ModuleCategory;
import wtf.norma.nekito.module.ModuleInfo;


@ModuleInfo(
        name = "NoWeather",
        moduleCategory = ModuleCategory.VISUALS
)
public class NoWeatherModule extends Module {

    @Handler
    public void onEvent(EventUpdate event) {
        mc.theWorld.setThunderStrength(0);
        mc.theWorld.setRainStrength(0);
    }
}
