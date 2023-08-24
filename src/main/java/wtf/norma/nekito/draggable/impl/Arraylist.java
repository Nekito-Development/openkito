package wtf.norma.nekito.draggable.impl;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.util.vector.Vector2f;
import wtf.norma.nekito.draggable.AbstractDraggable;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.nekito;
import wtf.norma.nekito.util.color.ColorUtility;
import wtf.norma.nekito.util.font.FontRenderer;
import wtf.norma.nekito.util.font.Fonts;

import java.awt.*;
import java.sql.Array;
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

    public FontRenderer getFont() {
         switch(wtf.norma.nekito.module.impl.Arraylist.fonts.getMode()){
             case "SemiBold": return Fonts.SEMI_BOLD_16;
             case"ProductSans": return Fonts.productSansRegular16;
             case "Sans": return Fonts.sans_16;
             case "Rubik": return Fonts.RUBIK_16;
             case "Ubuntu": return Fonts.ubuntu16;
             case "Vag": return Fonts.vag16;
             case "Hack": return Fonts.hack16;
             default: return Fonts.SEMI_BOLD_16;
         }
        // maybe do 18 not 16
        // productsansior$$$
    }

    @Override
    public Vector2f Render() {
        longest = 0;
        ScaledResolution sr = new ScaledResolution(mc);
        ArrayList<Module> enabledMods = new ArrayList<Module>();
        for(Module m : nekito.INSTANCE.getModuleManager().getModules())
            if(m.isToggled())
                enabledMods.add(m);

        enabledMods.sort((m1,m2) -> getFont().getStringWidth(m2.getName()) - getFont().getStringWidth(m1.getName()));



        int offset = Y;
        if(X < sr.getScaledWidth()/2.0f) {
            for(Module m : enabledMods) {
                if(getFont().getStringWidth(m.getName()) > longest) {
                    longest = getFont().getStringWidth(m.getName());
                }
                Gui.drawRect(X-1,offset,X+getFont().getStringWidth(m.getName())+2,offset+10,new Color(0,0,0,153).getRGB());
                getFont().drawString(m.getName(),X,offset+2, ColorUtility.getColor(3000));
                offset+=10;
            }
            if (wtf.norma.nekito.module.impl.Arraylist.line.isEnabled()) {
                Gui.drawRect(X - 1, Y - 1, X + longest + 2, Y, ColorUtility.getColor(300));
            }
        } else {
            for(Module m : enabledMods) {
                if(getFont().getStringWidth(m.getName()) > longest) {
                    longest = getFont().getStringWidth(m.getName());
                }
                Gui.drawRect(X-getFont().getStringWidth(m.getName())-1,offset,X-getFont().getStringWidth(m.getName())+getFont().getStringWidth(m.getName())+2,offset+10,new Color(0,0,0,153).getRGB());
                getFont().drawString(m.getName(),X-getFont().getStringWidth(m.getName()),offset+2, ColorUtility.getColor(3000));
                offset+=10;
            }
            if (wtf.norma.nekito.module.impl.Arraylist.line.isEnabled()){
            Gui.drawRect(X-longest-1,Y-1,X+2,Y,ColorUtility.getColor(3000));
            }
        }

        return  new Vector2f(longest,100);
    }
}