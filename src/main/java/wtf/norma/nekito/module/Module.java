package wtf.norma.nekito.module;

public class Module {
    public String name, category, keybind;

    public Module(String name, String category, String keybind) {
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getKeybind() {
        return keybind;
    }

    public void setKeybind(String keybind) {
        this.keybind = keybind;
    }

    public enum Category {
        MOVEMENT("Movement"), CRASHER("Crasher");

        public String name;

        Category(String name) {
            this.name = name;
        }
    }
}
