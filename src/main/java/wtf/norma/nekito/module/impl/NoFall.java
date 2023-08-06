package wtf.norma.nekito.module.impl;

import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.event.impl.EventPreMotion;
import wtf.norma.nekito.event.impl.EventUpdate;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.settings.impl.ModeSetting;
import wtf.norma.nekito.util.Time.TimerUtility;
import wtf.norma.nekito.util.player.MovementUtil;


public class NoFall extends Module {


    public ModeSetting mode = new ModeSetting("Mode", "Vanilla", "Vanilla", "");


    public NoFall() {
        super("No Fall", Category.MOVEMENT, Keyboard.KEY_NONE);
    }


    @Override
    public void onEvent(Event e) {
        if (e instanceof EventPreMotion) {
               switch(mode.getMode()){
                   case "Vanilla":
                       if(!MovementUtil.isOnGround(0.001) && mc.thePlayer.motionY < 0){
                           ((EventPreMotion) e).setOnGround(true);
                       }
                       break;
               }




        }
    }
}
