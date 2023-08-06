package wtf.norma.nekito.module;

import wtf.norma.nekito.module.impl.*;

import java.util.ArrayList;
import java.util.Arrays;

public class ModuleManager {

    public static ArrayList<Module> modules = new ArrayList<>();

    public ModuleManager() {
        addAll(
                new Sprint(),
                new ClickGUI(),
                new Cape(),
                new FogColor(),
                new AntiVoid(),
                new TargetStrafe(),
                new ItemPhysics(),
                new NoFall(),
                new Fly(),
                new FreeCam(),
                new FastWorldLoad(),
                new NoSlowDown(),
                new HitBox(),
                new FullBright(),
                new CustomButtons(),
                new CustomModel(),
                new TargetHUD(),
                new Eagle(),
                new ServerInfo(),
                new Tickbase(),
                new HELIUMDDOS(),
                new Velocity(),
                new InventorySettings(),
                new eleczkamode(),
                new bandytakamera(),
                new NoClip(),
                new WorldColor(),
                new CrashGUI(),
                new Watermark(),
                new AirJump(),
                new Watermark2(),
                new Arraylist(),
                new AutoClicker(),
                new Timer(),
                new AimBot(),
                new NoWeather(),
                new Reach(),
                new CustomHotbar(),
                new Criticals(),
                new TimeChanger(),
                new Speed(),
                new Stealer(),
                new Wings(),
                new Ears(),
                new KillAura()
        );
    }

    public void addAll(Module... modules) {
        this.modules.addAll(
                Arrays.asList(modules));
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
