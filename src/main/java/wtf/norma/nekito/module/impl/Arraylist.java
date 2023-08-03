package wtf.norma.nekito.module.impl;

import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.nekito;

public class Arraylist extends Module {
    public Arraylist() {
        super("Arraylist", Category.VISUALS, Keyboard.KEY_NONE);
      //  toggle();
    }


    @Override
    public void onEnable() {
        super.onEnable();
        nekito.INSTANCE.getDraggableManager().<wtf.norma.nekito.draggable.impl.Arraylist>Get("Arraylist").AllowRender = true;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        nekito.INSTANCE.getDraggableManager().<wtf.norma.nekito.draggable.impl.Arraylist>Get("Arraylist").AllowRender = false;
    }



}
