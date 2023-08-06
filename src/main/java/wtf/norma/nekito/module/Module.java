package wtf.norma.nekito.module;

import com.google.gson.JsonObject;
import de.gerrygames.viarewind.utils.ChatUtil;
import net.minecraft.client.Minecraft;
import wtf.norma.nekito.event.Event;
import wtf.norma.nekito.helper.ChatHelper;
import wtf.norma.nekito.nekito;
import wtf.norma.nekito.settings.Setting;
import wtf.norma.nekito.settings.impl.BooleanSetting;
import wtf.norma.nekito.settings.impl.ModeSetting;
import wtf.norma.nekito.settings.impl.NumberSetting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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


    public final List<Setting> getSettings() {
        return this.settings;
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


    public void load(JsonObject object) {
        if (object != null) {
            if (object.has("state")) {
                this.setToggled(object.get("state").getAsBoolean());
            }
            if (object.has("keyIndex")) {
                this.setKeybind(object.get("keyIndex").getAsInt());
            }
            for (Setting set : settings) {
                JsonObject propertiesObject = object.getAsJsonObject("Settings");
                if (set == null)
                    continue;
                if (propertiesObject == null)
                    continue;
                if (!propertiesObject.has(set.getName()))
                    continue;
                if (set instanceof BooleanSetting) {
                    ((BooleanSetting) set).setBoolValue(propertiesObject.get(set.getName()).getAsBoolean());
                } else if (set instanceof ModeSetting) {
                    ((ModeSetting) set).setMode(propertiesObject.get(set.getName()).getAsString());
                } else if (set instanceof NumberSetting) {
                    ((NumberSetting) set).setValueNumber(propertiesObject.get(set.getName()).getAsFloat());
                }
            }
        }
    }




    public JsonObject save() {
        JsonObject object = new JsonObject();
        object.addProperty("state", isToggled());
        object.addProperty("keyIndex", getKeybind());
        JsonObject propertiesObject = new JsonObject();
        for (Setting set : this.getSettings()) {
            if (this.getSettings() != null) {
                if (set instanceof BooleanSetting) {
                    propertiesObject.addProperty(set.getName(), ((BooleanSetting) set).value);
                } else if (set instanceof ModeSetting) {
                    propertiesObject.addProperty(set.getName(), ((ModeSetting) set).getMode());
                } else if (set instanceof NumberSetting) {
                    propertiesObject.addProperty(set.getName(), ((NumberSetting) set).getValue());
                }
            }
            object.add("Settings", propertiesObject);
        }
        return object;
    }



    public void addSettings(Setting... settings) {
        this.settings.addAll(Arrays.asList(settings));
    }

    public enum Category {
        COMBAT("Combat"), LEGIT ("Legit"), MOVEMENT("Movement"), VISUALS("Visuals"),OTHER("Other"),CRASHERS("Crashers List");

        public String name;

        Category(String name) {
            this.name = name;
        }
    }
}
