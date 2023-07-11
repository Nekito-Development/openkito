package wtf.norma.nekito.cosmetics;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import wtf.norma.nekito.Nekito;
import wtf.norma.nekito.helper.TimeHelper;
import wtf.norma.nekito.module.impl.visuals.WingsModule;

public class WingsModel extends ModelBase {
    private final TimeHelper timeHelper = new TimeHelper();

    private float airTicks;

    public static void renderWingsIn3D(WorldRenderer worldrenderer) {
        Tessellator tessellator = Tessellator.getInstance();
        GL11.glPushMatrix();
        GL11.glTranslated(0.0D, 0.0D, 0.0D);
        GL11.glEnable(32826);
        GL11.glTranslatef(1.0F, -0.10000001F, 0.1F);
        GL11.glScalef(1.875F, 1.875F, 1.0F);
        GL11.glRotatef(0.0F, 0.0F, 200.0F, 0.0F);
        GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
        GL11.glTranslatef(-0.9375F, -0.0625F, 0.0F);
        GL11.glScaled(1.0D, 1.0D, 0.7D);
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);
        worldrenderer.pos(0.0D, 0.0D, 0.0D).tex(1.0D, 1.0D).normal(0.0F, 0.0F, 1.0F).endVertex();
        worldrenderer.pos(1.0D, 0.0D, 0.0D).tex(0.0D, 1.0D).normal(0.0F, 0.0F, 1.0F).endVertex();
        worldrenderer.pos(1.0D, 1.0D, 0.0D).tex(0.0D, 0.0D).normal(0.0F, 0.0F, 1.0F).endVertex();
        worldrenderer.pos(0.0D, 1.0D, 0.0D).tex(1.0D, 0.0D).normal(0.0F, 0.0F, 1.0F).endVertex();
        tessellator.draw();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);
        worldrenderer.pos(0.0D, 1.0D, -0.078125D).tex(1.0D, 0.0D).normal(0.0F, 0.0F, -1.0F).endVertex();
        worldrenderer.pos(1.0D, 1.0D, -0.078125D).tex(0.0D, 0.0D).normal(0.0F, 0.0F, -1.0F).endVertex();
        worldrenderer.pos(1.0D, 0.0D, -0.078125D).tex(0.0D, 1.0D).normal(0.0F, 0.0F, -1.0F).endVertex();
        worldrenderer.pos(0.0D, 0.0D, -0.078125D).tex(1.0D, 1.0D).normal(0.0F, 0.0F, -1.0F).endVertex();
        tessellator.draw();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);
        int k;
        for (k = 0; k < 32.0F; k++) {
            float f1 = k / 32.0F;
            float f2 = 1.0F + -1.0F * f1 - 0.015625F;
            worldrenderer.pos(f1, 0.0D, -0.078125D).tex(f2, 1.0D).normal(-1.0F, 0.0F, 0.0F).endVertex();
            worldrenderer.pos(f1, 0.0D, 0.0D).tex(f2, 1.0D).normal(-1.0F, 0.0F, 0.0F).endVertex();
            worldrenderer.pos(f1, 1.0D, 0.0D).tex(f2, 0.0D).normal(-1.0F, 0.0F, 0.0F).endVertex();
            worldrenderer.pos(f1, 1.0D, -0.078125D).tex(f2, 0.0D).normal(-1.0F, 0.0F, 0.0F).endVertex();
        }
        tessellator.draw();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);
        for (k = 0; k < 32.0F; k++) {
            float f1 = k / 32.0F;
            float f2 = 1.0F + -1.0F * f1 - 0.015625F;
            f1 += 0.03125F;
            worldrenderer.pos(f1, 1.0D, -0.078125D).tex(f2, 0.0D).normal(1.0F, 0.0F, 0.0F).endVertex();
            worldrenderer.pos(f1, 1.0D, 0.0D).tex(f2, 0.0D).normal(1.0F, 0.0F, 0.0F).endVertex();
            worldrenderer.pos(f1, 0.0D, 0.0D).tex(f2, 1.0D).normal(1.0F, 0.0F, 0.0F).endVertex();
            worldrenderer.pos(f1, 0.0D, -0.078125D).tex(f2, 1.0D).normal(1.0F, 0.0F, 0.0F).endVertex();
        }
        tessellator.draw();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);
        for (k = 0; k < 32.0F; k++) {
            float f1 = k / 32.0F;
            float f2 = 1.0F + -1.0F * f1 - 0.015625F;
            f1 += 0.03125F;
            worldrenderer.pos(0.0D, f1, 0.0D).tex(1.0D, f2).normal(0.0F, 1.0F, 0.0F).endVertex();
            worldrenderer.pos(1.0D, f1, 0.0D).tex(0.0D, f2).normal(0.0F, 1.0F, 0.0F).endVertex();
            worldrenderer.pos(1.0D, f1, -0.078125D).tex(0.0D, f2).normal(0.0F, 1.0F, 0.0F).endVertex();
            worldrenderer.pos(0.0D, f1, -0.078125D).tex(1.0D, f2).normal(0.0F, 1.0F, 0.0F).endVertex();
        }
        tessellator.draw();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);
        for (k = 0; k < 32.0F; k++) {
            float f1 = k / 32.0F;
            float f2 = 1.0F + -1.0F * f1 - 0.015625F;
            worldrenderer.pos(1.0D, f1, 0.0D).tex(0.0D, f2).normal(0.0F, -1.0F, 0.0F).endVertex();
            worldrenderer.pos(0.0D, f1, 0.0D).tex(1.0D, f2).normal(0.0F, -1.0F, 0.0F).endVertex();
            worldrenderer.pos(0.0D, f1, -0.078125D).tex(1.0D, f2).normal(0.0F, -1.0F, 0.0F).endVertex();
            worldrenderer.pos(1.0D, f1, -0.078125D).tex(0.0D, f2).normal(0.0F, -1.0F, 0.0F).endVertex();
        }
        tessellator.draw();
        GL11.glDisable(32826);
        GL11.glPopMatrix();
    }

    public void render(EntityPlayerSP player, boolean isSneaking, boolean isFlying, boolean onGround, boolean isSprinting) {
        postRender(player, isSneaking, isFlying, onGround, isSprinting);
    }

    private void postRender(EntityPlayerSP player, boolean isSneaking, boolean isFlying, boolean onGround, boolean isSprinting) {
        renderWings(player, isSneaking, isFlying, onGround, isSprinting);
    }

    public void renderWings(EntityPlayerSP player, boolean isSneaking, boolean isFlying, boolean onGround, boolean isSprinting) {
        Tessellator tessellator = Tessellator.getInstance();
        GL11.glPushMatrix();
        GL11.glScalef(1.1F, 1.1F, 1.1F);
        GL11.glTranslatef(0.0F, 0.290F, 0.195F);
        if (isSneaking)
            GL11.glTranslatef(0.0F, 0.1F, 0.175F);
        GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
        GL11.glPushMatrix();

        if (timeHelper.passed(10L)) {
            if (isSneaking)
                this.airTicks -= 0.1F;
            if ((!isFlying || onGround) && !isSprinting) {
                this.airTicks += 0.2F;
            } else {
                this.airTicks += 0.4F;
            }
            timeHelper.reset();
        }

        if (isSneaking) {
            GL11.glRotatef(30.0F + (float) Math.sin(this.airTicks / 4.0D) * 20.0F, 1.5F, 0.0F, 2.5F);
        } else {
            GL11.glRotatef(24.0F + (float) Math.sin(this.airTicks / 4.0D) * 20.0F, 0.0F, 0.0F, 1.0F);
        }
        GL11.glTranslatef(-0.125F, 0.125F, 0.18F);

        WingsModule wingsModule = Nekito.INSTANCE.getModuleManager().findModule(WingsModule.class).map(WingsModule.class::cast).orElseThrow(NullPointerException::new);

        switch (wingsModule.selectedWing.get()) {
            case "Wing1":
                Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("nekito/wings/wings0.png"));
                break;
            case "Wing2":
                Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("nekito/wings/wings1.png"));
                break;
            case "Wing3":
                Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("nekito/wings/wings3.png"));
                break;
            case "Wing4":
                Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("nekito/wings/wings4.png"));
                break;
        }
        GL11.glRotatef(100.0F, 0.0F, 1.0F, 0.0F);
        renderWingsIn3D(tessellator.getWorldRenderer());
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        if (isSneaking) {
            GL11.glRotatef(-30.0F - (float) Math.sin(this.airTicks / 4.0D) * 20.0F, -1.5F, 0.0F, 2.5F);
        } else {
            GL11.glRotatef(-24.0F - (float) Math.sin(this.airTicks / 4.0D) * 20.0F, 0.0F, 0.0F, 1.0F);
        }
        GL11.glTranslatef(0.0F, 0.125F, 0.18F);

        switch (wingsModule.selectedWing.get()) {
            case "Wing1":
                Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("nekito/wings/wings0.png"));
                break;
            case "Wing2":
                Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("nekito/wings/wings1.png"));
                break;
            case "Wing3":
                Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("nekito/wings/wings3.png"));
                break;
            case "Wing4":
                Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("nekito/wings/wings4.png"));
                break;
        }

        GL11.glRotatef(80.0F, 0.0F, 1.0F, 0.0F);
        renderWingsIn3D(tessellator.getWorldRenderer());
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
}
