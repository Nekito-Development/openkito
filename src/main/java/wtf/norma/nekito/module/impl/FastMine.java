package wtf.norma.nekito.module.impl;

import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.settings.impl.NumberSetting;


public class FastMine extends Module {

    public FastMine() {
        super("FastMine", Category.OTHER, Keyboard.KEY_NONE);
        addSettings(speed);
    }





    public static NumberSetting speed = new NumberSetting("Speed",3,1,10,1);

    public static float speed() {
        // cry abt it
       return FastMine.speed.getValue();
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }




}
