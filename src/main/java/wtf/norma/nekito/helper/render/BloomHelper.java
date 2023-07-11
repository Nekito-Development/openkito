package wtf.norma.nekito.helper.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.shader.Framebuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import wtf.norma.nekito.helper.shader.ShaderHelper;

import java.awt.*;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.GL_GREATER;

//expensiv

public final class BloomHelper {

    private static final ShaderHelper GAUSSIAN_BLOOM = new ShaderHelper("shaders/bloom.frag");

    private static final Framebuffer FRAME_BUFFER = ShaderHelper.createFrameBuffer(new Framebuffer(new ScaledResolution(Minecraft.getMinecraft()).getScaledWidth(), new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight(), true));

    private BloomHelper() {
    }

    public static void renderBlur(int sourceTexture, int radius, int offset, Color c, float des, boolean fill) {
        GlStateManager.pushMatrix();
        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc(516, 0.0f);
        GlStateManager.enableBlend();
        OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);

        final FloatBuffer weightBuffer = BufferUtils.createFloatBuffer(256);
        for (int i = 1; i <= radius; i++) {
            weightBuffer.put(ShaderHelper.calculateGaussianValue(i, radius / 2f));
        }
        weightBuffer.rewind();

        setAlphaLimit(0.0F);

        FRAME_BUFFER.framebufferClear();
        FRAME_BUFFER.bindFramebuffer(true);
        GAUSSIAN_BLOOM.init();
        setupUniforms(radius, 1, 0, weightBuffer, c, des);
        ShaderHelper.bindTexture(sourceTexture);
        ShaderHelper.drawQuads();
        GAUSSIAN_BLOOM.unload();
        FRAME_BUFFER.unbindFramebuffer();


        Minecraft.getMinecraft().getFramebuffer().bindFramebuffer(true);
        GAUSSIAN_BLOOM.init();
        setupUniforms(radius, 0, 1, weightBuffer, c, des);
        GlStateManager.setActiveTexture(GL13.GL_TEXTURE16);
        ShaderHelper.bindTexture(sourceTexture);
        GlStateManager.setActiveTexture(GL13.GL_TEXTURE0);
        ShaderHelper.bindTexture(FRAME_BUFFER.framebufferTexture);
        ShaderHelper.drawQuads();
        GAUSSIAN_BLOOM.unload();

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
        GAUSSIAN_BLOOM.setUniformi("inTexture", 0);
        GAUSSIAN_BLOOM.setUniformi("textureToCheck", 16);
        GAUSSIAN_BLOOM.setUniformf("radius", radius);
        GAUSSIAN_BLOOM.setUniformf("exposure", des);
        GAUSSIAN_BLOOM.setUniformf("color", c.getRed() / 255f, c.getGreen() / 255f, c.getBlue() / 255f);
        GAUSSIAN_BLOOM.setUniformf("texelSize", 1.0F / (float) Minecraft.getMinecraft().displayWidth, 1.0F / (float) Minecraft.getMinecraft().displayHeight);
        GAUSSIAN_BLOOM.setUniformf("direction", directionX, directionY);
        GL20.glUniform1fv(GAUSSIAN_BLOOM.getUniform("weights"), weights);
    }
}