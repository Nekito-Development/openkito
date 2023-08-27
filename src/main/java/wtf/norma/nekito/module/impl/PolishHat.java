package wtf.norma.nekito.module.impl;


import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import me.zero.alpine.listener.Subscriber;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import wtf.norma.nekito.Nekito;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.newevent.impl.render.EventRender3D;
import wtf.norma.nekito.util.color.ColorUtility;
import wtf.norma.nekito.util.render.RenderUtility;

import java.awt.*;


public class PolishHat extends Module implements Subscriber {

    // aureola
        public PolishHat() {
            super("PolishHat", Category.VISUALS, Keyboard.KEY_NONE);

        }

    @Override
    public void onEnable() {
        Nekito.EVENT_BUS.subscribe(this);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        Nekito.EVENT_BUS.unsubscribe(this);
        super.onDisable();
    }

    @Subscribe
    private final Listener<EventRender3D> listener = new Listener<>(event ->{
        double height = 0.0;
        if (mc.gameSettings.thirdPersonView == 1 || mc.gameSettings.thirdPersonView == 2) { // change this later
            GlStateManager.pushMatrix();
            GL11.glBlendFunc(770, 771);
            GlStateManager.disableDepth();
            GlStateManager.disableTexture2D();
            RenderUtility.enableSmoothLine(2.5f);
            GL11.glShadeModel(7425);
            GL11.glDisable(2884);
            GL11.glEnable(3042);
            GL11.glEnable(2929);
            GL11.glTranslatef(0.0f, (float) (mc.thePlayer.height + height), 0.0f);
            GL11.glRotatef(-mc.thePlayer.rotationYaw, 0.0f, 1.0f, 0.0f);
            Color color = new Color(ColorUtility.getColor(0));
            GL11.glBegin(6);
            RenderUtility.glColor(color, 255);
            GL11.glVertex3d(0.0, 0.3, 0.0);
            for (float i = 0.0f; i < 360.5; ++i) {
                RenderUtility.glColor(color, 255);
            }
            GL11.glVertex3d(0.0, 0.3, 0.0);
            GL11.glEnd();
            GL11.glBegin(2);
            for (float i = 0.0f; i < 360.5; ++i) {
                RenderUtility.glColor(color, 255);
                GL11.glVertex3d(Math.cos(i * 3.14 / 180.0) * 0.45, 0.10, Math.sin(i * 3.14 / 180.0) * 0.45);
                GL11.glVertex3d(Math.cos(i * 3.14/ 180.0) * 0.45, 0.10, Math.sin(i * 3.14 / 180.0) * 0.45);
                GL11.glVertex3d(Math.cos(i * 3.14 / 180.0) * 0.45, 0.10, Math.sin(i * 3.14 / 180.0) * 0.45);
            }
            GL11.glEnd();
            GlStateManager.enableAlpha();
            RenderUtility.disableSmoothLine();
            GL11.glShadeModel(7424);
            GL11.glEnable(2884);
            GL11.glDisable(3042);
            GlStateManager.enableTexture2D();
            GlStateManager.enableDepth();
            GlStateManager.resetColor();
            GlStateManager.popMatrix();
        }
    });



//    @Override
//        public void onEvent(Event e) {
//            if (e instanceof EventRender3D) {
//                double height = 0.0;
//             if (mc.gameSettings.thirdPersonView == 1 || mc.gameSettings.thirdPersonView == 2) { // change this later
//                    GlStateManager.pushMatrix();
//                    GL11.glBlendFunc(770, 771);
//                    GlStateManager.disableDepth();
//                    GlStateManager.disableTexture2D();
//                    RenderUtility.enableSmoothLine(2.5f);
//                    GL11.glShadeModel(7425);
//                    GL11.glDisable(2884);
//                    GL11.glEnable(3042);
//                    GL11.glEnable(2929);
//                    GL11.glTranslatef(0.0f, (float) (mc.thePlayer.height + height), 0.0f);
//                    GL11.glRotatef(-mc.thePlayer.rotationYaw, 0.0f, 1.0f, 0.0f);
//                    Color color = new Color(ColorUtility.getColor(0));
//                    GL11.glBegin(6);
//                    RenderUtility.glColor(color, 255);
//                    GL11.glVertex3d(0.0, 0.3, 0.0);
//                    for (float i = 0.0f; i < 360.5; ++i) {
//                        RenderUtility.glColor(color, 255);
//                    }
//                    GL11.glVertex3d(0.0, 0.3, 0.0);
//                    GL11.glEnd();
//                    GL11.glBegin(2);
//                    for (float i = 0.0f; i < 360.5; ++i) {
//                        RenderUtility.glColor(color, 255);
//                        GL11.glVertex3d(Math.cos(i * 3.14 / 180.0) * 0.45, 0.10, Math.sin(i * 3.14 / 180.0) * 0.45);
//                        GL11.glVertex3d(Math.cos(i * 3.14/ 180.0) * 0.45, 0.10, Math.sin(i * 3.14 / 180.0) * 0.45);
//                        GL11.glVertex3d(Math.cos(i * 3.14 / 180.0) * 0.45, 0.10, Math.sin(i * 3.14 / 180.0) * 0.45);
//                    }
//                    GL11.glEnd();
//                    GlStateManager.enableAlpha();
//                    RenderUtility.disableSmoothLine();
//                    GL11.glShadeModel(7424);
//                    GL11.glEnable(2884);
//                    GL11.glDisable(3042);
//                    GlStateManager.enableTexture2D();
//                    GlStateManager.enableDepth();
//                    GlStateManager.resetColor();
//                    GlStateManager.popMatrix();
//                }
//            }
//        }
    }
