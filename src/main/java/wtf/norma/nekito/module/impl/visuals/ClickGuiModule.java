package wtf.norma.nekito.module.impl.visuals;

import wtf.norma.nekito.Nekito;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.module.ModuleCategory;
import wtf.norma.nekito.module.ModuleInfo;

@ModuleInfo(
        name = "ClickGui",
        moduleCategory = ModuleCategory.VISUALS,
        key = 54 //not cute and funny
)
public class ClickGuiModule extends Module {

    @Override
    public void onEnable() {
        mc.displayGuiScreen(Nekito.INSTANCE.getClickGui());
        toggle();
    }
}
