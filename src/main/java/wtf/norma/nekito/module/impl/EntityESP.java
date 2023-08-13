package wtf.norma.nekito.module.impl;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.*;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.event.impl.EventRender2D;
import wtf.norma.nekito.event.impl.EventRender3D;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.nekito;
import wtf.norma.nekito.settings.impl.BooleanSetting;
import wtf.norma.nekito.settings.impl.ModeSetting;
import wtf.norma.nekito.util.color.ColorUtility;
import wtf.norma.nekito.util.color.ColorUtils;
import wtf.norma.nekito.util.font.Fonts;
import wtf.norma.nekito.util.math.MathUtility;
import wtf.norma.nekito.util.render.RenderUtility;

import javax.vecmath.Vector3d;
import javax.vecmath.Vector4d;
import java.awt.*;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class EntityESP extends Module {
    public EntityESP() {
        super("Entity ESP", Category.VISUALS, Keyboard.KEY_NONE);
        addSettings(fullBox,cweluch);
    }

    public static BooleanSetting cweluch = new BooleanSetting("Client Color", true);



   // public static ModeSetting dyinginside = new ModeSetting("ESP Mode",  "Box" );

   // public static ModeSetting ambatukam = new ModeSetting("Border Mode", "Box",  "Box");


    public BooleanSetting fullBox = new BooleanSetting("Full Box", false);
    public void onEvent(Event e) {


        if (e instanceof EventRender3D) {


          //  if (dyinginside.getMode().equals("Box")) {
                GlStateManager.pushMatrix();
                for (Entity entity : mc.theWorld.loadedEntityList) {
                    if (!(entity instanceof EntityPlayer) || entity == mc.thePlayer && mc.gameSettings.thirdPersonView == 0) {
                        continue;
                    }
                    if (cweluch.isEnabled()) {
                        RenderUtility.drawEntityBox(entity, new Color(ColorUtility.getColor(0)), fullBox.isEnabled(), fullBox.isEnabled() ? 0.15f : 0.9f);
                    }  else { // master code
                        RenderUtility.drawEntityBox(entity, new Color(ColorUtils.WHITE.cwel), fullBox.isEnabled(), fullBox.isEnabled() ? 0.15f : 0.9f);
                    }

                }

                GlStateManager.popMatrix();
       //     }


        }

    }
    private boolean isValid(Entity entity) {
        if (entity == null) {
            return false;
        }

        if (mc.gameSettings.thirdPersonView == 0 && entity == mc.thePlayer) {
            return false;
        }

        if (entity.isDead) {
            return false;
        }

        if (entity instanceof EntityAnimal) {
            return false;
        }

        if (entity instanceof EntityPlayer) {
            return true;
        }

        if (entity instanceof EntityArmorStand) {
            return false;
        }

        if (entity instanceof IAnimals) {
            return false;
        }

        if (entity instanceof EntityItemFrame) {
            return false;
        }

        if (entity instanceof EntityArrow ) {
            return false;
        }

        if (entity instanceof EntityMinecart) {
            return false;
        }

        if (entity instanceof EntityBoat) {
            return false;
        }

        if (entity instanceof EntityFireball) {
            return false;
        }

        if (entity instanceof EntityXPOrb) {
            return false;
        }

        if (entity instanceof EntityMinecartChest) {
            return false;
        }

        if (entity instanceof EntityTNTPrimed) {
            return false;
        }

        if (entity instanceof EntityMinecartTNT) {
            return false;
        }

        if (entity instanceof EntityVillager) {
            return false;
        }

        if (entity instanceof EntityExpBottle) {
            return false;
        }

        if (entity instanceof EntityLightningBolt) {
            return false;
        }

        if (entity instanceof EntityPotion) {
            return false;
        }

        if (!(!(entity instanceof Entity) || entity instanceof EntityMob || entity instanceof EntityWaterMob || entity instanceof IAnimals || entity instanceof EntityLiving || entity instanceof EntityItem)) {
            return false;
        }

        if ((entity instanceof EntityMob || entity instanceof EntitySlime || entity instanceof EntityDragon || entity instanceof EntityGolem)) {
            return false;
        }

        return entity != mc.thePlayer;
    }

    private Vector3d project2D(int scaleFactor, double x, double y, double z) {
        float xPos = (float) x;
        float yPos = (float) y;
        float zPos = (float) z;
        IntBuffer viewport = GLAllocation.createDirectIntBuffer(16);
        FloatBuffer modelview = GLAllocation.createDirectFloatBuffer(16);
        FloatBuffer projection = GLAllocation.createDirectFloatBuffer(16);
        FloatBuffer vector = GLAllocation.createDirectFloatBuffer(4);
        GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX);
        GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX);
        GL11.glGetInteger(GL11.GL_VIEWPORT);

        if (GLU.gluProject(xPos, yPos, zPos, modelview, projection, viewport, vector)) {
            return new Vector3d(vector.get(0) / (float) scaleFactor, ((float) Display.getHeight() - vector.get(1)) / (float) scaleFactor, vector.get(2));
        }

        return null;
    }


}