package wtf.norma.nekito.draggable;

import net.minecraft.client.Minecraft;
import org.lwjgl.util.vector.Vector2f;

public abstract class AbstractDraggable {
    public Minecraft mc = Minecraft.getMinecraft();
    public boolean AllowRender = false;
    public int X = 0;
    public int Y = 0;
    public Vector2f Size;

    public void Init() {

    }

    /*
    * Render loop
    * Return value is using to calculate draggable size to make it movable
    * @author Tecness
    * */
    public abstract Vector2f Render();
}
