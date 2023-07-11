package wtf.norma.nekito.util.font;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import java.awt.*;

public class FontUtility {
    public static Font getFontFromTTF(ResourceLocation fontLocation, float fontSize, int fontType) {
        Font output = null;
        try {
            output = Font.createFont(fontType, Minecraft.getMinecraft().getResourceManager().getResource(fontLocation).getInputStream());
            output = output.deriveFont(fontSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }
}

