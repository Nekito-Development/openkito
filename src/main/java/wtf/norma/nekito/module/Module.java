package wtf.norma.nekito.module;

import net.minecraft.client.Minecraft;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.nekito;

public class Module {
    public String name;
    public int keybind;
    public boolean toggled;
    public Category category;

    public final static Minecraft mc = Minecraft.getMinecraft();

    public Module(String name, Category category, int keybind) {
        this.name = name;
        this.category = category;
        this.keybind = keybind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getKeybind() {
        return keybind;
    }

    public void setKeybind(int keybind) {
        this.keybind = keybind;
    }

    public boolean isToggled() {
        return toggled;
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;
    }

    public void onEnable() {

    }

    public void onDisable() {

    }

    public void onEvent(Event e) {

    }

    public void toggle() {
        toggled = !toggled;

        if (toggled) {
            onEnable();
        } else {
            onDisable();
        }
    }

    public enum Category {
        MOVEMENT("Movement"), CRASHER("Crasher");

        public String name;

        Category(String name) {
            this.name = name;
        }
    }
}
