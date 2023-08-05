package wtf.norma.nekito.module.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.event.impl.EventRender2D;
import wtf.norma.nekito.helper.ChatHelper;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.util.render.RenderUtility;

import java.awt.*;


public class Criticals extends Module {



    public Criticals() {
        super("Criticals", Category.COMBAT, Keyboard.KEY_NONE);
    }



    @Override
    public void onEvent(Event e) {
    }



}
