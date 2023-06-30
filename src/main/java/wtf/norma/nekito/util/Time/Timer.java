package wtf.norma.nekito.util.Time;


public class Timer {
    private static long lastTime;

    public void Timer() {
        reset();
    }

    public void reset() {
        this.lastTime = System.currentTimeMillis();
    }

    public static boolean hasReached(double milliseconds) {
        return ((System.currentTimeMillis() - lastTime) >= milliseconds);
    }

    public long getLastTime() {
        return lastTime;
    }
}
