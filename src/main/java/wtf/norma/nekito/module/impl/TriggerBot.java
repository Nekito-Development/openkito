package wtf.norma.nekito.module.impl;

import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.settings.impl.NumberSetting;


public class TriggerBot extends Module {



    public TriggerBot() {

        super("TriggerBot", Category.OTHER, Keyboard.KEY_NONE);
        addSettings(cwel);
    }

    public NumberSetting cwel = new NumberSetting("cwel",1.0f,1f,10.0f,0.1f);


    public void onEvent(Event event) {
    }


    @Override
    public void onEnable() {
        super.onEnable();
    }


    @Override
    public void onDisable() {
        super.onDisable();
    }


}
