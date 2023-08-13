package wtf.norma.nekito.module.impl;

import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.nekito;

public class CustomHotbar extends Module {
    public CustomHotbar() {
        super("CustomHotbar", Category.VISUALS, Keyboard.KEY_NONE);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        nekito.INSTANCE.getDraggableManager().<wtf.norma.nekito.draggable.impl.Hotbar>Get("Hotbar").AllowRender = true;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        nekito.INSTANCE.getDraggableManager().<wtf.norma.nekito.draggable.impl.Hotbar>Get("Hotbar").AllowRender = false;
    }
}
