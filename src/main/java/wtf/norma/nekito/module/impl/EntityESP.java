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
                    if (!(entity instanceof EntityPlayer) || entity == mc.thePlayer && mc.gameSettings.thirdPersonView == 0 ) {
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

}