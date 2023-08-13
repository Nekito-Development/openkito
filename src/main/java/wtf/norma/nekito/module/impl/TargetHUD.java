package wtf.norma.nekito.module.impl;

import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.event.impl.EventRender2D;
import wtf.norma.nekito.helper.ChatHelper;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.nekito;
import wtf.norma.nekito.settings.impl.BooleanSetting;
import wtf.norma.nekito.util.Animations.AnimationHelper;
import wtf.norma.nekito.util.color.ColorUtils;
import wtf.norma.nekito.util.font.Fonts;
import wtf.norma.nekito.util.render.RenderUtility;

import java.awt.*;


/**
 * @author eleczka
 * @project nekito
 * @prod hackerzy mysliborz S.A
 * @at 05.08, 3:37
 */


public class TargetHUD extends Module {
    public static final Color cwel = new Color(250, 247, 250);
    public static BooleanSetting numerfona = new BooleanSetting("Draw Name", true);
    private static EntityLivingBase curTarget = null;
    public final float height = 80;
    public final float mysliborzpolska = height;
    private final float playerWidth = 135;
    private double scale = 0;

    public TargetHUD() {
        super("Target HUD", Category.VISUALS, Keyboard.KEY_NONE);
        //  toggle();
        addSettings(numerfona);

    }

    @Override
    public void onEnable() {
        super.onEnable();
        ChatHelper.printMessage("isnt draggable cuz im too lazy");
        ChatHelper.printMessage("this module is in beta(you can have issues with it)");
        //   nekito.INSTANCE.getDraggableManager().<wtf.norma.nekito.draggable.impl.Arraylist>Get("Arraylist").AllowRender = true;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        //  nekito.INSTANCE.getDraggableManager().<wtf.norma.nekito.draggable.impl.Arraylist>Get("Arraylist").AllowRender = false;
    }

    public void onEvent(Event e) {
        if (e instanceof EventRender2D) {
            this.draworangenakarte();


        }

    }

    public void draworangenakarte() {

        if (KillAura.target == null) {
            if (mc.thePlayer != null && mc.currentScreen instanceof GuiChat) {
                curTarget = mc.thePlayer;
                scale = AnimationHelper.animation((float) scale, (float) 1, (float) (6 * nekito.deltaTime()));
            } else {
                scale = AnimationHelper.animation((float) scale, (float) 0, (float) (6 * nekito.deltaTime()));
            }
        } else {
            curTarget = KillAura.target;
            scale = AnimationHelper.animation((float) scale, (float) 1, (float) (6 * nekito.deltaTime()));
        }


        if (curTarget != null) {

            // why are you skidding my targethud?
            // anyway tag me if you do.

            // author: eleczka aka intexpression

            int x = 50;
            int y = 50;
            int curTargetHealth = (int) curTarget.getHealth();
            int maxTargetHealth = (int) curTarget.getMaxHealth2();


            GlStateManager.pushMatrix();
            GlStateManager.resetColor();
            GL11.glTranslated(x + 36, y + 26, 0);
            GL11.glScaled(scale, scale, 0);
            GL11.glTranslated(-(x + 36), -(y + 26), 0);


            RenderUtility.drawRound(x + (mysliborzpolska - 15), y, playerWidth + 15, height, 6, cwel);
            Fonts.SEMI_BOLD_30.drawString("Orange na karte", 117, 52, -29696);
            RenderUtility.drawImage(new ResourceLocation("images/cwelowate/simswap.png"), 117, 75, 30, 20, new Color(255, 255, 255));
            // prints targetet by killaura player Health (hp)
            Fonts.simkarta.drawString(curTargetHealth + "0GB na", 165, 78, ColorUtils.GREY.cwel);
            // prints targetet by killaura player max Healthhp)
            Fonts.simkarta.drawString(maxTargetHealth + "0 dni", 165, 90, ColorUtils.GREY.cwel);

            RenderUtility.drawImage(new ResourceLocation("images/cwelowate/orange.png"), 245, 54, 15, 15, new Color(255, 255, 255));


            // fuck this bordered rect all my homies uses drawimage
            if (numerfona.isEnabled()) {
                RenderUtility.drawImage(new ResourceLocation("images/cwelowate/numerfona.png"), 117, 100, 50, 30, new Color(255, 255, 255));
                Fonts.SEMI_BOLD_12.drawString(curTarget.getName(), 123, 120, ColorUtils.GREY.cwel);
            }

            GL11.glPopMatrix();


            //     RenderUtility.drawImage(new ResourceLocation("images/cwelowate/kartysim.png"), 230, 100, 50, 30, new Color(255, 255, 255));

        }
    }


}
