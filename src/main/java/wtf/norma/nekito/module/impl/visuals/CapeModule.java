package wtf.norma.nekito.module.impl.visuals;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.util.ResourceLocation;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.module.ModuleCategory;
import wtf.norma.nekito.module.ModuleInfo;
import wtf.norma.nekito.module.value.impl.ModeValue;

import java.util.HashMap;
import java.util.Map;

/*
author @intexpression
 */


@ModuleInfo(
        name = "Cape",
        moduleCategory = ModuleCategory.VISUALS
)
public class CapeModule extends Module {

    //@TODO: EnumValue?
    private static final Map<String, ResourceLocation> CAPES = new HashMap<>();

    static {
        CAPES.put("Black", new ResourceLocation("images/cape/black.png"));
        CAPES.put("Pink", new ResourceLocation("images/cape/pink.png"));
        CAPES.put("Blue", new ResourceLocation("images/cape/blue.png"));
    }

    public final ModeValue selectedCape = new ModeValue("Wing type", "Black", "Black", "Pink", "Blue");

    public ResourceLocation getCape() {
        return CAPES.get(selectedCape.get());
    }

    public boolean canRender(AbstractClientPlayer player) {
        return player == mc.thePlayer;
    }
}
