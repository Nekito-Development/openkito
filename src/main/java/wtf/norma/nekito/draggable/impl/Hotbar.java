package wtf.norma.nekito.draggable.impl;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.util.vector.Vector2f;
import wtf.norma.nekito.draggable.AbstractDraggable;
import wtf.norma.nekito.helper.OpenGlHelper;
import wtf.norma.nekito.module.impl.CustomHotbar;
import wtf.norma.nekito.util.font.Fonts;
import wtf.norma.nekito.util.render.RenderUtility;

import java.awt.*;


public class Hotbar extends AbstractDraggable {
    ScaledResolution sr = new ScaledResolution(mc);
    public int index;

    @Override
    public void Init() {
        //Initable position
        X = 0;
        Y = 0;
    }

    //<inheritdoc /> (From AbstractDraggable)
    @Override
    public Vector2f Render() {
        GlStateManager.pushMatrix();
        GlStateManager.translate(X, Y, 1);

        RenderUtility.drawGradientRound(0, 0, 180, 20, 5, new Color(1, 111, 255, 130), new Color(0, 225, 255, 130), new Color(112, 114, 255, 130), new Color(128, 32, 231, 130));
        RenderUtility.drawRound(mc.thePlayer.inventory.currentItem * 20, 0, 20, 20, 5, new Color(255, 255, 255, 100));

        for (int j = 0; j < 9; ++j) {
            mc.ingameGUI.renderHotbarItem(j, j * 20 + 2, 2, mc.timer.renderPartialTicks, mc.thePlayer);
        }

        GlStateManager.popMatrix();
        return new Vector2f(180, 20);
    }
}
