package wtf.norma.nekito.module.impl.visuals;

import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.module.ModuleCategory;
import wtf.norma.nekito.module.ModuleInfo;
import wtf.norma.nekito.module.value.impl.ModeValue;

@ModuleInfo(
        name = "CustomButtons",
        moduleCategory = ModuleCategory.VISUALS
)
public class CustomButtonsModule extends Module {
    public final ModeValue rect = new ModeValue("Rect", "Rounded", "Rounded", "Texture");
    public final ModeValue font = new ModeValue("Font", "Custom", "Custom", "Minecraft");

    @Override
    public void onEnable() {
        toggle();
    }
}
