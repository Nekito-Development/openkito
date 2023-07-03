package wtf.norma.nekito.module.impl;

import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.nekito;


public class Hotbar extends Module {
    public Hotbar() {
        super("Hotbar", Category.VISUALS, Keyboard.KEY_NONE);
    }


    @Override
    public void onEvent(Event e) {

    }

    @Override
    public void onEnable() {
        super.onEnable();
        nekito.INSTANCE.getDraggableManager().<wtf.norma.nekito.draggable.impl.Watermark>Get("Hotbar").AllowRender = true;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        nekito.INSTANCE.getDraggableManager().<wtf.norma.nekito.draggable.impl.Watermark>Get("Hotbar").AllowRender = false;
    }
}
