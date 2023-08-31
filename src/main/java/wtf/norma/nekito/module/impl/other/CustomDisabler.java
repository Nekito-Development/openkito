package wtf.norma.nekito.module.impl.other;

import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import me.zero.alpine.listener.Subscriber;
import org.lwjgl.input.Keyboard;
import wtf.norma.nekito.event.impl.packet.PacketEvent;
import wtf.norma.nekito.module.Module;
import wtf.norma.nekito.registry.impl.ServerPacketRegistry;
import wtf.norma.nekito.settings.impl.BooleanSetting;

import java.util.ArrayList;


public class CustomDisabler extends Module implements Subscriber {

    private final ArrayList<BooleanSetting> settingsList = generateSettings();

    public CustomDisabler() {
        super("Cum Disabler", Category.OTHER, Keyboard.KEY_NONE);
        this.addSettings(settingsList);
    }

    public ArrayList<BooleanSetting> generateSettings() {
        try {
            ArrayList<BooleanSetting> temp = new ArrayList<>();
            ServerPacketRegistry.names.forEach(name -> temp.add(new BooleanSetting(name, false)));
            return temp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    @Subscribe
    private final Listener<PacketEvent> listener = new Listener<>(event -> {

        this.settingsList.forEach(setting -> {
            if (event.getPacket().equals(ServerPacketRegistry.entries.get(setting.getName())) && setting.isEnabled()) event.setCancelled(true);
        });
        
    });

}
