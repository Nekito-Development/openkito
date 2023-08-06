package wtf.norma.nekito.module.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.core.pattern.AbstractStyleNameConverter;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.event.impl.EventRender2D;
import wtf.norma.nekito.helper.ChatHelper;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.nekito;
import wtf.norma.nekito.settings.impl.BooleanSetting;
import wtf.norma.nekito.settings.impl.ModeSetting;
import wtf.norma.nekito.util.Animations.AnimationHelper;
import wtf.norma.nekito.util.color.ColorUtility;
import wtf.norma.nekito.util.color.ColorUtils;
import wtf.norma.nekito.util.font.Fonts;
import wtf.norma.nekito.util.render.BlurUtility;
import wtf.norma.nekito.util.render.RenderUtility;

import java.awt.*;
import java.util.ArrayList;


/**
 * @project nekito
 * @prod hackerzy mysliborz S.A
 * @author eleczka
 * @at 05.08, 3:37
 */


public class TargetHUD extends Module {
    public TargetHUD() {
        super("Target HUD", Category.VISUALS, Keyboard.KEY_NONE);
        //  toggle();
        addSettings(numerfona);

    }
    public static BooleanSetting numerfona = new BooleanSetting("Draw Name", true);




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




    public final float height = 80;
    public final float mysliborzpolska = height;
    public void onEvent(Event e) {
        if (e instanceof EventRender2D) {
                this.draworangenakarte(KillAura.target);






        }

    }
    private final float playerWidth = 135;



    public static final Color cwel = new Color(250,247,250);
    public void draworangenakarte(EntityLivingBase target) {


        if (KillAura.target != null) {




            // why are you skidding my targethud?
            // anyway tag me if you do.

            // author: eleczka aka intexpression

            int x = 50;
            int y = 50;
            int curTargetHealth = (int) target.getHealth();
            int maxTargetHealth = (int) target.getMaxHealth2();


            //  Fonts.SEMI_BOLD_18.drawString("Orange",256,890, ColorUtils.ORANGE.cwel);

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
                Fonts.SEMI_BOLD_12.drawString(target.getName(), 123, 120, ColorUtils.GREY.cwel);
            }




            //     RenderUtility.drawImage(new ResourceLocation("images/cwelowate/kartysim.png"), 230, 100, 50, 30, new Color(255, 255, 255));

        }
    }


}
