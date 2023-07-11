package wtf.norma.nekito.util.other;

public class MyszaUtil {

    public static boolean isHovered(double mouseX, double mouseY, double x, double y, double width, double height) {
        return mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + height;
    }

}
