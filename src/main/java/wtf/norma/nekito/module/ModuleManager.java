package wtf.norma.nekito.module;

import wtf.norma.nekito.module.impl.*;

import java.util.ArrayList;
import java.util.Arrays;

public class ModuleManager {

    public ArrayList<Module> modules = new ArrayList<>();

    public ModuleManager() {
        addAll(
                new Sprint(),
                new ClickGUI(),
                new Cape(),
                new ItemPhysics(),
                new Fly(),
                new NoSlowDown(),
                new HitBox(),
                new FullBright(),
                new CustomButtons(),
                new CustomModel(),
                new ServerInfo(),
                new WorldColor(),
                new Watermark(),
                new Arraylist(),
                new AutoClicker(),
                new AimBot(),
                new NoWeather(),
                new Reach(),
                new CustomHotbar(),
                new Wings(),
                new Ears(),
                new KillAura()
        );
    }

    public void addAll(Module... modules) {
        this.modules.addAll(Arrays.asList(modules));
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
