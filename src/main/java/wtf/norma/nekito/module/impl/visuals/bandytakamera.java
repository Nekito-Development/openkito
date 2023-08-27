package wtf.norma.nekito.module.impl.visuals;

import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import me.zero.alpine.listener.Subscriber;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.Nekito;
import wtf.norma.nekito.helper.ChatHelper;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.event.impl.render.EventRender2D;
import wtf.norma.nekito.util.render.RenderUtility;

import java.awt.*;


public class bandytakamera extends Module implements Subscriber {


    // nudzilo mi sie ok?


    public bandytakamera() {
        super("bandyta kamera", Category.VISUALS, Keyboard.KEY_NONE);
    }

    @Override
    public void onEnable() {
        ChatHelper.printMessage("this module is in beta(you can have issues with it)");
        ChatHelper.printMessage("If its bugged or dark wait 8secs");
        Nekito.EVENT_BUS.subscribe(this);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        Nekito.EVENT_BUS.unsubscribe(this);
        super.onDisable();
    }

    @Subscribe
    private final Listener<EventRender2D> listener = new Listener<>(event ->{
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        if (mc.gameSettings.thirdPersonView <= 0)
            RenderUtility.drawImage(new ResourceLocation("images/cwelowate/bandicam.png"), sr.getScaledWidth() / 2 - 200, 1, 400, 100, new Color(255, 255, 255));
    });

//    @Override
//    public void onEvent(Event e) {
//        if (e instanceof EventRender2D) {
//            ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
//            if (mc.gameSettings.thirdPersonView <= 0) {
//                RenderUtility.drawImage(new ResourceLocation("images/cwelowate/bandicam.png"), sr.getScaledWidth() / 2 - 200, 1, 400, 100, new Color(255, 255, 255));
//            }
//        }
//    }


}
