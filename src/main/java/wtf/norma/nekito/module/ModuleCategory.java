package wtf.norma.nekito.module;

public enum ModuleCategory {

    MOVEMENT("Movement"), VISUALS("Visuals"), CRASHERS("Crashers");

    private final String name;

    ModuleCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
