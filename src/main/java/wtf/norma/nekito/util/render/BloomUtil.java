package wtf.norma.nekito.util.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.shader.Framebuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import wtf.norma.nekito.util.Util;
import wtf.norma.nekito.util.shader.ShaderUtility;

import java.awt.*;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;

//expensiv

public class BloomUtil implements Util {

    public static ShaderUtility gaussianBloom = new ShaderUtility("shaders/bloom.frag");

    public static Framebuffer framebuffer = ShaderUtility.createFrameBuffer(new Framebuffer(new ScaledResolution(Minecraft.getMinecraft()).getScaledWidth(), new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight(), true));


    public static void renderBlur(int sourceTexture, int radius, int offset, Color c, float des, boolean fill) {
        GlStateManager.pushMatrix();
        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc(516, 0.0f);
        GlStateManager.enableBlend();
        OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);

        final FloatBuffer weightBuffer = BufferUtils.createFloatBuffer(256);
        for (int i = 1; i <= radius; i++) {
            weightBuffer.put(ShaderUtility.calculateGaussianValue(i, radius / 2f));
        }
        weightBuffer.rewind();

        setAlphaLimit(0.0F);

        framebuffer.framebufferClear();
        framebuffer.bindFramebuffer(true);
        gaussianBloom.init();
        setupUniforms(radius, 1, 0, weightBuffer, c, des);
        ShaderUtility.bindTexture(sourceTexture);
        ShaderUtility.drawQuads();
        gaussianBloom.unload();
        framebuffer.unbindFramebuffer();


        mc.getFramebuffer().bindFramebuffer(true);
        gaussianBloom.init();
        setupUniforms(radius, 0, 1, weightBuffer, c, des);
        GlStateManager.setActiveTexture(GL13.GL_TEXTURE16);
        ShaderUtility.bindTexture(sourceTexture);
        GlStateManager.setActiveTexture(GL13.GL_TEXTURE0);
        ShaderUtility.bindTexture(framebuffer.framebufferTexture);
        ShaderUtility.drawQuads();
        gaussianBloom.unload();

        GlStateManager.alphaFunc(516, 0f);
        GlStateManager.enableAlpha();

        GlStateManager.bindTexture(0);
        GlStateManager.popMatrix();
    }

    public static void setAlphaLimit(float limit) {
        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc(GL_GREATER, (float) (limit * .01));
    }

    public static void setupUniforms(int radius, int directionX, int directionY, FloatBuffer weights, Color c, float des) {
        gaussianBloom.setUniformi("inTexture", 0);
        gaussianBloom.setUniformi("textureToCheck", 16);
        gaussianBloom.setUniformf("radius", radius);
        gaussianBloom.setUniformf("exposure", des);
        gaussianBloom.setUniformf("color", c.getRed() / 255f, c.getGreen() / 255f, c.getBlue() / 255f);
        gaussianBloom.setUniformf("texelSize", 1.0F / (float) mc.displayWidth, 1.0F / (float) mc.displayHeight);
        gaussianBloom.setUniformf("direction", directionX, directionY);
        GL20.glUniform1fv(gaussianBloom.getUniform("weights"), weights);
    }
}