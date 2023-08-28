package wtf.norma.nekito.draggable.impl;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.util.vector.Vector2f;
import wtf.norma.nekito.draggable.AbstractDraggable;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.Nekito;
import wtf.norma.nekito.module.impl.hud.UiSettings;
import wtf.norma.nekito.util.color.ColorUtility;
import wtf.norma.nekito.util.font.FontRenderer;
import wtf.norma.nekito.util.font.Fonts;
import wtf.norma.nekito.util.render.BlurUtility;
import wtf.norma.nekito.util.render.RenderUtility;
import wtf.norma.nekito.util.shader.ShaderUtility;

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

    public FontRenderer getFont() {
         switch(wtf.norma.nekito.module.impl.hud.Arraylist.fonts.getMode()){
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


        boolean lowercase;
        lowercase = wtf.norma.nekito.module.impl.hud.Arraylist.lowerCase.isEnabled();



        for(Module m : Nekito.INSTANCE.getModuleManager().getModules())
            if(m.isToggled())
                enabledMods.add(m);

        enabledMods.sort((m1,m2) -> getFont().getStringWidth(m2.getName()) - getFont().getStringWidth(m1.getName()));



        int offset = Y;
        if(X < sr.getScaledWidth()/2.0f) {
            for(Module m : enabledMods) {

                String cwel = m.getName();
                if (wtf.norma.nekito.module.impl.hud.Arraylist.lowerCase.isEnabled()){
                    cwel = cwel.toLowerCase();
                }

                if(getFont().getStringWidth(cwel) > longest) {
                    longest = getFont().getStringWidth(cwel);
                }
                if (UiSettings.fpseating.isEnabled()) {
                    RenderUtility.drawBlurredShadow(X - 2, Y + (float) offset - 3.5f + 2, getFont().getStringWidth(cwel) + 5f, 10, 5, RenderUtility.glAlpha(new Color(ColorUtility.getColor(0)), 150)); // draws that "glow"
                }

                Gui.drawRect(X-1,offset,X+getFont().getStringWidth(cwel)+2,offset+10,new Color(0,0,0,153).getRGB()); // draws background


                getFont().drawString(cwel,X,offset+2, ColorUtility.getColor(3000));
                offset+=10;

            }
            if (wtf.norma.nekito.module.impl.hud.Arraylist.line.isEnabled()) {

                    Gui.drawRect(X - 1, Y - 1, X + longest + 2, Y, ColorUtility.getColor(300));



            }
        } else {
            for(Module m : enabledMods) {
                String cwel = m.getName();
                if (wtf.norma.nekito.module.impl.hud.Arraylist.lowerCase.isEnabled()){
                    cwel = cwel.toLowerCase();
                }
                if(getFont().getStringWidth(cwel) > longest) {
                    longest = getFont().getStringWidth(cwel);
                }
                Gui.drawRect(X-getFont().getStringWidth(cwel)-1,offset,X-getFont().getStringWidth(cwel)+getFont().getStringWidth(cwel)+2,offset+10,new Color(0,0,0,153).getRGB());
                getFont().drawString(cwel,X-getFont().getStringWidth(cwel),offset+2, ColorUtility.getColor(3000));
                offset+=10;
            }
            if (wtf.norma.nekito.module.impl.hud.Arraylist.line.isEnabled()){


                    Gui.drawRect(X-longest-1,Y-1,X+2,Y,ColorUtility.getColor(3000));

            }
        }

        return  new Vector2f(longest,100);
    }
}