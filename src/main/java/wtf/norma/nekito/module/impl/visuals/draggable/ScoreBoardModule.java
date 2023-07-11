package wtf.norma.nekito.module.impl.visuals.draggable;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.util.vector.Vector2f;
import rip.hippo.lwjeb.annotation.Handler;
import wtf.norma.nekito.draggable.Draggable;
import wtf.norma.nekito.event.impl.EventRender2D;
import wtf.norma.nekito.helper.font.FontHelper;
import wtf.norma.nekito.helper.render.RenderHelper;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.module.ModuleCategory;
import wtf.norma.nekito.module.ModuleInfo;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

@ModuleInfo(
        name = "ScoreBoard",
        moduleCategory = ModuleCategory.VISUALS
)
public class ScoreBoardModule extends Module implements Draggable {
    private int x = 5, y = 70;
    private Vector2f size = Draggable.EMPTY_SIZE;

    private ScoreObjective scoreObjective;

    @Handler
    public void onRender(EventRender2D event) {
        if (scoreObjective != null) {
            net.minecraft.scoreboard.Scoreboard scoreboard = scoreObjective.getScoreboard();
            Collection collection = scoreboard.getSortedScores(scoreObjective);
            ArrayList arraylist = Lists.newArrayList(Iterables.filter(collection, new Predicate() {
                public boolean apply(Score p_apply_1_) {
                    return p_apply_1_.getPlayerName() != null && !p_apply_1_.getPlayerName().startsWith("#");
                }

                public boolean apply(Object p_apply_1_) {
                    return this.apply((Score) p_apply_1_);
                }
            }));
            ArrayList arraylist1;

            if (arraylist.size() > 15) {
                arraylist1 = Lists.newArrayList(Iterables.skip(arraylist, collection.size() - 15));
            } else {
                arraylist1 = arraylist;
            }


            int i = FontHelper.SEMI_BOLD_18.getStringWidth(scoreObjective.getDisplayName());

            for (Object score0 : arraylist1) {
                Score score = (Score) score0;
                ScorePlayerTeam scoreplayerteam = scoreboard.getPlayersTeam(score.getPlayerName());
                String s = ScorePlayerTeam.formatPlayerName(scoreplayerteam, score.getPlayerName()) + ": " + EnumChatFormatting.RED + score.getScorePoints();
                i = Math.max(i, FontHelper.SEMI_BOLD_18.getStringWidth(s));
            }

            int i1 = collection.size() * FontHelper.SEMI_BOLD_18.getStringHeight("siurek");
            int j1 = event.getScaledResolution().getScaledHeight() / 2 + i1 / 3;
            int k1 = 3;
            int l1 = event.getScaledResolution().getScaledWidth() - i - k1 - (event.getScaledResolution().getScaledWidth() + 300);

            float xd = getDraggableY();
            float offset = getDraggableY();

            int longest = 0;
            float foundSize = 0;

            for (Object score10 : arraylist1) {
                Score score1 = (Score) score10;

                ScorePlayerTeam scoreplayerteam1 = scoreboard.getPlayersTeam(score1.getPlayerName());
                String s1 = ScorePlayerTeam.formatPlayerName(scoreplayerteam1, score1.getPlayerName());

                if (FontHelper.SEMI_BOLD_18.getStringWidth(s1) > longest) {
                    longest = FontHelper.SEMI_BOLD_18.getStringWidth(s1);
                }
                foundSize += FontHelper.SEMI_BOLD_18.getStringHeight(s1) + 2;
            }
            longest += 8;

            RenderHelper.drawRect(getDraggableX(), getDraggableY(), getDraggableX() + longest, getDraggableY() + foundSize, new Color(0, 0, 0, 153).getRGB());

            for (Object score10 : arraylist1) {
                Score score1 = (Score) score10;

                ScorePlayerTeam scoreplayerteam1 = scoreboard.getPlayersTeam(score1.getPlayerName());
                String s1 = ScorePlayerTeam.formatPlayerName(scoreplayerteam1, score1.getPlayerName());
                String s2 = EnumChatFormatting.RED + String.valueOf(score1.getScorePoints());
                int k = (int) (j1 - (xd / 12) * FontHelper.SEMI_BOLD_18.getStringHeight("siurek"));
                int l = event.getScaledResolution().getScaledWidth() - k1 + 2;

                FontHelper.SEMI_BOLD_18.drawString(s1, getDraggableX() + 5, offset + 5, -1);
                offset += FontHelper.SEMI_BOLD_18.getStringHeight(s1) + 2;
            }

            setDraggableSize(new Vector2f(longest, foundSize));
        }
        setDraggableSize(new Vector2f(0, 0));
    }

    public ScoreObjective getScoreObjective() {
        return scoreObjective;
    }

    public void setScoreObjective(ScoreObjective scoreObjective) {
        this.scoreObjective = scoreObjective;
    }

    @Override
    public void onDisable() {
        this.size = Draggable.EMPTY_SIZE;
    }

    @Override
    public int getDraggableX() {
        return x;
    }

    @Override
    public void setDraggableX(int x) {
        this.x = x;
    }

    @Override
    public int getDraggableY() {
        return y;
    }

    @Override
    public void setDraggableY(int y) {
        this.y = y;
    }

    @Override
    public Vector2f getDraggableSize() {
        return size;
    }

    @Override
    public void setDraggableSize(Vector2f size) {
        this.size = size;
    }
}