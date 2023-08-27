package wtf.norma.nekito.module.impl;

import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.Nekito;


public class ServerInfo extends Module {
    public ServerInfo() {
        super("ServerInfo", Category.HUD, Keyboard.KEY_NONE);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        Nekito.INSTANCE.getDraggableManager().<wtf.norma.nekito.draggable.impl.ServerInfo>Get("ServerInfo").AllowRender = true;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        Nekito.INSTANCE.getDraggableManager().<wtf.norma.nekito.draggable.impl.ServerInfo>Get("ServerInfo").AllowRender = false;
    }
}





