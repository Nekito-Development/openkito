package wtf.norma.nekito.cosmetics;

public class Animation {
    public long time = System.nanoTime() / 1000000L;

    public boolean hasTimeElapsed() {
        if (getTime() >= 10L) {
            reset();
            return true;
        }
        return false;
    }

    public long getTime() {
        return System.nanoTime() / 1000000L - this.time;
    }

    public void reset() {
        this.time = System.nanoTime() / 1000000L;
    }
}
