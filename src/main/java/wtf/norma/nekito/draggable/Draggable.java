package wtf.norma.nekito.draggable;

import org.lwjgl.util.vector.Vector2f;

public interface Draggable {

    Vector2f EMPTY_SIZE = new Vector2f(0, 0);

    int getDraggableX();

    void setDraggableX(int x);

    int getDraggableY();

    void setDraggableY(int y);

    Vector2f getDraggableSize();

    void setDraggableSize(Vector2f size);
}
