package wtf.norma.nekito.util.color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;
import wtf.norma.nekito.module.impl.Watermark;

import java.awt.*;

public class ColorUtility {
    public static void setColor(final Color color, final float alpha) {
        final float red = color.getRed() / 255F;
        final float green = color.getGreen() / 255F;
        final float blue = color.getBlue() / 255F;

        GlStateManager.color(red, green, blue, alpha);
    }




    public static Color rainbowEffect(final long offset, final float fade) {
        final float hue = (System.nanoTime() + offset) / 1.0E10f % 1.0f;
        final long color = Long.parseLong(Integer.toHexString(Color.HSBtoRGB(hue, 1.0f, 1.0f)), 16);
        final Color c = new Color((int) color);
        return new Color(c.getRed() / 255.0f * fade, c.getGreen() / 255.0f * fade, c.getBlue() / 255.0f * fade,
                c.getAlpha() / 255.0f);
    }
    public static int rgba(int r, int g, int b, int a) {
        return a << 24 | r << 16 | g << 8 | b;
    }
    public static int rgba(double r, double g, double b, double a) {
        return rgba((int) r, (int) g, (int) b, (int) a);
    }


    public static int getNekito(int speed, int offset) {
        // yeah ik hardcodded

        // DODAC TE NEW
        Minecraft mc = Minecraft.getMinecraft();
        int cwel = ColorUtility.getGradientOffset(new Color(98, 207, 244), new Color(44, 103, 242), (Math.abs(((System.currentTimeMillis()) / 10)) / 100D) + offset / mc.fontRendererObj.FONT_HEIGHT * 9.95).getRGB();
   return  cwel;
    }

    public static int getRainbow(int speed, int offset) {
        float hue = (System.currentTimeMillis() + offset) % speed;
        hue /= speed;
        return Color.getHSBColor(hue, 0.55f, 1f).getRGB();
    }

    public static int getColor(int offset, int i) {
        Minecraft mc = Minecraft.getMinecraft();
        //convertingcolors.com fajno strona
        switch (Watermark.colorMode.getMode()) {
            case "Purple":
                return 0xff9000c4;
            case"Pink":
                return  0xFFFFC0CB;
            case"Nekito":

              return getNekito(4000,offset * 5) ;

            case "Rainbow":
                return getRainbow(4000, offset * 5);

        }
        return 0xFFFFC0CB;
    }
    public static int getClientColor(float counter) {

        return new Color(ColorUtility.getColor(0,0)).getRGB();
    }


    public static Color getGradientOffset(Color color1, Color color2, double offset) {
        if (offset > 1) {
            double left = offset % 1;
            int off = (int) offset;
            offset = off % 2 == 0 ? left : 1 - left;

        }
        double inverse_percent = 1 - offset;
        int redPart = (int) (color1.getRed() * inverse_percent + color2.getRed() * offset);
        int greenPart = (int) (color1.getGreen() * inverse_percent + color2.getGreen() * offset);
        int bluePart = (int) (color1.getBlue() * inverse_percent + color2.getBlue() * offset);
        return new Color(redPart, greenPart, bluePart);
    }
    public static int getColor(int red, int green, int blue, int alpha) {
        int color = 0;
        color |= alpha << 24;
        color |= red << 16;
        color |= green << 8;
        return color |= blue;
    }
    public static int HSBtoRGB(float hue, float saturation, float brightness) {
        int r = 0, g = 0, b = 0;
        if (saturation == 0) {
            r = g = b = (int) (brightness * 255.0f + 0.5f);
        } else {
            float h = (hue - (float) Math.floor(hue)) * 6.0f;
            float f = h - (float) Math.floor(h);
            float p = brightness * (1.0f - saturation);
            float q = brightness * (1.0f - saturation * f);
            float t = brightness * (1.0f - (saturation * (1.0f - f)));
            switch ((int) h) {
                case 0:
                    r = (int) (brightness * 255.0f + 0.5f);
                    g = (int) (t * 255.0f + 0.5f);
                    b = (int) (p * 255.0f + 0.5f);
                    break;
                case 1:
                    r = (int) (q * 255.0f + 0.5f);
                    g = (int) (brightness * 255.0f + 0.5f);
                    b = (int) (p * 255.0f + 0.5f);
                    break;
                case 2:
                    r = (int) (p * 255.0f + 0.5f);
                    g = (int) (brightness * 255.0f + 0.5f);
                    b = (int) (t * 255.0f + 0.5f);
                    break;
                case 3:
                    r = (int) (p * 255.0f + 0.5f);
                    g = (int) (q * 255.0f + 0.5f);
                    b = (int) (brightness * 255.0f + 0.5f);
                    break;
                case 4:
                    r = (int) (t * 255.0f + 0.5f);
                    g = (int) (p * 255.0f + 0.5f);
                    b = (int) (brightness * 255.0f + 0.5f);
                    break;
                case 5:
                    r = (int) (brightness * 255.0f + 0.5f);
                    g = (int) (p * 255.0f + 0.5f);
                    b = (int) (q * 255.0f + 0.5f);
                    break;
            }
        }
        return 0xff000000 | (r << 16) | (g << 8) | (b);
    }
    public static int getColor(int bright) {
        return getColor(bright, bright, bright, 255);
    }
    public static String getHealthStr(EntityLivingBase entity) {
        String str = "";
        int health = (int) entity.getHealth();
        if (health <= entity.getMaxHealth() * 0.25) {
            str = "�4";
        } else if (health <= entity.getMaxHealth() * 0.5) {
            str = "�6";
        } else if (health <= entity.getMaxHealth() * 0.75) {
            str = "�e";
        } else if (health <= entity.getMaxHealth()) {
            str = "�a";
        }
        return str;
    }
    public static void setColor(double red, double green, double blue, double alpha) {
        GL11.glColor4d(red, green, blue, alpha);
    }


    public static void setColor(Color color) {
        if (color == null)
            color = Color.white;
        setColor(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, color.getAlpha() / 255F);
    }

    public static void setColor(int color) {
        setColor(color, (float) (color >> 24 & 255) / 255.0F);
    }

    public static void setColor(int color, float alpha) {
        float r = (float) (color >> 16 & 255) / 255.0F;
        float g = (float) (color >> 8 & 255) / 255.0F;
        float b = (float) (color & 255) / 255.0F;
        GlStateManager.color(r, g, b, alpha);
    }




}
