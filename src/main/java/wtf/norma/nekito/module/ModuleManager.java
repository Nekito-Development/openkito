package wtf.norma.nekito.module;

import wtf.norma.nekito.module.impl.*;

import java.util.ArrayList;

public class ModuleManager {

    public ArrayList<Module> modules = new ArrayList<>();

    public ModuleManager() {
        addAll(
                new Sprint(),
                new ClickGUI(),
                new Cape(),
                new ItemPhysics(),
                new Fly()
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

    public <T extends Module> T getModule(Class<T> clas) {
        return (T) getModules().stream().filter(module -> module.getClass() == clas).findFirst().orElse(null);
    }


    public ArrayList<Module> getModules() {
        return modules;
    }
}
