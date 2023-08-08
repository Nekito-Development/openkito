package wtf.norma.nekito.module.impl;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import wtf.norma.nekito.event.Event;

import wtf.norma.nekito.event.impl.*;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.util.math.MathUtility;
import wtf.norma.nekito.util.render.models.TessellatorModel;
import wtf.norma.nekito.event.impl.EventCustomModel;

public class CustomModel extends Module {



    public CustomModel() {
        super("CustomModel", Category.VISUALS, Keyboard.KEY_NONE);
    }

    private TessellatorModel hitlerHead;
    private TessellatorModel hitlerBody;


    @Override
    public void onEnable() {
        super.onEnable();
        this.hitlerHead = new TessellatorModel("/assets/minecraft/nekito/head.obj");
        this.hitlerBody = new TessellatorModel("/assets/minecraft/nekito/body.obj");
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.hitlerHead = null;
        this.hitlerBody = null;
    }



    // nie wiem
    // https://www.youtube.com/watch?v=xjD8MiCe9BU

    public void onEvent(Event event) {
        if (event instanceof EventCustomModel) {
            GlStateManager.pushMatrix();
            AbstractClientPlayer entity = mc.thePlayer;  // tu mozna zmienic np na friends jak dodam ale mi sie nie chce Xddddddddd
            RenderManager manager = mc.getRenderManager();
            double x = MathUtility.interpolate(entity.posX, entity.lastTickPosX, mc.getRenderPartialTicks()) - manager.renderPosX;
            double y = MathUtility.interpolate(entity.posY, entity.lastTickPosY, mc.getRenderPartialTicks()) - manager.renderPosY;
            double z =  MathUtility.interpolate(entity.posZ, entity.lastTickPosZ, mc.getRenderPartialTicks()) - manager.renderPosZ;
            float yaw = mc.thePlayer.prevRotationYaw + (mc.thePlayer.rotationYaw - mc.thePlayer.prevRotationYaw) * mc.getRenderPartialTicks();
            boolean sneak = mc.thePlayer.isSneaking();
            GL11.glTranslated(x, y, z);
            if (!(mc.currentScreen instanceof GuiContainer))
                GL11.glRotatef(-yaw, 0.0F, mc.thePlayer.height, 0.0F);
            GlStateManager.scale(0.03, sneak ? 0.027 : 0.029, 0.03);
            GlStateManager.disableLighting();
            GlStateManager.color(1, 1, 1, 1.0F);
            this.hitlerHead.render();
            this.hitlerBody.render();
            GlStateManager.enableLighting();
            GlStateManager.resetColor();
            GlStateManager.popMatrix();


        }
    }

}
