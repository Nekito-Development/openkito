package wtf.norma.nekito.registry.impl;

import lombok.Getter;
import net.minecraft.network.Packet;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ServerPacketRegistry {

    @Getter
    public static ConcurrentHashMap<String, Class<Packet>> entries = new ConcurrentHashMap<>();

    @Getter
    public static List<String> names;
    @Getter
    public static List<Class<Packet>> clazzez;

    public ServerPacketRegistry() {
        init();
    }

    private void init() {

        names = getPacketNames();
        clazzez = getPacketClazzez();

        for (int i = 0; i < names.size(); i++) {
                for (int j = 0; j < clazzez.size(); j++) {
                    if (clazzez.get(i).getSimpleName().equalsIgnoreCase(names.get(j))) {
                        entries.put(names.get(j), clazzez.get(i));
                    }
                }
            }


    }


    public List<String> getPacketNames() {
        Reflections reflections = new Reflections("net.minecraft.network.play.server", new SubTypesScanner(false));
        List<Class> temp1 =  reflections.getSubTypesOf(Object.class)
                .stream()
                .collect(Collectors.toList());
        ArrayList<String> names = new ArrayList<>();
        temp1.stream().forEach(aClass -> names.add(aClass.getSimpleName()));
        return names.stream().filter(name ->
                        !name.equalsIgnoreCase("Extracted") &&
                        !name.equalsIgnoreCase("Snapshot") &&
                        !name.equalsIgnoreCase("AddPlayerData") &&
                        !name.equalsIgnoreCase("BlockUpdateData") &&
                        !name.equalsIgnoreCase(""))
                .collect(Collectors.toList());
    }

    public List<Class<Packet>> getPacketClazzez() {
        Reflections reflections = new Reflections("net.minecraft.network.play.server", new SubTypesScanner(false));
        List<Class<? extends Packet>> temp =  reflections.getSubTypesOf(Packet.class)
                .stream()
                .collect(Collectors.toList());
        ArrayList<Class<Packet>> clazzez = new ArrayList();
        temp.stream().forEach(aClass -> clazzez.add((Class<Packet>) aClass));
        return clazzez;
    }


    public static void main(String[] args) {
        ServerPacketRegistry registry = new ServerPacketRegistry();
        System.out.println(registry.clazzez.size());
        System.out.println(registry.getPacketNames());

        registry.entries.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));
    }

}


