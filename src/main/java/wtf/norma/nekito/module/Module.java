package wtf.norma.nekito.module;

import net.minecraft.client.Minecraft;
import org.apache.commons.lang3.Validate;
import wtf.norma.nekito.Nekito;
import wtf.norma.nekito.module.value.Value;

import java.util.HashMap;
import java.util.Map;

public abstract class Module {

    protected final static Minecraft mc = Minecraft.getMinecraft();
    private final Map<String, Value<?>> values = new HashMap<>();

    private final String name;
    private final String description;
    private final ModuleCategory category;
    private int key;
    private boolean enabled;

    public Module() {
        ModuleInfo moduleInfo = this.getClass().getAnnotation(ModuleInfo.class);
        Validate.notNull(moduleInfo, "CONFUSED ANNOTATION EXCEPTION");

        this.name = moduleInfo.name();
        this.description = moduleInfo.description();
        this.category = moduleInfo.moduleCategory();
        this.key = moduleInfo.key();
    }

    public void onEnable() {

    }

    public void onDisable() {

    }

    public void setState(boolean state) {
        enabled = state;
        if (state) {
            onEnable();
            Nekito.INSTANCE.getEventBus().subscribe(this);
        } else {
            onDisable();
            Nekito.INSTANCE.getEventBus().unsubscribe(this);
        }
    }

    public final void toggle() {
        enabled = !enabled;
        if (enabled) {
            onEnable();
            Nekito.INSTANCE.getEventBus().subscribe(this);
        } else {
            onDisable();
            Nekito.INSTANCE.getEventBus().unsubscribe(this);
        }
    }

    public Map<String, Value<?>> getValues() {
        return values;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ModuleCategory getCategory() {
        return category;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
