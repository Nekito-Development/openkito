package wtf.norma.nekito.module.impl.visuals.draggable;

import net.minecraft.client.gui.Gui;
import org.lwjgl.util.vector.Vector2f;
import rip.hippo.lwjeb.annotation.Handler;
import wtf.norma.nekito.Nekito;
import wtf.norma.nekito.draggable.Draggable;
import wtf.norma.nekito.event.impl.EventRender2D;
import wtf.norma.nekito.helper.OpenGlHelper;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.module.ModuleCategory;
import wtf.norma.nekito.module.ModuleInfo;
import wtf.norma.nekito.module.value.impl.BooleanValue;
import wtf.norma.nekito.util.font.Fonts;

import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

@ModuleInfo(
        name = "ModuleList",
        moduleCategory = ModuleCategory.VISUALS
)
public class ModuleListModule extends Module implements Draggable {

    private static final int BACKGROUND = new Color(10, 10, 10, 100).getRGB();

    public final BooleanValue background = new BooleanValue("Background", true);
    private final AtomicInteger longest = new AtomicInteger();
    private int x = 5, y = 5;
    private Vector2f size = Draggable.EMPTY_SIZE;

    @Handler
    public void onRender(EventRender2D event) {
        final boolean state = getDraggableX() < event.getScaledResolution().getScaledWidth() / 2.0f;
        final AtomicInteger offset = new AtomicInteger(y);

        Nekito.INSTANCE.getModuleManager().getModules().stream().filter(Module::isEnabled).sorted((firstModule, secondModule) -> Fonts.SEMI_BOLD_16.getStringWidth(secondModule.getName()) - Fonts.SEMI_BOLD_16.getStringWidth(firstModule.getName())).forEach(module -> {
            float width = Fonts.SEMI_BOLD_16.getStringWidth(module.getName());
            if (width > longest.get()) longest.set((int) width);

            int moduleOffset = offset.getAndUpdate(operand -> operand + 10);
            int color = OpenGlHelper.rainbowColor(3000, 1 + moduleOffset * 22);

            if (background.get()) {
                float height = Fonts.SEMI_BOLD_16.getStringHeight(module.getName());
                if (state) {
                    Gui.drawRect(getDraggableX() - 1, moduleOffset, (int) (getDraggableX() + width + 2), (int) (moduleOffset + 4 + height), BACKGROUND);
                } else {
                    Gui.drawRect((int) (getDraggableX() - width - 1), moduleOffset, getDraggableX() + 2, (int) (moduleOffset + 4 + height), BACKGROUND);
                }
            }

            Fonts.SEMI_BOLD_16.drawString(module.getName(), state ? getDraggableX() : getDraggableX() - width, moduleOffset + 2, color);
        });

        if (state) {
            Gui.drawRect(getDraggableX() - 1, getDraggableY() - 1, getDraggableX() + longest.get() + 2, getDraggableY(), OpenGlHelper.rainbowColor(3000, 23));
        } else {
            Gui.drawRect(getDraggableX() - longest.get() - 1, getDraggableY() - 1, getDraggableX() + 2, getDraggableY(), OpenGlHelper.rainbowColor(3000, 23));
        }

        setDraggableSize(new Vector2f(longest.get(), 100));
        longest.set(0);
    }

    public AtomicInteger getLongest() {
        return longest;
    }

    @Override
    public void onDisable() {
        size = Draggable.EMPTY_SIZE;
        longest.set(0);
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
