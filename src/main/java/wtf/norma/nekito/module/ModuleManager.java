package wtf.norma.nekito.module;

import wtf.norma.nekito.module.impl.Sprint;

import java.util.ArrayList;

public class ModuleManager {

    public ArrayList<Module> modules = new ArrayList<>();

    public ModuleManager() {
        initModules();
    }

    public void initModules() {
        modules.add(new Sprint());
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
