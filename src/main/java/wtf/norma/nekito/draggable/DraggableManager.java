package wtf.norma.nekito.draggable;

import net.minecraft.client.gui.Gui;
import org.lwjgl.util.vector.Vector2f;
import wtf.norma.nekito.draggable.impl.Arraylist;
import wtf.norma.nekito.draggable.impl.Scoreboard;
import wtf.norma.nekito.draggable.impl.ServerInfo;
import wtf.norma.nekito.draggable.impl.Watermark;

import java.util.LinkedList;

public class DraggableManager {

    //made by Tecnessino
    public LinkedList<AbstractDraggable> DraggableList = new LinkedList<>();

    public DraggableManager() {

    }

    public void Init() {
        Add(new Watermark());
        Add(new Arraylist());
        Add(new ServerInfo());

        for(AbstractDraggable draggable : DraggableList) {
            draggable.Init();
        }
    }

    public void Add(AbstractDraggable Draggable) {
        DraggableList.add(Draggable);
    }
    public <T extends AbstractDraggable> T Get(String name) {
        return (T) DraggableList.stream().filter(manager -> manager.getClass().getSimpleName().equalsIgnoreCase(name)).findFirst().get();
    }
    public void Render() {
        for(AbstractDraggable draggable : DraggableList) {
            if(draggable.AllowRender) {
                Vector2f size = draggable.Render();
                draggable.Size = size;
            } else {
                draggable.Size = new Vector2f(0,0);
            }
            if(draggable.getClass().getSimpleName().equalsIgnoreCase("Arraylist")) {
           //     System.out.println(draggable.X + " " + draggable.Y + " " + draggable.Size.x + " " + draggable.Size.y);

                //Gui.drawRect(draggable.X,draggable.Y, (int) (draggable.X+draggable.Size.x), (int) (draggable.Y+draggable.Size.y),-1);
            }
        }
    }
}
