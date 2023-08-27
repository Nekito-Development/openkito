package wtf.norma.nekito.module.impl.visuals;

import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import me.zero.alpine.listener.Subscriber;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import wtf.norma.nekito.Nekito;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.event.impl.render.EventCustomModel;
import wtf.norma.nekito.settings.impl.ModeSetting;
import wtf.norma.nekito.util.math.MathUtility;
import wtf.norma.nekito.util.render.models.TessellatorModel;

public class CustomModel extends Module implements Subscriber {


    public ModeSetting mode = new ModeSetting("Mode", "Hitla", "Hitla", "Jake", "Baba");
    private TessellatorModel hitlerHead;
    private TessellatorModel hitlerBody;
    private TessellatorModel jake;
    private TessellatorModel baba;
    public CustomModel() {
        super("CustomModel", Category.VISUALS, Keyboard.KEY_NONE);
        addSettings(mode);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        Nekito.EVENT_BUS.subscribe(this);
        this.hitlerHead = new TessellatorModel("/assets/minecraft/nekito/head.obj");
        this.hitlerBody = new TessellatorModel("/assets/minecraft/nekito/body.obj");
        this.jake = new TessellatorModel("/assets/minecraft/nekito/Jake.obj");
        this.baba = new TessellatorModel("/assets/minecraft/nekito/Aether.obj"); // ta z genshina kojarze ja bo gralem
        // cwele z mihoyo kiedy kurwa wkoncu ten jeabny nintendo switch support bo na tym jebanym nvidia now sie grac nie dai
    }

    @Override
    public void onDisable() {
        super.onDisable();
        Nekito.EVENT_BUS.unsubscribe(this);
        this.hitlerHead = null;
        this.jake = null;
        this.hitlerBody = null;
        this.baba = null;
    }


    // nie wiem
    // https://www.youtube.com/watch?v=xjD8MiCe9BU
    @Subscribe
    private final Listener<EventCustomModel> listener = new Listener<>(event -> {
        GlStateManager.pushMatrix();
        AbstractClientPlayer entity = mc.thePlayer;  // tu mozna zmienic np na friends jak dodam ale mi sie nie chce Xddddddddd
        RenderManager manager = mc.getRenderManager();
        double x = MathUtility.interpolate(entity.posX, entity.lastTickPosX, mc.getRenderPartialTicks()) - RenderManager.renderPosX;
        double y = MathUtility.interpolate(entity.posY, entity.lastTickPosY, mc.getRenderPartialTicks()) - RenderManager.renderPosY;
        double z = MathUtility.interpolate(entity.posZ, entity.lastTickPosZ, mc.getRenderPartialTicks()) - RenderManager.renderPosZ;
        float yaw = mc.thePlayer.prevRotationYaw + (mc.thePlayer.rotationYaw - mc.thePlayer.prevRotationYaw) * mc.getRenderPartialTicks();
        boolean sneak = mc.thePlayer.isSneaking();
        GL11.glTranslated(x, y, z);
        if (!(mc.currentScreen instanceof GuiContainer))
            GL11.glRotatef(-yaw, 0.0F, mc.thePlayer.height, 0.0F);


        switch (mode.getMode()) {
            case "Hitla":
                GlStateManager.scale(0.03, sneak ? 0.027 : 0.029, 0.03);
                GlStateManager.disableLighting();
                GlStateManager.color(1, 1, 1, 1.0F);
                this.hitlerHead.render();
                this.hitlerBody.render();
                break;
            case "Jake":
                GlStateManager.scale(0.3, sneak ? 0.27 : 0.29, 0.3);
                GlStateManager.disableLighting();
                GlStateManager.color(1, 1, 1, 1.0F);
                this.jake.render();
                break;
            case "Baba":
                GlStateManager.scale(0.3, sneak ? 0.12 : 0.15, 0.3);
                GlStateManager.disableLighting();
                GlStateManager.color(1, 1, 1, 1.0F);
                this.baba.render();
                break;
        }
        GlStateManager.enableLighting();
        GlStateManager.resetColor();
        GlStateManager.popMatrix();
    });


//Commented out by DevOfDeath 😂😂😂😂😂
//    public void onEvent(Event event) {
//        if (event instanceof EventCustomModel) {
//            GlStateManager.pushMatrix();
//            AbstractClientPlayer entity = mc.thePlayer;  // tu mozna zmienic np na friends jak dodam ale mi sie nie chce Xddddddddd
//            RenderManager manager = mc.getRenderManager();
//            double x = MathUtility.interpolate(entity.posX, entity.lastTickPosX, mc.getRenderPartialTicks()) - RenderManager.renderPosX;
//            double y = MathUtility.interpolate(entity.posY, entity.lastTickPosY, mc.getRenderPartialTicks()) - RenderManager.renderPosY;
//            double z = MathUtility.interpolate(entity.posZ, entity.lastTickPosZ, mc.getRenderPartialTicks()) - RenderManager.renderPosZ;
//            float yaw = mc.thePlayer.prevRotationYaw + (mc.thePlayer.rotationYaw - mc.thePlayer.prevRotationYaw) * mc.getRenderPartialTicks();
//            boolean sneak = mc.thePlayer.isSneaking();
//            GL11.glTranslated(x, y, z);
//            if (!(mc.currentScreen instanceof GuiContainer))
//                GL11.glRotatef(-yaw, 0.0F, mc.thePlayer.height, 0.0F);
//
//
//            switch (mode.getMode()) {
//                case "Hitla":
//                    GlStateManager.scale(0.03, sneak ? 0.027 : 0.029, 0.03);
//                    GlStateManager.disableLighting();
//                    GlStateManager.color(1, 1, 1, 1.0F);
//                    this.hitlerHead.render();
//                    this.hitlerBody.render();
//                    break;
//                case "Jake":
//                    GlStateManager.scale(0.3, sneak ? 0.27 : 0.29, 0.3);
//                    GlStateManager.disableLighting();
//                    GlStateManager.color(1, 1, 1, 1.0F);
//                    this.jake.render();
//                    break;
//                case "Baba":
//                    GlStateManager.scale(0.3, sneak ? 0.12 : 0.15, 0.3);
//                    GlStateManager.disableLighting();
//                    GlStateManager.color(1, 1, 1, 1.0F);
//                    this.baba.render();
//                    break;
//            }
//            GlStateManager.enableLighting();
//            GlStateManager.resetColor();
//            GlStateManager.popMatrix();
//        }
//    }

}
