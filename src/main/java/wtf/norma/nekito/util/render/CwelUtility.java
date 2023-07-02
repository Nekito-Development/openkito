package wtf.norma.nekito.util.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.shader.Framebuffer;
import wtf.norma.nekito.util.shader.ShaderUtility;

import static org.lwjgl.opengl.GL11.*;

public class CwelUtility  {

    Minecraft mc = Minecraft.getMinecraft();
    ScaledResolution sr = new ScaledResolution(mc);
    public static ShaderUtility blurShader = new ShaderUtility("shaders/white.frag");

    public static Framebuffer framebuffer = new Framebuffer(1, 1, false);


    public static void setupUniforms() {
        blurShader.setUniformi("textureIn", 0);
    }


    public static void renderGray() {
        GlStateManager.enableBlend();
        GlStateManager.color(1, 1, 1, 1);
        OpenGlHelper.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, GL_ONE, GL_ZERO);


        framebuffer = ShaderUtility.createFrameBuffer(framebuffer);

        framebuffer.framebufferClear();
        framebuffer.bindFramebuffer(true);
        blurShader.init();
        setupUniforms();
        Minecraft mc = Minecraft.getMinecraft();

        ShaderUtility.bindTexture(mc.getFramebuffer().framebufferTexture);

        ShaderUtility.drawQuads();
        framebuffer.unbindFramebuffer();
        blurShader.unload();

        mc.getFramebuffer().bindFramebuffer(true);
        blurShader.init();
        setupUniforms();

        ShaderUtility.bindTexture(framebuffer.framebufferTexture);
        ShaderUtility.drawQuads();
        blurShader.unload();

        GlStateManager.resetColor();
        GlStateManager.bindTexture(0);
    }

}