package wtf.norma.nekito.module;

import wtf.norma.nekito.module.value.Value;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ModuleManager {

    private final List<Module> modules = new ArrayList<>();

    public ModuleManager(Module... modules) {
        register(modules);
    }

    public void register(Module... modules) {
        this.modules.addAll(Arrays.asList(modules));
    }

    public Optional<Module> findModule(String moduleName) {
        return modules.stream()
                .filter(module -> module.getName().equalsIgnoreCase(moduleName))
                .findFirst();
    }

    public Optional<Module> findModule(Class<? extends Module> clazz) {
        return modules.stream()
                .filter(module -> module.getClass().equals(clazz))
                .findFirst();
    }

    public void registerValues() {
        modules.forEach(this::registerValues);
    }

    public void registerValues(Module module) {
        for (Field field : module.getClass().getDeclaredFields()) {
            if (!Value.class.isAssignableFrom(field.getType()))
                continue;

            try {
                Value<?> value = (Value<?>) field.get(module);
                if (value == null)
                    continue;

                module.getValues().put(value.getName(), value);
            } catch (Exception ignored) {
            }
        }
    }

    public Optional<Value<?>> findValue(Module module, String name) {
        if (module.getValues().containsKey(name))
            return Optional.ofNullable(module.getValues().get(name));

        return module.getValues().values().stream().filter(value -> value.getName().equalsIgnoreCase(name)).findFirst();
    }

    public List<Module> getModules() {
        return modules;
    }
}
