package wtf.norma.nekito.module.impl.visuals;

import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import me.zero.alpine.listener.Subscriber;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.Nekito;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.event.impl.render.EventRender3D;
import wtf.norma.nekito.settings.impl.BooleanSetting;
import wtf.norma.nekito.util.color.ColorUtility;
import wtf.norma.nekito.util.color.ColorUtils;
import wtf.norma.nekito.util.render.RenderUtility;

import java.awt.*;

public class EntityESP extends Module implements Subscriber {
    public static BooleanSetting cweluch = new BooleanSetting("Client Color", true);
    public BooleanSetting fullBox = new BooleanSetting("Full Box", false);


    // public static ModeSetting dyinginside = new ModeSetting("ESP Mode",  "Box" );

    // public static ModeSetting ambatukam = new ModeSetting("Border Mode", "Box",  "Box");








    public EntityESP() {
        super("Entity ESP", Category.VISUALS, Keyboard.KEY_NONE);
        addSettings(fullBox, cweluch);
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
    private final Listener<EventRender3D> listener = new Listener<>(event -> {
        //  if (dyinginside.getMode().equals("Box")) {
        GlStateManager.pushMatrix();
//        Can't use stream api due to continue keyword
        for (Entity entity : mc.theWorld.loadedEntityList) {
            if (!(entity instanceof EntityPlayer) || entity == mc.thePlayer && mc.gameSettings.thirdPersonView == 0 && !entity.isInvisible())
                continue;

            if (cweluch.isEnabled())
            RenderUtility.drawEntityBox(entity, new Color(ColorUtility.getColor(0)), fullBox.isEnabled(), fullBox.isEnabled() ? 0.15f : 0.9f);

            else // master code
            RenderUtility.drawEntityBox(entity, new Color(ColorUtils.WHITE.cwel), fullBox.isEnabled(), fullBox.isEnabled() ? 0.15f : 0.9f);
        }
        GlStateManager.popMatrix();
    });

//    public void onEvent(Event e) {
//        if (e instanceof EventRender3D) {
//            //  if (dyinginside.getMode().equals("Box")) {
//            GlStateManager.pushMatrix();
//            for (Entity entity : mc.theWorld.loadedEntityList) {
//                if (!(entity instanceof EntityPlayer) || entity == mc.thePlayer && mc.gameSettings.thirdPersonView == 0 && !entity.isInvisible()) {
//                    continue;
//                }
//                if (cweluch.isEnabled()) {
//                    RenderUtility.drawEntityBox(entity, new Color(ColorUtility.getColor(0)), fullBox.isEnabled(), fullBox.isEnabled() ? 0.15f : 0.9f);
//                } else { // master code
//                    RenderUtility.drawEntityBox(entity, new Color(ColorUtils.WHITE.cwel), fullBox.isEnabled(), fullBox.isEnabled() ? 0.15f : 0.9f);
//                }
//            }
//            GlStateManager.popMatrix();
//            //     }
//        }
//    }
}