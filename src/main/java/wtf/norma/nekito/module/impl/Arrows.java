package wtf.norma.nekito.module.impl;

import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import me.zero.alpine.listener.Subscriber;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import wtf.norma.nekito.Nekito;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.newevent.impl.render.EventRender2D;
import wtf.norma.nekito.util.color.ColorUtility;
import wtf.norma.nekito.util.font.Fonts;
import wtf.norma.nekito.util.render.RenderUtility;

import java.awt.*;


public class Arrows extends Module implements Subscriber {
    public Arrows() {
        super("Arrows", Category.VISUALS, Keyboard.KEY_NONE);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        Nekito.EVENT_BUS.subscribe(this);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        Nekito.EVENT_BUS.unsubscribe(this);
    }

    @Subscribe
    private final Listener<EventRender2D> listener = new Listener<>(event -> {
        float size = 50;
        ScaledResolution sr = new ScaledResolution(mc);
        float xOffset = sr.getScaledWidth() / 2F - 24.5F;
        float yOffset = sr.getScaledHeight() / 2F - 25.2F;
        mc.theWorld.playerEntities.forEach(entity -> {
            Color col = new Color(255, 255, 255, 80);

            if (!isValid(entity)) return;

                GlStateManager.pushMatrix();
                GlStateManager.disableBlend();
                double x = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * mc.timer.renderPartialTicks - RenderManager.renderPosX;
                double z = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * mc.timer.renderPartialTicks - RenderManager.renderPosZ;
                double cos = Math.cos(mc.thePlayer.rotationYaw * (Math.PI * 2 / 360));
                double sin = Math.sin(mc.thePlayer.rotationYaw * (Math.PI * 2 / 360));
                double rotY = -(z * cos - x * sin);
                double rotX = -(x * cos + z * sin);

                float angle = (float) (Math.atan2(rotY, rotX) * 180 / Math.PI);
                double xPos = (20 * Math.cos(Math.toRadians(angle))) + xOffset + size / 2;
                double y = (20 * Math.sin(Math.toRadians(angle))) + yOffset + size / 2;
                GL11.glPushMatrix();
                GlStateManager.translate(xPos - 6, y + 4, 0);

                Fonts.SEMI_BOLD_12.drawCenteredString(String.format("%.1f", mc.thePlayer.getDistanceToEntity(entity)) + "m", 8, -2, ColorUtility.brighter(col, 0.5F).getRGB());
                GL11.glPopMatrix();

                GlStateManager.translate(xPos, y, 0);

                GlStateManager.rotate(angle, 0, 0, 1);

                GlStateManager.disableBlend();
                GlStateManager.scale(0.6, 0., 0.);
                RenderUtility.drawTriangle(0 - 5F, 0F, 5F, 10F, col.brighter().getRGB(), col.darker().getRGB());
                RenderUtility.drawBlurredShadow(-3F, -5F, 12F, 10F, 13, col);
                GlStateManager.enableBlend();

                GlStateManager.popMatrix();

        });
    });

//    public void onEvent(Event e) {
//        if (e instanceof EventRender2D) {
//            float size = 50;
//            ScaledResolution sr = new ScaledResolution(mc);
//            float xOffset = sr.getScaledWidth() / 2F - 24.5F;
//            float yOffset = sr.getScaledHeight() / 2F - 25.2F;
//            mc.theWorld.playerEntities.forEach(entity -> {
//                Color col = new Color(255, 255, 255, 80);
//
//                if (isValid(entity)) {
//                    GlStateManager.pushMatrix();
//                    GlStateManager.disableBlend();
//                    double x = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * mc.timer.renderPartialTicks - RenderManager.renderPosX;
//                    double z = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * mc.timer.renderPartialTicks - RenderManager.renderPosZ;
//                    double cos = Math.cos(mc.thePlayer.rotationYaw * (Math.PI * 2 / 360));
//                    double sin = Math.sin(mc.thePlayer.rotationYaw * (Math.PI * 2 / 360));
//                    double rotY = -(z * cos - x * sin);
//                    double rotX = -(x * cos + z * sin);
//
//                    float angle = (float) (Math.atan2(rotY, rotX) * 180 / Math.PI);
//                    double xPos = (20 * Math.cos(Math.toRadians(angle))) + xOffset + size / 2;
//                    double y = (20 * Math.sin(Math.toRadians(angle))) + yOffset + size / 2;
//                    GL11.glPushMatrix();
//                    GlStateManager.translate(xPos - 6, y + 4, 0);
//
//                    Fonts.SEMI_BOLD_12.drawCenteredString(String.format("%.1f", mc.thePlayer.getDistanceToEntity(entity)) + "m", 8, -2, ColorUtility.brighter(col, 0.5F).getRGB());
//                    GL11.glPopMatrix();
//
//                    GlStateManager.translate(xPos, y, 0);
//
//                    GlStateManager.rotate(angle, 0, 0, 1);
//
//                    GlStateManager.disableBlend();
//                    GlStateManager.scale(0.6, 0., 0.);
//                    RenderUtility.drawTriangle(0 - 5F, 0F, 5F, 10F, col.brighter().getRGB(), col.darker().getRGB());
//                    RenderUtility.drawBlurredShadow(-3F, -5F, 12F, 10F, 13, col);
//                    GlStateManager.enableBlend();
//
//                    GlStateManager.popMatrix();
//                }
//            });
//        }
//    }


    public boolean isValid(Entity entity) {
        return entity != mc.thePlayer;
    }
}




