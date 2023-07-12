package wtf.norma.nekito.util.Animations;

public class EasingHelper {


    public static double easeOutQuart(float n) {
        return 1.0 - Math.pow(1.0f - n, 4.0);
    }


}
