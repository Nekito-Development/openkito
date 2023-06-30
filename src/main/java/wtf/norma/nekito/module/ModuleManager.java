package wtf.norma.nekito.module;

import wtf.norma.nekito.module.impl.Cape;
import wtf.norma.nekito.module.impl.ClickGUI;

import wtf.norma.nekito.module.impl.Sprint;

import java.util.ArrayList;

public class ModuleManager {

    public ArrayList<Module> modules = new ArrayList<>();

    public ModuleManager() {
        addAll(
                new Sprint(),
                new ClickGUI(),
                new Cape()
        );
    }

    public void addAll(Module... modules) {
        for (Module m : modules) {
            this.modules.add(m);
        }
    }

    public Module getModuleByName(String name) {
        for (Module m : modules) {
            if (m.getName().equalsIgnoreCase(name)) {
                return m;
            }
        }
        return null;
    }

    public ArrayList<Module> getModules() {
        return modules;
    }
}
