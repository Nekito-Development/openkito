package wtf.norma.nekito.draggable.impl;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.util.vector.Vector2f;
import wtf.norma.nekito.draggable.AbstractDraggable;
import wtf.norma.nekito.helper.OpenGlHelper;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.nekito;
import wtf.norma.nekito.util.color.ColorUtility;
import wtf.norma.nekito.util.font.Fonts;
import wtf.norma.nekito.util.render.RenderUtil;
import wtf.norma.nekito.util.render.RenderUtility;
import wtf.norma.nekito.util.render.BloomUtil;

import java.awt.*;
import java.util.ArrayList;

public class Arraylist extends AbstractDraggable {
    public int longest;

    @Override
    public void Init() {
        super.Init();
       // AllowRender = false;
        ScaledResolution sr = new ScaledResolution(mc);
        X = 5;
        Y = 5;
    }

    @Override
    public Vector2f Render() {
        longest = 0;
        ScaledResolution sr = new ScaledResolution(mc);
        ArrayList<Module> enabledMods = new ArrayList<Module>();
        for(Module m : nekito.INSTANCE.getModuleManager().getModules())
            if(m.isToggled())
                enabledMods.add(m);

        enabledMods.sort((m1,m2) -> Fonts.SEMI_BOLD_16.getStringWidth(m2.getName()) - Fonts.SEMI_BOLD_16.getStringWidth(m1.getName()));



        int offset = Y;
        if(X < sr.getScaledWidth()/2.0f) {
            for(Module m : enabledMods) {
                if(Fonts.SEMI_BOLD_16.getStringWidth(m.getName()) > longest) {
                    longest = Fonts.SEMI_BOLD_16.getStringWidth(m.getName());
                }
                Gui.drawRect(X-1,offset,X+Fonts.SEMI_BOLD_16.getStringWidth(m.getName())+2,offset+10,new Color(0,0,0,153).getRGB());
                Fonts.SEMI_BOLD_16.drawString(m.getName(),X,offset+2, ColorUtility.getColor(3000));
                offset+=10;
            }
            Gui.drawRect(X-1,Y-1,X+longest+2,Y,ColorUtility.getColor(300));
        } else {
            for(Module m : enabledMods) {
                if(Fonts.SEMI_BOLD_16.getStringWidth(m.getName()) > longest) {
                    longest = Fonts.SEMI_BOLD_16.getStringWidth(m.getName());
                }
                Gui.drawRect(X-Fonts.SEMI_BOLD_16.getStringWidth(m.getName())-1,offset,X-Fonts.SEMI_BOLD_16.getStringWidth(m.getName())+Fonts.SEMI_BOLD_16.getStringWidth(m.getName())+2,offset+10,new Color(0,0,0,153).getRGB());
                Fonts.SEMI_BOLD_16.drawString(m.getName(),X-Fonts.SEMI_BOLD_16.getStringWidth(m.getName()),offset+2, ColorUtility.getColor(3000));
                offset+=10;
            }
            Gui.drawRect(X-longest-1,Y-1,X+2,Y,ColorUtility.getColor(3000));
        }

        return  new Vector2f(longest,100);
    }
}