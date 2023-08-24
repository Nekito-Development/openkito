package wtf.norma.nekito.module.impl;

import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.nekito;
import wtf.norma.nekito.settings.impl.BooleanSetting;
import wtf.norma.nekito.settings.impl.ModeSetting;

public class Arraylist extends Module {
    public Arraylist() {
        super("Arraylist", Category.HUD, Keyboard.KEY_NONE);
        //  toggle();
        addSettings(fonts,line,lowerCase);
    }


    public static ModeSetting fonts = new ModeSetting("Fonts", "SemiBold", "SemiBold","ProductSans","Sans","Rubik", "Ubuntu",  "Vag", "Hack");

    public static BooleanSetting lowerCase = new BooleanSetting("LowerCase",false);


    public static BooleanSetting line = new BooleanSetting("Line",true);





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
