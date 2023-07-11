package wtf.norma.nekito.draggable;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

//@TODO Remove?
public class DraggableManager {

    private final List<Draggable> draggables = new LinkedList<>();

    public DraggableManager(Draggable... draggables) {
        register(draggables);
    }

    public void register(Draggable... draggables) {
        this.draggables.addAll(Arrays.asList(draggables));
    }

    public Optional<Draggable> findDraggable(Class<? extends Draggable> clazz) {
        return draggables.stream()
                .filter(draggable -> draggable.getClass().equals(clazz))
                .findFirst();
    }

    public List<Draggable> getDraggables() {
        return draggables;
    }
}
