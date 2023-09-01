package wtf.norma.nekito.util.other;

public class HoveringUtil {

    //it shoudl be in math but who cares
    public static boolean isHovering(float x, float y, float width, float height, int mouseX, int mouseY) {
        return (float)mouseX >= x && (float)mouseY >= y && (float)mouseX < x + width && (float)mouseY < y + height;
    }
}
