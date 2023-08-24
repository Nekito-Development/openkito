package wtf.norma.nekito.module.impl;

import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.nekito;


public class Watermark2 extends Module {
    public Watermark2() {
        super("cfx watermark", Category.HUD, Keyboard.KEY_NONE);
    }


    @Override
    public void onEvent(Event e) {

    }

    @Override
    public void onEnable() {
        super.onEnable();
        nekito.INSTANCE.getDraggableManager().<wtf.norma.nekito.draggable.impl.Watermark2>Get("Watermark2").AllowRender = true;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        nekito.INSTANCE.getDraggableManager().<wtf.norma.nekito.draggable.impl.Watermark2>Get("Watermark2").AllowRender = false;
    }
}
