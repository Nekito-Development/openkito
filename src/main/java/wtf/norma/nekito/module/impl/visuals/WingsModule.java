package wtf.norma.nekito.module.impl.visuals;

import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.module.ModuleCategory;
import wtf.norma.nekito.module.ModuleInfo;
import wtf.norma.nekito.module.value.impl.ModeValue;

@ModuleInfo(
        name = "Wings",
        moduleCategory = ModuleCategory.VISUALS
)
public class WingsModule extends Module {
    public final ModeValue selectedWing = new ModeValue("Wing type", "Wing1", "Wing1", "Wing2", "Wing3", "Wing4");
}
