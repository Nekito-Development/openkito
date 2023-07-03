package wtf.norma.nekito.draggable.impl;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.util.vector.Vector2f;
import wtf.norma.nekito.draggable.AbstractDraggable;
import wtf.norma.nekito.util.font.Fonts;
import wtf.norma.nekito.util.render.RenderUtility;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class Scoreboard extends AbstractDraggable {
    public ScoreObjective p_180475_1_;
    public ScaledResolution p_180475_2_;

    @Override
    public void Init() {
        X = 5;
        Y = 70;
    }

    @Override
    public Vector2f Render() {
        if(p_180475_1_ != null) {
            net.minecraft.scoreboard.Scoreboard scoreboard = p_180475_1_.getScoreboard();
            Collection collection = scoreboard.getSortedScores(p_180475_1_);
            ArrayList arraylist = Lists.newArrayList(Iterables.filter(collection, new Predicate()
            {
                private static final String __OBFID = "CL_00001958";
                public boolean apply(Score p_apply_1_)
                {
                    return p_apply_1_.getPlayerName() != null && !p_apply_1_.getPlayerName().startsWith("#");
                }
                public boolean apply(Object p_apply_1_)
                {
                    return this.apply((Score)p_apply_1_);
                }
            }));
            ArrayList arraylist1;

            if (arraylist.size() > 15)
            {
                arraylist1 = Lists.newArrayList(Iterables.skip(arraylist, collection.size() - 15));
            }
            else
            {
                arraylist1 = arraylist;
            }
            

            int i = (int) Fonts.SEMI_BOLD_18.getStringWidth(p_180475_1_.getDisplayName());

            for (Object score0 : arraylist1)
            {
                Score score = (Score) score0;
                ScorePlayerTeam scoreplayerteam = scoreboard.getPlayersTeam(score.getPlayerName());
                String s = ScorePlayerTeam.formatPlayerName(scoreplayerteam, score.getPlayerName()) + ": " + EnumChatFormatting.RED + score.getScorePoints();
                i = (int) Math.max(i, Fonts.SEMI_BOLD_18.getStringWidth(s));
            }

            int i1 = (int) (collection.size() * Fonts.SEMI_BOLD_18.getStringHeight("siurek"));
            int j1 = p_180475_2_.getScaledHeight() / 2 + i1 / 3;
            int k1 = 3;
            int l1 = p_180475_2_.getScaledWidth() - i - k1 - (p_180475_2_.getScaledWidth()+300);

            float xd = Y;
            float offset = Y;

            int longest = 0;
            float foundSize = 0;

            for(Object score10 : arraylist1) {
                Score score1 = (Score) score10;

                ScorePlayerTeam scoreplayerteam1 = scoreboard.getPlayersTeam(score1.getPlayerName());
                String s1 = ScorePlayerTeam.formatPlayerName(scoreplayerteam1, score1.getPlayerName());

                if(Fonts.SEMI_BOLD_18.getStringWidth(s1) > longest) {
                    longest = (int) Fonts.SEMI_BOLD_18.getStringWidth(s1);
                }
                foundSize+=Fonts.SEMI_BOLD_18.getStringHeight(s1)+2;
            }
            longest+=8;

            RenderUtility.drawRect(X,Y,X+longest,Y+foundSize,new Color(0,0,0,153).getRGB());

            for(Object score10 : arraylist1) {
                Score score1 = (Score) score10;

                ScorePlayerTeam scoreplayerteam1 = scoreboard.getPlayersTeam(score1.getPlayerName());
                String s1 = ScorePlayerTeam.formatPlayerName(scoreplayerteam1, score1.getPlayerName());
                String s2 = EnumChatFormatting.RED + "" + score1.getScorePoints();
                int k = (int) (j1 - (xd/12) * Fonts.SEMI_BOLD_18.getStringHeight("siurek"));
                int l = p_180475_2_.getScaledWidth() - k1 + 2;

                Fonts.SEMI_BOLD_18.drawString(s1,X+5,offset+5,-1);
                offset+=Fonts.SEMI_BOLD_18.getStringHeight(s1)+2;
            }

            return new Vector2f(longest,foundSize);
        }
        return new Vector2f(0,0);
    }
}
