package wtf.norma.nekito.util.render;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import wtf.norma.nekito.util.filter.filter.image.GaussianFilter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class RenderUtil {

    private final Minecraft mc;
    private final HashMap<Integer, Integer> shadowCache;
    private final ResourceLocation blurLocation;
    private ShaderGroup shaderGroup;
    private Framebuffer framebuffer;
    private int lastFactorBlur;
    private int lastWidthBlur;
    private int lastHeightBlur;
    private int lastFactorBuffer;
    private int lastWidthBuffer;
    private int lastHeightBuffer;
    private ShaderGroup blurShaderGroupBuffer;
    private Framebuffer blurBuffer;
    private final boolean oldBlur;

    public static RenderUtil Instance;

    public RenderUtil(final boolean oldBlur) {
        this.mc = Minecraft.getMinecraft();
        this.shadowCache = new HashMap<Integer, Integer>();
        this.blurLocation = new ResourceLocation("shaders/post/blur.json");
        this.oldBlur = oldBlur;
        (this.blurBuffer = new Framebuffer(this.mc.displayWidth, this.mc.displayHeight, false)).setFramebufferColor(0.0f, 0.0f, 0.0f, 0.0f);
        final ScaledResolution scaledResolution = new ScaledResolution(this.mc);
        final int scaleFactor = scaledResolution.getScaleFactor();
        final int width = scaledResolution.getScaledWidth();
        final int height = scaledResolution.getScaledHeight();
        final int n = scaleFactor;
        this.lastFactorBuffer = n;
        this.lastFactorBlur = n;
        final int n2 = width;
        this.lastWidthBuffer = n2;
        this.lastWidthBlur = n2;
        final int n3 = height;
        this.lastHeightBuffer = n3;
        this.lastHeightBlur = n3;
    }

    public void init() {
        try {
            (this.shaderGroup = new ShaderGroup(this.mc.getTextureManager(), this.mc.getResourceManager(), this.mc.getFramebuffer(), this.blurLocation)).createBindFramebuffers(this.mc.displayWidth, this.mc.displayHeight);
            this.framebuffer = this.shaderGroup.mainFramebuffer;
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    private void setPostValues() {
        this.shaderGroup.listShaders.get(0).getShaderManager().getShaderUniform("BlurDir").set(1.0f, 0.0f);
        this.shaderGroup.loadShaderGroup(this.mc.timer.renderPartialTicks);
        this.shaderGroup.listShaders.get(0).getShaderManager().getShaderUniform("BlurDir").set(0.0f, 1.0f);
        this.shaderGroup.loadShaderGroup(this.mc.timer.renderPartialTicks);
    }

    private void setPreValues(final float strength) {
        this.shaderGroup.listShaders.get(1).getShaderManager().getShaderUniform("Radius").set(0.0f);
        this.shaderGroup.listShaders.get(1).getShaderManager().getShaderUniform("BlurDir").set(0.0f, 0.0f);
        this.shaderGroup.listShaders.get(0).getShaderManager().getShaderUniform("Radius").set(strength);
    }

    public void update(final float partialTicks) {
        final ScaledResolution scaledResolution = new ScaledResolution(this.mc);
        final int scaleFactor = scaledResolution.getScaleFactor();
        final int width = scaledResolution.getScaledWidth();
        final int height = scaledResolution.getScaledHeight();
        if (this.sizeHasChangedBuffer(scaleFactor, width, height)) {
            (this.blurBuffer = new Framebuffer(this.mc.displayWidth, this.mc.displayHeight, false)).setFramebufferColor(0.0f, 0.0f, 0.0f, 0.0f);
            this.loadShader(this.blurLocation, this.blurBuffer);
        }
        this.lastFactorBuffer = scaleFactor;
        this.lastWidthBuffer = width;
        this.lastHeightBuffer = height;
        if (this.blurShaderGroupBuffer == null) {
            this.loadShader(this.blurLocation, this.blurBuffer);
        }
        GlStateManager.enableDepth();
        this.mc.getFramebuffer().unbindFramebuffer();
        this.blurBuffer.bindFramebuffer(true);
        this.mc.getFramebuffer().framebufferRenderExt(this.mc.displayWidth, this.mc.displayHeight, true);
        if (OpenGlHelper.shadersSupported && this.blurShaderGroupBuffer != null) {
            GlStateManager.matrixMode(5890);
            GlStateManager.pushMatrix();
            GlStateManager.loadIdentity();
            this.blurShaderGroupBuffer.loadShaderGroup(partialTicks);
            GlStateManager.popMatrix();
        }
        this.blurBuffer.unbindFramebuffer();
        this.mc.getFramebuffer().bindFramebuffer(true);
        this.mc.entityRenderer.setupOverlayRendering();
    }

    private void loadShader(final ResourceLocation resourceLocationIn, final Framebuffer framebuffer) {
        if (OpenGlHelper.isFramebufferEnabled()) {
            try {
                (this.blurShaderGroupBuffer = new ShaderGroup(this.mc.getTextureManager(), this.mc.getResourceManager(), framebuffer, resourceLocationIn)).createBindFramebuffers(this.mc.displayWidth, this.mc.displayHeight);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void blur(final double x, final double y, final double areaWidth, final double areaHeight, final boolean setupOverlayRendering, final boolean bloom, final boolean reverseBloom, final int bloomRadius, final int bloomAlpha) {
        this.blur(x, y, areaWidth, areaHeight, setupOverlayRendering, bloom, reverseBloom, bloomRadius, bloomAlpha, false);
    }


    public static void dbb(final AxisAlignedBB abb, final float r, final float g, final float b) {
        final float a = 0.25f;
        final Tessellator ts = Tessellator.getInstance();
        final WorldRenderer vb = ts.getWorldRenderer();
        vb.begin(7, DefaultVertexFormats.POSITION_COLOR);
        vb.pos(abb.minX, abb.minY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.maxY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.minY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.maxY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.minY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.maxY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.minY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.maxY, abb.maxZ).color(r, g, b, a).endVertex();
        ts.draw();
        vb.begin(7, DefaultVertexFormats.POSITION_COLOR);
        vb.pos(abb.maxX, abb.maxY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.minY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.maxY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.minY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.maxY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.minY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.maxY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.minY, abb.maxZ).color(r, g, b, a).endVertex();
        ts.draw();
        vb.begin(7, DefaultVertexFormats.POSITION_COLOR);
        vb.pos(abb.minX, abb.maxY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.maxY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.maxY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.maxY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.maxY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.maxY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.maxY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.maxY, abb.minZ).color(r, g, b, a).endVertex();
        ts.draw();
        vb.begin(7, DefaultVertexFormats.POSITION_COLOR);
        vb.pos(abb.minX, abb.minY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.minY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.minY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.minY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.minY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.minY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.minY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.minY, abb.minZ).color(r, g, b, a).endVertex();
        ts.draw();
        vb.begin(7, DefaultVertexFormats.POSITION_COLOR);
        vb.pos(abb.minX, abb.minY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.maxY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.minY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.maxY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.minY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.maxY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.minY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.maxY, abb.minZ).color(r, g, b, a).endVertex();
        ts.draw();
        vb.begin(7, DefaultVertexFormats.POSITION_COLOR);
        vb.pos(abb.minX, abb.maxY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.minY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.maxY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.minY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.maxY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.minY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.maxY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.minY, abb.maxZ).color(r, g, b, a).endVertex();
        ts.draw();
    }

    public void blur(final double x, final double y, final double areaWidth, final double areaHeight, final boolean setupOverlayRendering, final boolean bloom, final boolean reverseBloom, final int bloomRadius, final int bloomAlpha, final boolean ignoreModule) {
        GlStateManager.enableBlend();
        if (bloom && !reverseBloom) {
            this.bloom((int)x - 4, (int)y - 4, (int)areaWidth + 8, (int)areaHeight + 8, bloomRadius, bloomAlpha);
        }
        if (this.mc.theWorld != null && this.mc.thePlayer != null && !this.oldBlur) {
            GL11.glEnable(3089);
            this.scissor(x, y, areaWidth, areaHeight);
            this.scissor(x, y, areaWidth, areaHeight);
            this.blur(10.0f, setupOverlayRendering, true);
            GL11.glDisable(3089);
        }
        else {
            final ScaledResolution scaledResolution = new ScaledResolution(this.mc);
            final int scaleFactor = scaledResolution.getScaleFactor();
            final int width = scaledResolution.getScaledWidth();
            final int height = scaledResolution.getScaledHeight();
            if (this.sizeHasChangedBlur(scaleFactor, width, height) || this.framebuffer == null || this.shaderGroup == null) {
                this.init();
            }
            this.lastFactorBlur = scaleFactor;
            this.lastWidthBlur = width;
            this.lastHeightBlur = height;
            if (bloom && !reverseBloom) {
                this.bloom((int)x - 4, (int)y - 4, (int)areaWidth + 8, (int)areaHeight + 8, bloomRadius, bloomAlpha);
            }
            GlStateManager.disableDepth();
            GL11.glEnable(3089);
            this.scissor(x, y, areaWidth, areaHeight);
            this.setPreValues(10.0f);
            this.framebuffer.bindFramebuffer(true);
            this.setPostValues();
            this.mc.getFramebuffer().bindFramebuffer(false);
            GL11.glDisable(3089);
            GlStateManager.enableDepth();
        }
        if (bloom && reverseBloom) {
            this.bloom((int)x - 4, (int)y - 4, (int)areaWidth + 8, (int)areaHeight + 8, bloomRadius, bloomAlpha);
        }
    }


    public void blur(final float blurStrength, final boolean setupOverlayRendering) {
        this.blur(blurStrength, setupOverlayRendering, false);
    }

    public void blurOld(final float blurStrength) {
        final ScaledResolution scaledResolution = new ScaledResolution(this.mc);
        final int scaleFactor = scaledResolution.getScaleFactor();
        final int width = scaledResolution.getScaledWidth();
        final int height = scaledResolution.getScaledHeight();
        if (this.sizeHasChangedBlur(scaleFactor, width, height) || this.framebuffer == null || this.shaderGroup == null) {
            this.init();
        }
        this.lastFactorBlur = scaleFactor;
        this.lastWidthBlur = width;
        this.lastHeightBlur = height;
        GlStateManager.disableDepth();
        this.setPreValues(blurStrength);
        this.framebuffer.bindFramebuffer(true);
        this.setPostValues();
        this.mc.getFramebuffer().bindFramebuffer(true);
        GlStateManager.enableDepth();
    }

    public void blur(final float blurStrength, final boolean setupOverlayRendering, final boolean ignoreModule) {
        if (this.mc.theWorld != null && this.mc.thePlayer != null && !this.oldBlur) {
            GL11.glPushMatrix();
            GL11.glPushMatrix();
            this.blurShaderGroupBuffer.listShaders.get(0).getShaderManager().getShaderUniform("Radius").set(blurStrength);
            this.blurShaderGroupBuffer.listShaders.get(1).getShaderManager().getShaderUniform("Radius").set(blurStrength);
            this.blurBuffer.framebufferRender(this.mc.displayWidth, this.mc.displayHeight);
            GL11.glPopMatrix();
            if (setupOverlayRendering) {
                this.mc.entityRenderer.setupOverlayRendering();
            }
            GlStateManager.enableDepth();
            GlStateManager.enableAlpha();
            GL11.glPopMatrix();
        }
        else {
            final ScaledResolution scaledResolution = new ScaledResolution(this.mc);
            final int scaleFactor = scaledResolution.getScaleFactor();
            final int width = scaledResolution.getScaledWidth();
            final int height = scaledResolution.getScaledHeight();
            if (this.sizeHasChangedBlur(scaleFactor, width, height) || this.framebuffer == null || this.shaderGroup == null) {
                this.init();
            }
            this.lastFactorBlur = scaleFactor;
            this.lastWidthBlur = width;
            this.lastHeightBlur = height;
            GlStateManager.disableDepth();
            this.setPreValues(blurStrength);
            this.framebuffer.bindFramebuffer(true);
            this.setPostValues();
            this.mc.getFramebuffer().bindFramebuffer(true);
            GlStateManager.enableDepth();
        }
    }

    public void blur(final double x, final double y, final double areaWidth, final double areaHeight, final boolean setupOverlayRendering, final boolean ignoreModule) {
        this.blur(x, y, areaWidth, areaHeight, setupOverlayRendering, false, false, 0, 0, ignoreModule);
    }

    public void blur(final double x, final double y, final double areaWidth, final double areaHeight, final boolean setupOverlayRendering) {
        this.blur(x, y, areaWidth, areaHeight, setupOverlayRendering, false, false, 0, 0);
        Gui.drawRect(0,0,0,0,0);

    }

    public void blurRounded(final double x, final double y, final double areaWidth, final double areaHeight, final int radius,final boolean setupOverlayRendering) {
        Gui.drawRect(0,0,0,0,0);
        /*Client.INSTANCE.getRenderHelper().addToBlurQueue(() -> {
            RoundedUtils.drawRoundedRectold((float) x, (float) y, (float) (x+areaWidth), (float) (y+areaHeight),radius,new Color(255,255,255));
            return null;
        });*/
    }

    public void bloom(final int x, final int y, final int width, final int height, final int blurRadius, final int bloomAlpha, final boolean ignoreModule) {
        this.bloom(x, y, width, height, blurRadius, new Color(0, 0, 0, bloomAlpha), ignoreModule);
    }

    public void bloom(final int x, final int y, final int width, final int height, final int blurRadius, final int bloomAlpha) {
        this.bloom(x, y, width, height, blurRadius, new Color(0, 0, 0, bloomAlpha), false);
    }

    public void bloom(final int x, final int y, final int width, final int height, final int blurRadius, final Color color) {
        this.bloom(x, y, width, height, blurRadius, color, false);
    }



    public void bloom(int x, int y, int width, int height, final int blurRadius, final Color color, final boolean ignoreModule) {
        GlStateManager.pushAttrib();
        GlStateManager.pushMatrix();
        GlStateManager.alphaFunc(516, 0.01f);
        height = Math.max(0, height);
        width = Math.max(0, width);
        width += blurRadius * 2;
        height += blurRadius * 2;
        x -= blurRadius;
        y -= blurRadius;
        final float _X = x - 0.25f;
        final float _Y = y + 0.25f;
        final int identifier = width * height + width + color.hashCode() * blurRadius + blurRadius;
        GL11.glEnable(3553);
        GL11.glDisable(2884);
        GL11.glEnable(3008);
        GL11.glEnable(3042);
        if (this.shadowCache.containsKey(identifier)) {
            final int texId = this.shadowCache.get(identifier);
            GlStateManager.bindTexture(texId);
        }
        else {
            final BufferedImage original = new BufferedImage(width, height, 2);
            final Graphics g = original.getGraphics();
            g.setColor(color);
            g.fillRect(blurRadius, blurRadius, width - blurRadius * 2, height - blurRadius * 2);
            g.dispose();
            final GaussianFilter op = new GaussianFilter((float)blurRadius);
            final BufferedImage blurred = op.filter(original, null);
            final int texId = TextureUtil.uploadTextureImageAllocate(TextureUtil.glGenTextures(), blurred, true, false);
            this.shadowCache.put(identifier, texId);
        }
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glBegin(7);
        GL11.glTexCoord2f(0.0f, 0.0f);
        GL11.glVertex2d(_X, _Y);
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex2d(_X, _Y + height);
        GL11.glTexCoord2f(1.0f, 1.0f);
        GL11.glVertex2d(_X + width, _Y + height);
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex2d(_X + width, _Y);
        GL11.glEnd();
        GL11.glDisable(3553);
        GL11.glEnable(2884);
        GL11.glDisable(3008);
        GL11.glDisable(3042);
        GlStateManager.popAttrib();
        GlStateManager.popMatrix();
    }

    private boolean sizeHasChangedBlur(final int scaleFactor, final int width, final int height) {
        return this.lastFactorBlur != scaleFactor || this.lastWidthBlur != width || this.lastHeightBlur != height;
    }

    private boolean sizeHasChangedBuffer(final int scaleFactor, final int width, final int height) {
        return this.lastFactorBuffer != scaleFactor || this.lastWidthBuffer != width || this.lastHeightBuffer != height;
    }

    public void scissor(double x, double y, double width, double height) {
        final ScaledResolution sr = new ScaledResolution(this.mc);
        final double scale = sr.getScaleFactor();
        y = sr.getScaledHeight() - y;
        x *= scale;
        y *= scale;
        width *= scale;
        height *= scale;
        GL11.glScissor((int)x, (int)(y - height), (int)width, (int)height);
    }
}