package wtf.norma.nekito.helper.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.shader.Framebuffer;
import org.lwjgl.BufferUtils;
import wtf.norma.nekito.helper.shader.ShaderHelper;

import java.nio.FloatBuffer;

import static net.minecraft.client.renderer.OpenGlHelper.glUniform1;
import static org.lwjgl.opengl.GL11.*;


public final class GaussianBlurHelper {

    private static final ShaderHelper BLUR_SHADER = new ShaderHelper("client/shaders/gaussian.frag");

    private static Framebuffer FRAMEBUFFER = new Framebuffer(1, 1, false);

    private GaussianBlurHelper() {
    }

    public static void setupUniforms(float dir1, float dir2, float radius) {
        BLUR_SHADER.setUniformi("textureIn", 0);
        BLUR_SHADER.setUniformf("texelSize", 1.0F / (float) Minecraft.getMinecraft().displayWidth, 1.0F / (float) Minecraft.getMinecraft().displayHeight);
        BLUR_SHADER.setUniformf("direction", dir1, dir2);
        BLUR_SHADER.setUniformf("radius", radius);

        final FloatBuffer weightBuffer = BufferUtils.createFloatBuffer(256);
        for (int i = 0; i <= radius; i++) {
            weightBuffer.put(ShaderHelper.calculateGaussianValue(i, radius / 2));
        }

        weightBuffer.rewind();
        glUniform1(BLUR_SHADER.getUniform("weights"), weightBuffer);
    }


    public static void renderBlur(float radius) {
        GlStateManager.enableBlend();
        GlStateManager.color(1, 1, 1, 1);
        OpenGlHelper.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, GL_ONE, GL_ZERO);


        FRAMEBUFFER = ShaderHelper.createFrameBuffer(FRAMEBUFFER);

        FRAMEBUFFER.framebufferClear();
        FRAMEBUFFER.bindFramebuffer(true);
        BLUR_SHADER.init();
        setupUniforms(1, 0, radius);

        ShaderHelper.bindTexture(Minecraft.getMinecraft().getFramebuffer().framebufferTexture);

        ShaderHelper.drawQuads();
        FRAMEBUFFER.unbindFramebuffer();
        BLUR_SHADER.unload();

        Minecraft.getMinecraft().getFramebuffer().bindFramebuffer(true);
        BLUR_SHADER.init();
        setupUniforms(0, 1, radius);

        ShaderHelper.bindTexture(FRAMEBUFFER.framebufferTexture);
        ShaderHelper.drawQuads();
        BLUR_SHADER.unload();

        GlStateManager.resetColor();
        GlStateManager.bindTexture(0);
    }

    public static void renderBlur(int source, float radius) {
        GlStateManager.enableBlend();
        GlStateManager.color(1, 1, 1, 1);
        OpenGlHelper.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, GL_ONE, GL_ZERO);


        FRAMEBUFFER = ShaderHelper.createFrameBuffer(FRAMEBUFFER);

        FRAMEBUFFER.framebufferClear();
        FRAMEBUFFER.bindFramebuffer(true);
        BLUR_SHADER.init();
        setupUniforms(1, 0, radius);

        ShaderHelper.bindTexture(source);

        ShaderHelper.drawQuads();
        FRAMEBUFFER.unbindFramebuffer();
        BLUR_SHADER.unload();

        Minecraft.getMinecraft().getFramebuffer().bindFramebuffer(true);
        BLUR_SHADER.init();
        setupUniforms(0, 1, radius);

        ShaderHelper.bindTexture(FRAMEBUFFER.framebufferTexture);
        ShaderHelper.drawQuads();
        BLUR_SHADER.unload();

        GlStateManager.resetColor();
        GlStateManager.bindTexture(0);
    }

    public static void renderBlur(float radius, Runnable data) {
        GlStateManager.enableBlend();
        GlStateManager.color(1, 1, 1, 1);
        OpenGlHelper.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, GL_ONE, GL_ZERO);


        FRAMEBUFFER = ShaderHelper.createFrameBuffer(FRAMEBUFFER);

        FRAMEBUFFER.framebufferClear();
        FRAMEBUFFER.bindFramebuffer(true);
        BLUR_SHADER.init();
        setupUniforms(1, 0, radius);

        ShaderHelper.bindTexture(Minecraft.getMinecraft().getFramebuffer().framebufferTexture);

        ShaderHelper.drawQuads();
        FRAMEBUFFER.unbindFramebuffer();
        BLUR_SHADER.unload();

        Minecraft.getMinecraft().getFramebuffer().bindFramebuffer(true);
        BLUR_SHADER.init();
        setupUniforms(0, 1, radius);

        ShaderHelper.bindTexture(FRAMEBUFFER.framebufferTexture);
        data.run();
        BLUR_SHADER.unload();

        GlStateManager.resetColor();
        GlStateManager.bindTexture(0);
    }
}
