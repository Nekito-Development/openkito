package wtf.norma.nekito.module.impl;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.event.impl.EventRender3D;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.settings.impl.BooleanSetting;
import wtf.norma.nekito.util.color.ColorUtility;
import wtf.norma.nekito.util.color.ColorUtils;
import wtf.norma.nekito.util.render.RenderUtility;

import java.awt.*;

public class EntityESP extends Module {
    public static BooleanSetting cweluch = new BooleanSetting("Client Color", true);
    public BooleanSetting fullBox = new BooleanSetting("Full Box", false);


    // public static ModeSetting dyinginside = new ModeSetting("ESP Mode",  "Box" );

    // public static ModeSetting ambatukam = new ModeSetting("Border Mode", "Box",  "Box");


    public EntityESP() {
        super("Entity ESP", Category.VISUALS, Keyboard.KEY_NONE);
        addSettings(fullBox, cweluch);
    }

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
                } else { // master code
                    RenderUtility.drawEntityBox(entity, new Color(ColorUtils.WHITE.cwel), fullBox.isEnabled(), fullBox.isEnabled() ? 0.15f : 0.9f);
                }

            }

            GlStateManager.popMatrix();
            //     }


        }

    }

}