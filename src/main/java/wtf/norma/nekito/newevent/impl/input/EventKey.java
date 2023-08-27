package wtf.norma.nekito.newevent.impl.input;

import lombok.Getter;


public class EventKey {
    @Getter
    private final int key;
    //Code optimization blabla
    public EventKey(final int key) {
        this.key = key;
    }
}
