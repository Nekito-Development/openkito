package wtf.norma.nekito.helper.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import wtf.norma.nekito.helper.shader.ShaderHelper;

import javax.vecmath.Vector3d;
import java.awt.*;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;

public final class RenderHelper {

    private static final Minecraft mc = Minecraft.getMinecraft();

    private static final ShaderHelper ROUNDED_RECT_GRADIENT = new ShaderHelper("roundedRectGradient");
    private static final ShaderHelper ROUNDED_RECT = new ShaderHelper("roundedRect");
    private static final ShaderHelper ROUND_RECT_OUTLINE = new ShaderHelper("roundRectOutline");
    private static final ShaderHelper ROUNDED_TEXTURED_SHADER = new ShaderHelper("roundedTexturedShader");
    private static final Frustum FRUSTUM = new Frustum();

    private RenderHelper() {
    }

    public static void scissorRect(float x, float y, float width, double height) {
        ScaledResolution sr = new ScaledResolution(mc);
        int factor = sr.getScaleFactor();
        GL11.glScissor((int) (x * (float) factor), (int) (((float) sr.getScaledHeight() - height) * (float) factor), (int) ((width - x) * (float) factor), (int) ((height - y) * (float) factor));
    }

    public static void sizeAnimation(double width, double height, double animation) {
        GL11.glTranslated(width / 2, height / 2, 0);
        GL11.glScaled(animation, animation, 1);
        GL11.glTranslated(-width / 2, -height / 2, 0);
    }


    public static void drawBorderedRect(double x, double y, double x2, double y2, int borderedColor, int color) {
        drawRect(x + 1, y + 1, x2 - 1, y2 - 1, color);
        drawRect(x, y + 1, x2, y, borderedColor);
        drawRect(x2 - 1, y, x2, y2, borderedColor);
        drawRect(x, y2, x2, y2 - 1, borderedColor);
        drawRect(x, y, x + 1, y2, borderedColor);
    }

    public static void drawTriangle() {
        boolean needBlend = !GL11.glIsEnabled(GL11.GL_BLEND);
        if (needBlend)
            GL11.glEnable(GL11.GL_BLEND);
        int alpha = 255;
        int red_1 = 255;
        int green_1 = 255;
        int blue_1 = 255;
        int red_2 = Math.max(red_1 - 40, 0);
        int green_2 = Math.max(green_1 - 40, 0);
        int blue_2 = Math.max(blue_1 - 40, 0);
        float width = 6, height = 12;
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_POLYGON_SMOOTH);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glBegin(GL11.GL_POLYGON);
        GL11.glColor4f(red_1 / 255f, green_1 / 255f, blue_1 / 255f, alpha / 255f);
        glVertex2d(0, 0 - height);
        glVertex2d(0 - width, 0);
        glVertex2d(0, -3);
        GL11.glEnd();
        GL11.glBegin(GL11.GL_POLYGON);
        GL11.glColor4f(red_2 / 255f, green_2 / 255f, blue_2 / 255f, alpha / 255f);
        glVertex2d(0, 0 - height);
        glVertex2d(0, -3);
        glVertex2d(0 + width, 0);
        GL11.glEnd();
        GL11.glShadeModel(GL11.GL_FLAT);
        GL11.glDisable(GL11.GL_POLYGON_SMOOTH);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        if (needBlend)
            GL11.glDisable(GL11.GL_BLEND);
    }

    //    draw triangle with rotation
    public static void drawTriangle(double x, double y, double width, double height, double rotation) {
        boolean needBlend = !GL11.glIsEnabled(GL11.GL_BLEND);
        if (needBlend)
            GL11.glEnable(GL11.GL_BLEND);
        int alpha = 255;
        int red_1 = 255;
        int green_1 = 255;
        int blue_1 = 255;
        int red_2 = Math.max(red_1 - 40, 0);
        int green_2 = Math.max(green_1 - 40, 0);
        int blue_2 = Math.max(blue_1 - 40, 0);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_POLYGON_SMOOTH);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glBegin(GL11.GL_POLYGON);
        GL11.glColor4f(red_1 / 255f, green_1 / 255f, blue_1 / 255f, alpha / 255f);
        glVertex2d(x, y - height);
        glVertex2d(x - width, y);
        glVertex2d(x, y - 3);
        GL11.glEnd();
        GL11.glBegin(GL11.GL_POLYGON);
        GL11.glColor4f(red_2 / 255f, green_2 / 255f, blue_2 / 255f, alpha / 255f);
        glVertex2d(x, y - height);
        glVertex2d(x, y - 3);
        glVertex2d(x + width, y);
        GL11.glEnd();
        GL11.glShadeModel(GL11.GL_FLAT);
        GL11.glDisable(GL11.GL_POLYGON_SMOOTH);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        if (needBlend)
            GL11.glDisable(GL11.GL_BLEND);
    }

    public static void drawTriangle(float x, float y, float vector, int color) {
        GL11.glPushMatrix();
        GL11.glTranslated(x, y, 0);
        GL11.glRotatef(180 + vector, 0F, 0F, 1.0F);
        float alpha = (float) (color >> 24 & 255) / 255.0F;
        float red = (float) (color >> 16 & 255) / 255.0F;
        float green = (float) (color >> 8 & 255) / 255.0F;
        float blue = (float) (color & 255) / 255.0F;
        GL11.glColor4f(red, green, blue, alpha);
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL11.GL_POLYGON_SMOOTH);
        GL11.glHint(GL11.GL_POLYGON_SMOOTH_HINT, GL11.GL_NICEST);
        GL11.glBlendFunc(GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        float size = 0.9f;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer buffer = tessellator.getBuffer();
        buffer.begin(GL11.GL_TRIANGLES, DefaultVertexFormats.POSITION);
        buffer.pos(0, 3.1 * size, 0).endVertex();
        buffer.pos(2 * size, -1 * size, 0).endVertex();
        buffer.pos(-2.3 * size, -1 * size, 0).endVertex();
        tessellator.draw();
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_POLYGON_SMOOTH);

        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GL11.glRotatef(-180 - vector, 0F, 0F, 1.0F);
        GL11.glTranslated(-x, -y, 0);
        GlStateManager.resetColor();
        GL11.glPopMatrix();
    }


    public static javax.vecmath.Vector3d vectorTo2D(int scaleFactor, double x, double y, double z) {
        float xPos = (float) x;
        float yPos = (float) y;
        float zPos = (float) z;
        IntBuffer viewport = GLAllocation.createDirectIntBuffer(16);
        FloatBuffer modelview = GLAllocation.createDirectFloatBuffer(16);
        FloatBuffer projection = GLAllocation.createDirectFloatBuffer(16);
        FloatBuffer vector = GLAllocation.createDirectFloatBuffer(4);
        GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX);
        GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX);
        GL11.glGetInteger(GL11.GL_VIEWPORT);
        if (GLU.gluProject(xPos, yPos, zPos, modelview, projection, viewport, vector))
            return new Vector3d((vector.get(0) / scaleFactor), ((Display.getHeight() - vector.get(1)) / scaleFactor),
                    vector.get(2));
        return null;
    }

    public static void drawColoredCircle(double x, double y, double radius, float brightness) {
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glShadeModel(7425);

        GlStateManager.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        GL11.glBegin(GL_LINE_STRIP);

        for (int i = 0; i < 360; i++) {
            ColorHelper.setColor(Color.HSBtoRGB(i / 360f, 0, brightness));
            glVertex2d(x, y);
            ColorHelper.setColor(Color.HSBtoRGB(i / 360f, 1, brightness));
            glVertex2d(
                    x + Math.sin(Math.toRadians(i)) * radius,
                    y + Math.cos(Math.toRadians(i)) * radius
            );
        }
        GL11.glEnd();
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glShadeModel(7424);

        GL11.glPopMatrix();

    }

    public static void drawVGradientRect(final float left, final float top, final float right, final float bottom, final int startColor, final int endColor) {
        float f = (float) (startColor >> 24 & 0xFF) / 255.0f;
        float f1 = (float) (startColor >> 16 & 0xFF) / 255.0f;
        float f2 = (float) (startColor >> 8 & 0xFF) / 255.0f;
        float f3 = (float) (startColor & 0xFF) / 255.0f;
        float f4 = (float) (endColor >> 24 & 0xFF) / 255.0f;
        float f5 = (float) (endColor >> 16 & 0xFF) / 255.0f;
        float f6 = (float) (endColor >> 8 & 0xFF) / 255.0f;
        float f7 = (float) (endColor & 0xFF) / 255.0f;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.shadeModel(7425);
        GL11.glPushMatrix();
        GL11.glBegin(7);
        GL11.glColor4f(f1, f2, f3, f);
        glVertex2d(left, top);
        glVertex2d(right, top);
        GL11.glColor4f(f5, f6, f7, f4);
        glVertex2d(right, bottom);
        glVertex2d(left, bottom);


        GL11.glEnd();
        GL11.glPopMatrix();
        GlStateManager.resetColor();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();


    }


    private static boolean isInViewFrustum(AxisAlignedBB bb) {
        Entity current = mc.getRenderViewEntity();
        if (current != null) {
            FRUSTUM.setPosition(current.posX, current.posY, current.posZ);
        }
        return FRUSTUM.isBoundingBoxInFrustum(bb);
    }


    public static void drawFace(float d, float y, float u, float v, int uWidth, int vHeight, int width, int height, float tileWidth, float tileHeight, AbstractClientPlayer target) {
        try {

            ResourceLocation skin = target.getLocationSkin();
            Minecraft.getMinecraft().getTextureManager().bindTexture(skin);
            GL11.glEnable(GL11.GL_BLEND);
            float hurtPercent = getHurtPercent(target);

            GL11.glColor4f(1, 1 - hurtPercent, 1 - hurtPercent, 1);
            Gui.drawScaledCustomSizeModalRect(d, y, u, v, uWidth, vHeight, width, height, tileWidth, tileHeight);
            GL11.glDisable(GL11.GL_BLEND);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static float getRenderHurtTime(EntityLivingBase hurt) {
        return (float) hurt.hurtTime - (hurt.hurtTime != 0 ? mc.timer.renderPartialTicks : 0.0f);
    }

    public static float getHurtPercent(EntityLivingBase hurt) {
        return getRenderHurtTime(hurt) / (float) 10;
    }

    public static void drawRect(double left, double top, double right, double bottom, int color) {

        right += left;
        bottom += top;
        float f3 = (float) (color >> 24 & 255) / 255.0F;
        float f = (float) (color >> 16 & 255) / 255.0F;
        float f1 = (float) (color >> 8 & 255) / 255.0F;
        float f2 = (float) (color & 255) / 255.0F;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer bufferbuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,
                GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE,
                GlStateManager.DestFactor.ZERO);
        GlStateManager.color(f, f1, f2, f3);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferbuilder.pos(left, bottom, 0.0D).endVertex();
        bufferbuilder.pos(right, bottom, 0.0D).endVertex();
        bufferbuilder.pos(right, top, 0.0D).endVertex();
        bufferbuilder.pos(left, top, 0.0D).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void drawRectNotWH(double left, double top, double right, double bottom, int color) {
        if (left < right) {
            double i = left;
            left = right;
            right = i;
        }

        if (top < bottom) {
            double j = top;
            top = bottom;
            bottom = j;
        }
        float f3 = (float) (color >> 24 & 255) / 255.0F;
        float f = (float) (color >> 16 & 255) / 255.0F;
        float f1 = (float) (color >> 8 & 255) / 255.0F;
        float f2 = (float) (color & 255) / 255.0F;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer bufferbuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,
                GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE,
                GlStateManager.DestFactor.ZERO);
        GlStateManager.color(f, f1, f2, f3);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferbuilder.pos(left, bottom, 0.0D).endVertex();
        bufferbuilder.pos(right, bottom, 0.0D).endVertex();
        bufferbuilder.pos(right, top, 0.0D).endVertex();
        bufferbuilder.pos(left, top, 0.0D).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void drawImage(final double x, final double y, final double width, final double height, final ResourceLocation image) {
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glDepthMask(false);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        mc.getTextureManager().bindTexture(image);
        Gui.drawModalRectWithCustomSizedTexture(x, y, 0.0f, 0.0f, width, height, (float) width, (float) height);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
    }


    public static void drawImage(ResourceLocation resourceLocation, float x, float y, float width, float height, Color color) {
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);


        ColorHelper.setColor(color.getRGB());
        Minecraft.getMinecraft().getTextureManager().bindTexture(resourceLocation);
        Gui.drawModalRectWithCustomSizedTexture(x, y, 0, 0, width, height, width, height);

        GlStateManager.resetColor();
        GL11.glPopMatrix();

    }

    public static void drawCircle(float x, float y, float start, float end, float radius, float width, boolean filled, Color color) {
        float sin;
        float cos;
        float i;
        GlStateManager.color(0, 0, 0, 0);

        float endOffset;
        if (start > end) {
            endOffset = end;
            end = start;
            start = endOffset;
        }

        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        ColorHelper.setColor(color.getRGB());
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glLineWidth(width);
        GL11.glBegin(GL_LINE_STRIP);
        for (i = end; i >= start; i -= 4) {
            cos = (float) (Math.cos(i * Math.PI / 180) * radius * 1);
            sin = (float) (Math.sin(i * Math.PI / 180) * radius * 1);
            GL11.glVertex2f(x + cos, y + sin);
        }
        GL11.glEnd();
        GL11.glDisable(GL11.GL_LINE_SMOOTH);

        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glBegin(filled ? GL11.GL_TRIANGLE_FAN : GL_LINE_STRIP);
        for (i = end; i >= start; i -= 4) {
            cos = (float) Math.cos(i * Math.PI / 180) * radius;
            sin = (float) Math.sin(i * Math.PI / 180) * radius;
            GL11.glVertex2f(x + cos, y + sin);
        }
        GL11.glEnd();
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void drawGradientHorizontal(float x, float y, float width, float height, float radius, Color left, Color right) {
        drawGradientRound(x, y, width, height, radius, left, left, right, right);
    }


    public static void drawRoundCircle(float x, float y, float radius, Color color) {
        drawRound(x - (radius / 2), y - (radius / 2), radius, radius, (radius / 2) - 0.5f, color);
    }

    public static void drawRoundCircleC(float x, float y, float radius, Color color) {
        drawRoundOutline(x - (radius / 2), y - (radius / 2), radius, radius, (radius / 2) - 0.5f, 0.5f, new Color(0, 0, 0, 0), color);
    }

    public static void drawRoundCircleC2(float x, float y, float radius, Color color) {
        drawRoundOutline(x - (radius / 2), y - (radius / 2), radius, radius, (radius / 2) - 0.5f, 0.1f, new Color(0, 0, 0, 0), color);
    }

    public static void drawRound(float x, float y, float width, float height, float radius, Color color) {
        GlStateManager.resetColor();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        ROUNDED_RECT.init();
        ShaderHelper.setupRoundedRectUniforms(x, y, width, height, radius, ROUNDED_RECT);
        ROUNDED_RECT.setUniformf("blur", 0);
        ROUNDED_RECT.setUniformf("color", color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, color.getAlpha() / 255f);
        ShaderHelper.drawQuads(x - 1, y - 1, width + 2, height + 2);
        ROUNDED_RECT.unload();
        GlStateManager.disableBlend();
        GlStateManager.resetColor();
    }

    public static void drawGradientRound(float x, float y, float width, float height, float radius, Color bottomLeft, Color topLeft, Color bottomRight, Color topRight) {
        GlStateManager.resetColor();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        ROUNDED_RECT_GRADIENT.init();
        ShaderHelper.setupRoundedRectUniforms(x, y, width, height, radius, ROUNDED_RECT_GRADIENT);
        // Bottom Left
        ROUNDED_RECT_GRADIENT.setUniformf("color1", bottomLeft.getRed() / 255f, bottomLeft.getGreen() / 255f, bottomLeft.getBlue() / 255f, bottomLeft.getAlpha() / 255f);
        //Top left
        ROUNDED_RECT_GRADIENT.setUniformf("color2", topLeft.getRed() / 255f, topLeft.getGreen() / 255f, topLeft.getBlue() / 255f, topLeft.getAlpha() / 255f);
        //Bottom Right
        ROUNDED_RECT_GRADIENT.setUniformf("color3", bottomRight.getRed() / 255f, bottomRight.getGreen() / 255f, bottomRight.getBlue() / 255f, bottomRight.getAlpha() / 255f);
        //Top Right
        ROUNDED_RECT_GRADIENT.setUniformf("color4", topRight.getRed() / 255f, topRight.getGreen() / 255f, topRight.getBlue() / 255f, topRight.getAlpha() / 255f);
        ShaderHelper.drawQuads(x - 1, y - 1, width + 2, height + 2);
        ROUNDED_RECT_GRADIENT.unload();
        GlStateManager.disableBlend();
    }


    public static void drawRoundOutline(float x, float y, float width, float height, float radius, float outlineThickness, Color color, Color outlineColor) {
        GlStateManager.resetColor();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        ROUND_RECT_OUTLINE.init();

        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        ShaderHelper.setupRoundedRectUniforms(x, y, width, height, radius, ROUND_RECT_OUTLINE);
        ROUND_RECT_OUTLINE.setUniformf("outlineThickness", outlineThickness * sr.getScaleFactor());
        ROUND_RECT_OUTLINE.setUniformf("color", color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, color.getAlpha() / 255f);
        ROUND_RECT_OUTLINE.setUniformf("outlineColor", outlineColor.getRed() / 255f, outlineColor.getGreen() / 255f, outlineColor.getBlue() / 255f, outlineColor.getAlpha() / 255f);


        ShaderHelper.drawQuads(x - (2 + outlineThickness), y - (2 + outlineThickness), width + (4 + outlineThickness * 2), height + (4 + outlineThickness * 2));
        ROUND_RECT_OUTLINE.unload();
        GlStateManager.disableBlend();
    }

//

    public static void horizontalGradient(double x1, double y1, double x2, double y2, int startColor, int endColor) {
        x2 += x1;
        y2 += y1;
        float f = (float) (startColor >> 24 & 255) / 255.0F;
        float f1 = (float) (startColor >> 16 & 255) / 255.0F;
        float f2 = (float) (startColor >> 8 & 255) / 255.0F;
        float f3 = (float) (startColor & 255) / 255.0F;
        float f4 = (float) (endColor >> 24 & 255) / 255.0F;
        float f5 = (float) (endColor >> 16 & 255) / 255.0F;
        float f6 = (float) (endColor >> 8 & 255) / 255.0F;
        float f7 = (float) (endColor & 255) / 255.0F;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,
                GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE,
                GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos(x1, y1, 0).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos(x1, y2, 0).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos(x2, y2, 0).color(f5, f6, f7, f4).endVertex();
        bufferbuilder.pos(x2, y1, 0).color(f5, f6, f7, f4).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }

//    vertical

    public static void verticalGradient(double x1, double y1, double x2, double y2, int startColor, int endColor) {
        x2 += x1;
        y2 += y1;
        float f = (float) (startColor >> 24 & 255) / 255.0F;
        float f1 = (float) (startColor >> 16 & 255) / 255.0F;
        float f2 = (float) (startColor >> 8 & 255) / 255.0F;
        float f3 = (float) (startColor & 255) / 255.0F;
        float f4 = (float) (endColor >> 24 & 255) / 255.0F;
        float f5 = (float) (endColor >> 16 & 255) / 255.0F;
        float f6 = (float) (endColor >> 8 & 255) / 255.0F;
        float f7 = (float) (endColor & 255) / 255.0F;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,
                GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE,
                GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos(x1, y1, 0).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos(x1, y2, 0).color(f5, f6, f7, f4).endVertex();
        bufferbuilder.pos(x2, y2, 0).color(f5, f6, f7, f4).endVertex();
        bufferbuilder.pos(x2, y1, 0).color(f1, f2, f3, f).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }

}



