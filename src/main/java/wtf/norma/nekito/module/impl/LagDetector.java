package wtf.norma.nekito.module.impl;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.event.impl.EventRender2D;
import wtf.norma.nekito.event.impl.EventUpdate;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.settings.impl.NumberSetting;
import wtf.norma.nekito.util.Time.TimerUtility;
import wtf.norma.nekito.util.render.RenderUtility;

import java.awt.*;

public class LagDetector extends Module {

    public NumberSetting ping = new NumberSetting("Ping", 80, 50, 1000, 10);
    TimerUtility timer = new TimerUtility();

    public LagDetector() {
        super("Lag Detector", Category.VISUALS, Keyboard.KEY_NONE);
        addSettings(ping);
    }

    // images are not mine
    @Override
    public void onEvent(Event e) {
        if (e instanceof EventRender2D) {
            ScaledResolution sr = new ScaledResolution(mc);
            if (timer.hasReached(ping.getValue())) {
                if (timer.hasReached(ping.getValue() + 10)) {
                    RenderUtility.drawImage(new ResourceLocation("images/cwelowate/lag2.png"), sr.getScaledWidth() / 2 - 20, sr.getScaledHeight() / 2 - 130, 40, 40, new Color(255, 255, 255));
                } else {
                    RenderUtility.drawImage(new ResourceLocation("images/cwelowate/lag.png"), sr.getScaledWidth() / 2 - 20, sr.getScaledHeight() / 2 - 130, 40, 40, new Color(255, 255, 255));
                }
            }
        }
        if (e instanceof EventUpdate) {
            if (!(((EventUpdate) e).getPacket() instanceof S02PacketChat)) {
                this.timer.reset();
            }
        }
    }





}


