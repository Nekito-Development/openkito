package wtf.norma.nekito.util.Time;


import wtf.norma.nekito.util.Util;

public class TimerUtility implements Util {
    private static long lastTime;

    public void Timer() {
        reset();
    }

    public void reset() {
        lastTime = System.currentTimeMillis();
    }

    public  boolean hasReached(double milliseconds) {
        return ((System.currentTimeMillis() - lastTime) >= milliseconds);
    }




// we dont care

}
