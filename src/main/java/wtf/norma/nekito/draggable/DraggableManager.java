package wtf.norma.nekito.draggable;

import org.lwjgl.util.vector.Vector2f;
import wtf.norma.nekito.draggable.impl.Watermark;

import java.util.LinkedList;

public class DraggableManager {

    //made by Tecnessino
    public LinkedList<AbstractDraggable> DraggableList = new LinkedList<>();

    public DraggableManager() {
        Add(new Watermark());
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
            }
        }
    }
}
