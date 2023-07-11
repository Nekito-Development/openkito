package wtf.norma.nekito.helper.player;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.util.UUIDTypeAdapter;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

//author bettecraft
public final class PlayerUuidHelper {
    private static final String UUID_URL = "https://api.mojang.com/users/profiles/minecraft/%s?at=%d";
    private static final String NAME_URL = "https://api.mojang.com/user/profiles/%s/names";
    private static final Gson GSON = new GsonBuilder().registerTypeAdapter(UUID.class, new UUIDTypeAdapter()).create();

    private static final ExecutorService THREAD_POOL = Executors.newCachedThreadPool();

    private static final Map<String, UUID> uuidCache = new HashMap<>();
    private static final Map<UUID, String> nameCache = new HashMap<>();
    private String name;
    private UUID id;

    public static void getUUID(String name, Consumer<UUID> action) {
        THREAD_POOL.execute(() -> action.accept(PlayerUuidHelper.getUUID(name)));
    }

    public static UUID getUUID(String name) {
        return PlayerUuidHelper.getUUIDAt(name, System.currentTimeMillis());
    }

    public static void getUUIDAt(String name, long timestamp, Consumer<UUID> action) {
        THREAD_POOL.execute(() -> action.accept(PlayerUuidHelper.getUUIDAt(name, timestamp)));
    }

    public static UUID getUUIDAt(String name, long timestamp) {
        if (uuidCache.containsKey(name = name.toLowerCase())) {
            return uuidCache.get(name);
        }

        try (CloseableHttpClient client = HttpClients.createDefault(); CloseableHttpResponse response = client.execute(new HttpGet(String.format(UUID_URL, name, timestamp / 1000L)))) {
            if (response.getStatusLine().getStatusCode() != 200)
                throw new IOException("Server responded with: " + response.getStatusLine().getStatusCode());

            try (InputStream inputStream = response.getEntity().getContent()) {
                PlayerUuidHelper data = GSON.fromJson(IOUtils.toString(inputStream, StandardCharsets.UTF_8), PlayerUuidHelper.class);
                uuidCache.put(name, data.id);
                nameCache.put(data.id, data.name);
                return data.id;
            }
        } catch (Exception e) {
            return UUID.randomUUID();
        }
    }

    public static void getName(UUID uuid, Consumer<String> action) {
        THREAD_POOL.execute(() -> action.accept(PlayerUuidHelper.getName(uuid)));
    }

    public static String getName(UUID uuid) {
        if (nameCache.containsKey(uuid)) {
            return nameCache.get(uuid);
        }

        try (CloseableHttpClient client = HttpClients.createDefault(); CloseableHttpResponse response = client.execute(new HttpGet(String.format(NAME_URL, UUIDTypeAdapter.fromUUID(uuid))))) {
            if (response.getStatusLine().getStatusCode() != 200)
                throw new IOException("Server responded with: " + response.getStatusLine().getStatusCode());

            try (InputStream inputStream = response.getEntity().getContent()) {
                PlayerUuidHelper[] nameHistory = GSON.fromJson(IOUtils.toString(inputStream, StandardCharsets.UTF_8), PlayerUuidHelper[].class);
                PlayerUuidHelper currentNameData = nameHistory[nameHistory.length - 1];
                uuidCache.put(currentNameData.name.toLowerCase(), uuid);
                nameCache.put(uuid, currentNameData.name);
                return currentNameData.name;
            }
        } catch (Exception e) {
            return "Bert";
        }
    }
}
