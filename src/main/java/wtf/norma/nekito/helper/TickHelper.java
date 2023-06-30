package wtf.norma.nekito.helper;

public class TickHelper {
    private long tick;

    public void update() {
        ++this.tick;
    }

    public void reset() {
        this.tick = 0L;
    }

    public boolean hasTimePassed(long ticks) {
        return this.tick >= ticks;
    }

    public long get() {
        return this.tick;
    }
}
