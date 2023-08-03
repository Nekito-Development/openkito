package wtf.norma.nekito.module;

import de.gerrygames.viarewind.utils.ChatUtil;
import net.minecraft.client.Minecraft;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.helper.ChatHelper;
import wtf.norma.nekito.nekito;
import wtf.norma.nekito.settings.Setting;

import java.util.ArrayList;
import java.util.Arrays;

public class Module {
    public String name;
    public int keybind;
    public boolean toggled;
    public boolean enabled;
    public Category category;





    public final static Minecraft mc = Minecraft.getMinecraft();

    public ArrayList<Setting> settings = new ArrayList<Setting>();
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
            enabled = true;
            onEnable();
        } else {
            enabled = false;
            onDisable();
        }
    }

    public void addSettings(Setting... settings) {
        this.settings.addAll(Arrays.asList(settings));
    }

    public enum Category {
        LEGIT ("Legit"), MOVEMENT("Movement"), VISUALS("Visuals"),OTHER("Other"),CRASHERS("Crashers");

        public String name;

        Category(String name) {
            this.name = name;
        }
    }
}
