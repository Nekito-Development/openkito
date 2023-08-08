package wtf.norma.nekito.ui.config;
import java.io.File;
import java.util.Iterator;
import com.google.gson.JsonObject;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.nekito;

import java.io.File;
import java.util.Iterator;

/*
public final class Config implements ConfigUpdater {
    private final String name;
    private final File file;

    public Config(String name) {
        this.name = name;
        this.file = new File(ConfigManager.configDirectory, name + ".kuszko.to.cwel");
        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
            } catch (Exception var3) {
            }
        }

    }

    public File getFile() {
        return this.file;
    }

    public String getName() {
        return this.name;
    }

    public JsonObject save() {
        JsonObject jsonObject = new JsonObject();
        JsonObject modulesObject = new JsonObject();
        new JsonObject();
        Iterator var4 = nekito.INSTANCE.getModuleManager().getModules().iterator();

        // getAllFeatures().iterator();

        while(var4.hasNext()) {
            Module module = (Module)var4.next();
            modulesObject.add(module.getName(), module.save());
        }

        jsonObject.add("Modules", modulesObject);
        return jsonObject;
    }

    /*
    public void load(JsonObject object) {
        if (object.has("Features")) {
            JsonObject modulesObject = object.getAsJsonObject("Features");
            Iterator var3 = nekito.INSTANCE.getModuleManager().getModules().iterator();

            while(var3.hasNext()) {
                Module module = (Module)var3.next();
                module.setToggled(false);
                module.load(modulesObject.getAsJsonObject(module.getName()));
            }
        }
}

     */



