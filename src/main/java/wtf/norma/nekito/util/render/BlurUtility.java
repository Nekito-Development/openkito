package wtf.norma.nekito.util.render;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.shader.Framebuffer;
import wtf.norma.nekito.util.Util;
import wtf.norma.nekito.util.shader.ShaderUtility;

import java.awt.*;

//expensiv
public class BlurUtility implements Util {
    public static Framebuffer bloomFramebuffer =  ShaderUtility.createFrameBuffer(new Framebuffer(new ScaledResolution(Minecraft.getMinecraft()).getScaledWidth(), new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight(), true));

    public static void drawShadow(float radius, float des, Runnable data, Color c) {
        bloomFramebuffer.framebufferClear();
        bloomFramebuffer.bindFramebuffer(true);
        data.run();
        bloomFramebuffer.unbindFramebuffer();
        BloomUtil.renderBlur(bloomFramebuffer.framebufferTexture, (int) radius, 1, c, des, true);

    }
}
