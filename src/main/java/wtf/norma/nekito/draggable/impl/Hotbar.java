package wtf.norma.nekito.draggable.impl;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.FoodStats;
import net.minecraft.util.MathHelper;
import optifine.Config;
import optifine.CustomColors;
import org.lwjgl.util.vector.Vector2f;
import wtf.norma.nekito.draggable.AbstractDraggable;
import wtf.norma.nekito.helper.OpenGlHelper;
import wtf.norma.nekito.module.impl.CustomHotbar;
import wtf.norma.nekito.util.font.Fonts;
import wtf.norma.nekito.util.render.RenderUtility;

import java.awt.*;


public class Hotbar extends AbstractDraggable {
    ScaledResolution sr = new ScaledResolution(mc);
    public int index;

    @Override
    public void Init() {
        //Initable position
        X = sr.getScaledWidth() / 2 - 90;
        Y = sr.getScaledHeight() / 2 + 90;
    }

    //<inheritdoc /> (From AbstractDraggable)
    @Override
    public Vector2f Render() {
        GlStateManager.pushMatrix();
        GlStateManager.translate(X, Y, 1);

        RenderUtility.drawGradientRound(1, 0, 180, 20, 5, new Color(1, 111, 255, 130), new Color(0, 225, 255, 130), new Color(112, 114, 255, 130), new Color(128, 32, 231, 130));
        RenderUtility.drawRound(mc.thePlayer.inventory.currentItem * 20, 0, 20, 20, 5, new Color(255, 255, 255, 100));

        int offset = 7; //height offset for health, food, armor

        if (this.mc.playerController.shouldDrawHUD()) {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.mc.getTextureManager().bindTexture(Gui.icons);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(775, 769, 1, 0);
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);

            if (this.mc.getRenderViewEntity() instanceof EntityPlayer) {
                EntityPlayer entityplayer = (EntityPlayer) this.mc.getRenderViewEntity();
                int i = MathHelper.ceiling_float_int(entityplayer.getHealth());
                boolean flag = mc.ingameGUI.healthUpdateCounter > (long) mc.ingameGUI.updateCounter && (mc.ingameGUI.healthUpdateCounter - (long) mc.ingameGUI.updateCounter) / 3L % 2L == 1L;

                if (i < mc.ingameGUI.playerHealth && entityplayer.hurtResistantTime > 0) {
                    mc.ingameGUI.lastSystemTime = Minecraft.getSystemTime();
                    mc.ingameGUI.healthUpdateCounter = (long) (mc.ingameGUI.updateCounter + 20);
                } else if (i > mc.ingameGUI.playerHealth && entityplayer.hurtResistantTime > 0) {
                    mc.ingameGUI.lastSystemTime = Minecraft.getSystemTime();
                    mc.ingameGUI.healthUpdateCounter = (long) (mc.ingameGUI.updateCounter + 10);
                }

                if (Minecraft.getSystemTime() - mc.ingameGUI.lastSystemTime > 1000L) {
                    mc.ingameGUI.playerHealth = i;
                    mc.ingameGUI.lastPlayerHealth = i;
                    mc.ingameGUI.lastSystemTime = Minecraft.getSystemTime();
                }

                mc.ingameGUI.playerHealth = i;
                int j = mc.ingameGUI.lastPlayerHealth;
                mc.ingameGUI.rand.setSeed((long) (mc.ingameGUI.updateCounter * 312871));
                boolean flag1 = false;
                FoodStats foodstats = entityplayer.getFoodStats();
                int k = foodstats.getFoodLevel();
                int l = foodstats.getPrevFoodLevel();
                IAttributeInstance iattributeinstance = entityplayer.getEntityAttribute(SharedMonsterAttributes.maxHealth);
                int scaledWidthMinus = sr.getScaledWidth() / 2 - 91;
                int scaledWidthPlus = sr.getScaledWidth() / 2 + 91;
                int scaledHeight = sr.getScaledHeight() - 39;
                float f = (float) iattributeinstance.getAttributeValue();
                float f1 = entityplayer.getAbsorptionAmount();
                int l1 = MathHelper.ceiling_float_int((f + f1) / 2.0F / 10.0F);
                int i2 = Math.max(10 - (l1 - 2), 3);
                int j2 = (l1 - 1) * i2 + 25 + offset;
                float f2 = f1;
                int k2 = entityplayer.getTotalArmorValue();
                int l2 = -1;

                if (entityplayer.isPotionActive(Potion.regeneration)) {
                    l2 = mc.ingameGUI.updateCounter % MathHelper.ceiling_float_int(f + 5.0F);
                }

                this.mc.mcProfiler.startSection("armor");

                for (int i3 = 0; i3 < 10; ++i3) {
                    if (k2 > 0) {
                        int j3 = i3 * 8;

                        if (i3 * 2 + 1 < k2) {
                            mc.ingameGUI.drawTexturedModalRect(j3, -j2, 34, 9, 9, 9);
                        }

                        if (i3 * 2 + 1 == k2) {
                            mc.ingameGUI.drawTexturedModalRect(j3, -j2, 25, 9, 9, 9);
                        }

                        if (i3 * 2 + 1 > k2) {
                            mc.ingameGUI.drawTexturedModalRect(j3, -j2, 16, 9, 9, 9);
                        }
                    }
                }

                this.mc.mcProfiler.endStartSection("health");

                for (int j5 = MathHelper.ceiling_float_int((f + f1) / 2.0F) - 1; j5 >= 0; --j5) {
                    int k5 = 16;

                    if (entityplayer.isPotionActive(Potion.poison)) {
                        k5 += 36;
                    } else if (entityplayer.isPotionActive(Potion.wither)) {
                        k5 += 72;
                    }

                    byte b0 = 0;

                    if (flag) {
                        b0 = 1;
                    }

                    int k1 = 39;
                    int k3 = MathHelper.ceiling_float_int((float) (j5 + 1) / 10.0F) - 1;
                    int l3 = j5 % 10 * 8;
                    int i4 = k1 - k3 * i2 - offset - 53;

                    if (i <= 4) {
                        i4 += mc.ingameGUI.rand.nextInt(2);
                    }

                    if (j5 == l2) {
                        i4 -= 2;
                    }

                    byte b1 = 0;

                    if (entityplayer.worldObj.getWorldInfo().isHardcoreModeEnabled()) {
                        b1 = 5;
                    }

                    mc.ingameGUI.drawTexturedModalRect(l3, i4, 16 + b0 * 9, 9 * b1, 9, 9);

                    if (flag) {
                        if (j5 * 2 + 1 < j) {
                            mc.ingameGUI.drawTexturedModalRect(l3, i4, k5 + 54, 9 * b1, 9, 9);
                        }

                        if (j5 * 2 + 1 == j) {
                            mc.ingameGUI.drawTexturedModalRect(l3, i4, k5 + 63, 9 * b1, 9, 9);
                        }
                    }

                    if (f2 <= 0.0F) {
                        if (j5 * 2 + 1 < i) {
                            mc.ingameGUI.drawTexturedModalRect(l3, i4, k5 + 36, 9 * b1, 9, 9);
                        }

                        if (j5 * 2 + 1 == i) {
                            mc.ingameGUI.drawTexturedModalRect(l3, i4, k5 + 45, 9 * b1, 9, 9);
                        }
                    } else {
                        if (f2 == f1 && f1 % 2.0F == 1.0F) {
                            mc.ingameGUI.drawTexturedModalRect(l3, i4, k5 + 153, 9 * b1, 9, 9);
                        } else {
                            mc.ingameGUI.drawTexturedModalRect(l3, i4, k5 + 144, 9 * b1, 9, 9);
                        }

                        f2 -= 2.0F;
                    }
                }

                Entity entity = entityplayer.ridingEntity;

                if (entity == null) {
                    this.mc.mcProfiler.endStartSection("food");

                    for (int l5 = 0; l5 < 10; ++l5) {
                        int i8 = 0;
                        int j6 = 16;
                        byte b4 = 0;

                        if (entityplayer.isPotionActive(Potion.hunger)) {
                            j6 += 36;
                            b4 = 13;
                        }

                        if (entityplayer.getFoodStats().getSaturationLevel() <= 0.0F && mc.ingameGUI.updateCounter % (k * 3 + 1) == 0) {
                            i8 = (mc.ingameGUI.rand.nextInt(3) - 1);
                        }

                        if (flag1) {
                            b4 = 1;
                        }

                        int k7 = l5 * 8 + 100;
                        i8 = i8 - 14 - offset;
                        mc.ingameGUI.drawTexturedModalRect(k7, i8, 16 + b4 * 9, 27, 9, 9);

                        if (flag1) {
                            if (l5 * 2 + 1 < l) {
                                mc.ingameGUI.drawTexturedModalRect(k7, i8, j6 + 54, 27, 9, 9);
                            }

                            if (l5 * 2 + 1 == l) {
                                mc.ingameGUI.drawTexturedModalRect(k7, i8, j6 + 63, 27, 9, 9);
                            }
                        }

                        if (l5 * 2 + 1 < k) {
                            mc.ingameGUI.drawTexturedModalRect(k7, i8, j6 + 36, 27, 9, 9);
                        }

                        if (l5 * 2 + 1 == k) {
                            mc.ingameGUI.drawTexturedModalRect(k7, i8, j6 + 45, 27, 9, 9);
                        }
                    }
                } else if (entity instanceof EntityLivingBase) {
                    this.mc.mcProfiler.endStartSection("mountHealth");
                    EntityLivingBase entitylivingbase = (EntityLivingBase) entity;
                    int l7 = (int) Math.ceil((double) entitylivingbase.getHealth());
                    float f3 = entitylivingbase.getMaxHealth();
                    int l6 = (int) (f3 + 0.5F) / 2;

                    if (l6 > 30) {
                        l6 = 30;
                    }

                    int j7 = scaledHeight;

                    for (int j4 = 0; l6 > 0; j4 += 20) {
                        int k4 = Math.min(l6, 10);
                        l6 -= k4;

                        for (int l4 = 0; l4 < k4; ++l4) {
                            byte b2 = 52;
                            byte b3 = 0;

                            if (flag1) {
                                b3 = 1;
                            }

                            int i5 = scaledWidthPlus - l4 * 8 - 9;
                            mc.ingameGUI.drawTexturedModalRect(i5, j7, b2 + b3 * 9, 9, 9, 9);

                            if (l4 * 2 + 1 + j4 < l7) {
                                mc.ingameGUI.drawTexturedModalRect(i5, j7, b2 + 36, 9, 9, 9);
                            }

                            if (l4 * 2 + 1 + j4 == l7) {
                                mc.ingameGUI.drawTexturedModalRect(i5, j7, b2 + 45, 9, 9, 9);
                            }
                        }

                        j7 -= 10;
                    }
                }

                this.mc.mcProfiler.endStartSection("air");

                if (entityplayer.isInsideOfMaterial(Material.water)) {
                    int i6 = this.mc.thePlayer.getAir();
                    int j8 = MathHelper.ceiling_double_int((double) (i6 - 2) * 10.0D / 300.0D);
                    int k6 = MathHelper.ceiling_double_int((double) i6 * 10.0D / 300.0D) - j8;

                    for (int i7 = 0; i7 < j8 + k6; ++i7) {
                        if (i7 < j8) {
                            mc.ingameGUI.drawTexturedModalRect(scaledWidthPlus - i7 * 8 - 9, j2, 16, 18, 9, 9);
                        } else {
                            mc.ingameGUI.drawTexturedModalRect(scaledWidthPlus - i7 * 8 - 9, j2, 25, 18, 9, 9);
                        }
                    }
                }

                this.mc.mcProfiler.endSection();
            }
            GlStateManager.disableBlend();

            this.mc.mcProfiler.startSection("expBar");
            this.mc.getTextureManager().bindTexture(Gui.icons);
            int i = this.mc.thePlayer.xpBarCap();

            if (i > 0) {
                short short1 = 182;
                int k = (int) (this.mc.thePlayer.experience * (float) (short1 + 1));
                int j = -10;
                mc.ingameGUI.drawTexturedModalRect(0, j, 0, 64, short1, 5);

                if (k > 0) {
                    mc.ingameGUI.drawTexturedModalRect(0, j, 0, 69, k, 5);
                }
            }

            this.mc.mcProfiler.endSection();

            if (this.mc.thePlayer.experienceLevel > 0) {
                this.mc.mcProfiler.startSection("expLevel");
                int j1 = 8453920;

                if (Config.isCustomColors()) {
                    j1 = CustomColors.getExpBarTextColor(j1);
                }

                String s = "" + this.mc.thePlayer.experienceLevel;
                Fonts.SEMI_BOLD_18.drawCenteredString(s, 90, -16, -1);
                this.mc.mcProfiler.endSection();
            }

            this.mc.mcProfiler.startSection("selectedItemName");

            if (mc.ingameGUI.remainingHighlightTicks > 0 && mc.ingameGUI.highlightingItemStack != null) {
                String s = mc.ingameGUI.highlightingItemStack.getDisplayName();

                if (mc.ingameGUI.highlightingItemStack.hasDisplayName()) {
                    s = EnumChatFormatting.ITALIC + s;
                }

                int k = (int) ((float) mc.ingameGUI.remainingHighlightTicks * 256.0F / 10.0F);
                int k2 = (int) ((float) mc.ingameGUI.remainingHighlightTicks * 256.0F / 10.0F);

                if (k2 > 255) {
                    k2 = 255;
                }

                if (k > 125) {
                    k = 125;
                }

                if (k > 0) {
                    GlStateManager.pushMatrix();
                    GlStateManager.enableBlend();
                    GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);

                    RenderUtility.drawGradientRound(87 - Fonts.SEMI_BOLD_18.getStringWidth(s) / 2, -52, Fonts.SEMI_BOLD_18.getStringWidth(s) + 5, 12, 4, new Color(1, 111, 255, k), new Color(0, 225, 255, k), new Color(112, 114, 255, k), new Color(128, 32, 231, k));
                    Fonts.SEMI_BOLD_18.drawCenteredString(s, (float) 90, (float) -50, 16777215 + (k2 << 24));

                    GlStateManager.disableBlend();
                    GlStateManager.popMatrix();
                }
            }
        }

        this.mc.mcProfiler.endSection();

        for (int j = 0; j < 9; ++j) {
            mc.ingameGUI.renderHotbarItem(j, j * 20 + 2, 2, mc.timer.renderPartialTicks, mc.thePlayer);
        }

        GlStateManager.popMatrix();
        return new Vector2f(180, 20);
    }
}
