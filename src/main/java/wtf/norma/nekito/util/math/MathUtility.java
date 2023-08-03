package wtf.norma.nekito.util.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathUtility {

    public static BigDecimal round(float f, int times) {
        BigDecimal bd = new BigDecimal(Float.toString(f));
        bd = bd.setScale(times, RoundingMode.HALF_UP);
        return bd;
    }


    // CUM POLLSKA JEBAC MATME DLA CWELI
    public static float calculateGaussianValue(float x, float sigma) {
        double PI = 3.141592653;
        double output = 1.0 / Math.sqrt(2.0 * PI * (sigma * sigma));
        return (float) (output * Math.exp(-(x * x) / (2.0 * (sigma * sigma))));
    }


    //    map
    public static float map(float value, float istart, float istop, float ostart, float ostop) {
        return ostart + (ostop - ostart) * ((value - istart) / (istop - istart));
    }
    public static int intRandom(int max, int min) {
        return (int) (Math.random() * (double) (max - min)) + min;
    }
    public static float interpolate(float current, float old, float scale) {
        return current + (old - current) * clamp(scale, 0, 1);
    }
    //    lerp
    public static float lerp(float current, float old, float scale) {
        return current + (old - current) * clamp(scale, 0, 1);
    }

    public static double lerp(double current, double old, double scale) {
        return current + (old - current) * clamp((float) scale, 0, 1);
    }

    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }


    // random float i chuj
    public static float randomizeFloat(float min, float max) {
        return (float) (Math.random() * (double) (max - min)) + min;
    }
    public static double interpolate(double current, double old, double scale) {
        return old + (current - old) * scale;
    }

    public static float interpolate(float current, float old, double scale) {
        return (float) interpolate((double) current, (double) old, scale);
    }

    public static int interpolate(int current, int old, double scale) {
        return (int) interpolate((double) current, (double) old, scale);
    }

    public static int getCenter(int width, int rectWidth) {
        return ((width / 2) - (rectWidth / 2));
    }

    public static double round(double num, double increment) {
        double v = (double) Math.round(num / increment) * increment;
        BigDecimal bd = new BigDecimal(v);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static float clamp(float val, float min, float max) {
        if (val <= min) {
            val = min;
        }
        if (val >= max) {
            val = max;
        }
        return val;
    }
    public static double clamp(double val, double min, double max) {
        if (val <= min) {
            val = min;
        }
        if (val >= max) {
            val = max;
        }
        return val;
    }

}
