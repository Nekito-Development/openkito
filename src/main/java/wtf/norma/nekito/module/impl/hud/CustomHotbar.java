package wtf.norma.nekito.module.impl.hud;

import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.Nekito;

public class CustomHotbar extends Module {
    public CustomHotbar() {
        super("CustomHotbar", Category.HUD, Keyboard.KEY_NONE);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        Nekito.INSTANCE.getDraggableManager().<wtf.norma.nekito.draggable.impl.Hotbar>Get("Hotbar").AllowRender = true;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        Nekito.INSTANCE.getDraggableManager().<wtf.norma.nekito.draggable.impl.Hotbar>Get("Hotbar").AllowRender = false;
    }
}
