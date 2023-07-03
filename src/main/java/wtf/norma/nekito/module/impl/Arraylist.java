package wtf.norma.nekito.module.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.event.impl.EventRender2D;
import wtf.norma.nekito.helper.OpenGlHelper;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.module.ModuleManager;
import wtf.norma.nekito.nekito;
import wtf.norma.nekito.settings.impl.BooleanSetting;
import wtf.norma.nekito.util.font.Fonts;
import wtf.norma.nekito.util.render.RenderUtility;

import java.awt.*;
import java.util.ArrayList;


public class Arraylist extends Module {
    public Arraylist() {
        super("Arraylist", Category.VISUALS, Keyboard.KEY_NONE);
        toggle();
    }


    @Override
    public void onEvent(Event e) {
        if (e instanceof EventRender2D) {
            ScaledResolution sr = new ScaledResolution(mc);
            ArrayList<Module> enabledMods = new ArrayList<Module>();
            for(Module m : nekito.INSTANCE.getModuleManager().getModules())
                if(m.isToggled())
                    enabledMods.add(m);

            enabledMods.sort((m1,m2) -> Fonts.SEMI_BOLD_16.getStringWidth(m2.getName()) - Fonts.SEMI_BOLD_16.getStringWidth(m1.getName()));

            int y = 5;
            int x = 5;
            for(Module m : enabledMods)
            {
                Fonts.SEMI_BOLD_18.drawString(m.getName(), sr.getScaledWidth() - Fonts.SEMI_BOLD_18.getStringWidth(m.getName()) - x, y, OpenGlHelper.rainbowColor(3000, 1 + y * 22));
                y+=10;
            }
        }
    }
}
