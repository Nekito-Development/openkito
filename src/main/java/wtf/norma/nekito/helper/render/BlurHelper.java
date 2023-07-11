package wtf.norma.nekito.helper.render;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import wtf.norma.nekito.helper.shader.ShaderHelper;

import java.awt.*;

//expensiv
public final class BlurHelper {
    private static final ResourceLocation SHADER = new ResourceLocation("shaders/post/blur.json");
    private static final Framebuffer BLOOM_FRAMEBUFFER = ShaderHelper.createFrameBuffer(new Framebuffer(new ScaledResolution(Minecraft.getMinecraft()).getScaledWidth(), new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight(), true));
    private static int lastScale, lastScaleWidth, lastScaleHeight;
    private static Framebuffer buffer;
    private static ShaderGroup blurShader;

    private BlurHelper() {
    }

    public static void drawBlur(float radius, Runnable data) {
        StencilHelper.initStencilToWrite();
        data.run();
        StencilHelper.readStencilBuffer(1);
        GaussianBlurHelper.renderBlur(radius);
        StencilHelper.uninitStencilBuffer();
    }

    public static void blurAreaBoarder(int x, int y, int width, int height, float intensity) {
        ScaledResolution scale = new ScaledResolution(Minecraft.getMinecraft());
        int factor = scale.getScaleFactor();
        int factor2 = scale.getScaledWidth();
        int factor3 = scale.getScaledHeight();
        if (lastScale != factor || lastScaleWidth != factor2 || lastScaleHeight != factor3 || buffer == null || blurShader == null) {
            inShaderFBO();
        }
        lastScale = factor;
        lastScaleWidth = factor2;
        lastScaleHeight = factor3;
        GL11.glScissor(x * factor, Minecraft.getMinecraft().displayHeight - y * factor - height * factor, width * factor, height * factor);
        GL11.glEnable(3089);
        shaderConfigFix(intensity, 1.0f, 0.0f);
        buffer.bindFramebuffer(true);
        blurShader.loadShaderGroup(Minecraft.getMinecraft().timer.renderPartialTicks);
        Minecraft.getMinecraft().getFramebuffer().bindFramebuffer(true);
        GL11.glDisable(3089);
    }

    private static void shaderConfigFix(float intensity, float blurWidth, float blurHeight) {
        blurShader.getShaders().get(0).getShaderManager().getShaderUniform("Radius").set(intensity);
        blurShader.getShaders().get(1).getShaderManager().getShaderUniform("Radius").set(intensity);
        blurShader.getShaders().get(0).getShaderManager().getShaderUniform("BlurDir").set(blurWidth, blurHeight);
        blurShader.getShaders().get(1).getShaderManager().getShaderUniform("BlurDir").set(blurHeight, blurWidth);
    }

    public static void inShaderFBO() {
        try {
            blurShader = new ShaderGroup(Minecraft.getMinecraft().getTextureManager(), Minecraft.getMinecraft().getResourceManager(), Minecraft.getMinecraft().getFramebuffer(), SHADER);
            blurShader.createBindFramebuffers(Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
            buffer = blurShader.mainFramebuffer;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void renderBlur(int x, int y, int width, int height, int blurRadius) {
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glDisable(2884);
        blurAreaBoarder(x, y, width, height, blurRadius);
        GL11.glDisable(3042);
        GL11.glEnable(3553);
        GL11.glEnable(2884);
        GL11.glEnable(3008);
        GL11.glEnable(3553);
        GL11.glEnable(3042);
    }

    public static void drawShadow(float radius, float des, Runnable data, Color c) {
        BLOOM_FRAMEBUFFER.framebufferClear();
        BLOOM_FRAMEBUFFER.bindFramebuffer(true);
        data.run();
        BLOOM_FRAMEBUFFER.unbindFramebuffer();
        BloomHelper.renderBlur(BLOOM_FRAMEBUFFER.framebufferTexture, (int) radius, 1, c, des, true);
    }
}
