package wtf.norma.nekito.helper.font;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import wtf.norma.nekito.helper.font.renderer.FontRenderer;

import java.awt.*;
import java.io.InputStream;


public final class FontHelper {


    public static FontRenderer SEMI_BOLD_18 = new FontRenderer(loadFont(new ResourceLocation("sfpro.otf"), 18, 0), true, true);
    public static FontRenderer SEMI_BOLD_16 = new FontRenderer(loadFont(new ResourceLocation("sfpro.otf"), 16, 0), true, true);
    public static FontRenderer MONTSERRAT45 = new FontRenderer(loadFont(new ResourceLocation("mntsb.ttf"), 45, 0), true, true);


    private FontHelper() {
    }

    public static Font loadFont(ResourceLocation fontLocation, float fontSize, int fontType) {
        try (InputStream inputStream = Minecraft.getMinecraft().getResourceManager().getResource(fontLocation).getInputStream()) {
            return Font.createFont(fontType, inputStream).deriveFont(fontSize);
        } catch (Exception e) {
            throw new RuntimeException("Can't load font", e);
        }
    }
}
