package wtf.norma.nekito.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

public interface Util {
    Minecraft mc = Minecraft.getMinecraft();
    ScaledResolution sr = new ScaledResolution(mc);
}
