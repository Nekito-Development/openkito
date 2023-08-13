package wtf.norma.nekito.module.impl;

import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.nekito;
import wtf.norma.nekito.settings.impl.ModeSetting;


public class Watermark extends Module {
    public static ModeSetting colorMode = new ModeSetting("Color", "Nekito", "Nekito", "Rainbow", "Purple", "Pink");


    //  public static ModeSetting mode = new ModeSetting("Mode", "Nekito", "Nekito","wzrost");

    public Watermark() {
        super("Watermark", Category.VISUALS, Keyboard.KEY_NONE);
        addSettings(colorMode);
    }

    @Override
    public void onEvent(Event e) {


    }

    @Override
    public void onEnable() {
        nekito.INSTANCE.getDraggableManager().<wtf.norma.nekito.draggable.impl.Watermark>Get("Watermark").AllowRender = true;
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
        nekito.INSTANCE.getDraggableManager().<wtf.norma.nekito.draggable.impl.Watermark>Get("Watermark").AllowRender = false;
    }
}
