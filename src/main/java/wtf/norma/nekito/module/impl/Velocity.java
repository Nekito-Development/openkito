package wtf.norma.nekito.module.impl;

import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S27PacketExplosion;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.event.impl.EventMotion;
import wtf.norma.nekito.event.impl.PacketEvent;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.settings.impl.NumberSetting;


public class Velocity extends Module {



    public Velocity() {

        super("Velocity", Category.COMBAT, Keyboard.KEY_NONE);
        addSettings(horizontal,vertical);
    }

    public NumberSetting horizontal = new NumberSetting("Horizontal",0F, 0F, 1F, 0.01F);

    public NumberSetting vertical = new NumberSetting("Vertical",0F, 0F, 1F, 0.01F);

    public void onEvent(Event event) {
        if (event instanceof PacketEvent) {
            if (((PacketEvent) event).getPacket() instanceof S12PacketEntityVelocity) {
                S12PacketEntityVelocity sv = (S12PacketEntityVelocity) ((PacketEvent) event).getPacket();
                if (mc.thePlayer.getEntityId() == sv.getEntityID()) {
                    if (horizontal.getValue() == 0F && vertical.getValue() == 0F)
                        event.setCancelled(true);

                    sv.motionX = (int) (sv.motionX * horizontal.getValue());
                    sv.motionY = (int) (sv.motionY * vertical.getValue());
                    sv.motionZ = (int) (sv.motionZ * horizontal.getValue());


                }
            }



        }
        }
    }





