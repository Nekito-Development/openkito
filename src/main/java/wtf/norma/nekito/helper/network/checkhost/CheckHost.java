package wtf.norma.nekito.helper.network.checkhost;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import wtf.norma.nekito.helper.network.checkhost.request.CheckHostRequestType;
import wtf.norma.nekito.helper.network.checkhost.result.CheckHostHttpResult;
import wtf.norma.nekito.helper.network.checkhost.result.CheckHostTcpResult;
import wtf.norma.nekito.helper.network.checkhost.server.CheckHostServer;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class CheckHost {

    private final Map<String, CheckHostServer> servers = new HashMap<>();

    private final String ipAddress;

    private String requestId;

    public CheckHost(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    private void requestServers(CheckHostRequestType requestType) {
        servers.clear();

        HttpGet httpGet = new HttpGet(String.format(CheckHostConstants.NODES_API_URL, requestType.getType(), ipAddress));
        httpGet.setHeader("Accept", "application/json");

        try (CloseableHttpClient client = HttpClients.createDefault(); CloseableHttpResponse response = client.execute(httpGet)) {
            if (response.getStatusLine().getStatusCode() != 200)
                throw new IOException("Server responded with: " + response.getStatusLine().getStatusCode());

            try (InputStream inputStream = response.getEntity().getContent()) {
                JsonObject jsonElement = JsonParser.parseString(IOUtils.toString(inputStream, StandardCharsets.UTF_8)).getAsJsonObject();
                JsonObject nodes = jsonElement.get("nodes").getAsJsonObject();

                nodes.entrySet().forEach(entry -> {
                    JsonArray server = entry.getValue().getAsJsonArray();
                    servers.put(entry.getKey(),
                            new CheckHostServer(
                                    server.get(0).getAsString(),
                                    server.get(1).getAsString(),
                                    server.get(2).getAsString(),
                                    server.get(3).getAsString(),
                                    server.get(4).getAsString())
                    );
                });
                requestId = jsonElement.get("request_id").getAsString();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Map<CheckHostServer, CheckHostHttpResult> http() {
        Map<CheckHostServer, CheckHostHttpResult> result = new HashMap<>();

        requestServers(CheckHostRequestType.HTTP);
        HttpGet httpGet = new HttpGet(String.format(CheckHostConstants.RESULT_API_URL, requestId));
        httpGet.setHeader("Accept", "application/json");

        try (CloseableHttpClient client = HttpClients.createDefault(); CloseableHttpResponse response = client.execute(httpGet)) {
            if (response.getStatusLine().getStatusCode() != 200)
                throw new IOException("Server responded with: " + response.getStatusLine().getStatusCode());

            try (InputStream inputStream = response.getEntity().getContent()) {
                JsonObject jsonElement = JsonParser.parseString(IOUtils.toString(inputStream, StandardCharsets.UTF_8)).getAsJsonObject();
                jsonElement.entrySet().forEach(entry -> {
                    if (entry.getValue().isJsonNull())
                        return;

                    JsonElement array = entry.getValue().getAsJsonArray().get(0);
                    if (array.isJsonNull())
                        return;

                    JsonArray status = array.getAsJsonArray();
                    result.put(servers.get(entry.getKey()), new CheckHostHttpResult(
                            status.get(0).getAsInt(),
                            status.get(1).getAsDouble(),
                            status.get(2).getAsString(),
                            status.get(3).isJsonNull() ? "" : status.get(3).getAsString(),
                            status.get(4).isJsonNull() ? "" : status.get(4).getAsString()
                    ));
                });
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public Map<CheckHostServer, CheckHostTcpResult> tcp() {
        Map<CheckHostServer, CheckHostTcpResult> result = new HashMap<>();

        requestServers(CheckHostRequestType.TCP);
        HttpGet httpGet = new HttpGet(String.format(CheckHostConstants.RESULT_API_URL, requestId));
        httpGet.setHeader("Accept", "application/json");

        try (CloseableHttpClient client = HttpClients.createDefault(); CloseableHttpResponse response = client.execute(httpGet)) {
            if (response.getStatusLine().getStatusCode() != 200)
                throw new IOException("Server responded with: " + response.getStatusLine().getStatusCode());

            try (InputStream inputStream = response.getEntity().getContent()) {
                JsonObject jsonElement = JsonParser.parseString(IOUtils.toString(inputStream, StandardCharsets.UTF_8)).getAsJsonObject();
                jsonElement.entrySet().forEach(entry -> {
                    if (entry.getValue().isJsonNull())
                        return;

                    JsonObject jsonObject = entry.getValue().getAsJsonArray().get(0).getAsJsonObject();
                    if (jsonObject.has("error")) {
                        result.put(servers.get(entry.getKey()), new CheckHostTcpResult(null, -1, jsonObject.get("error").getAsString()));
                    } else {
                        result.put(servers.get(entry.getKey()), new CheckHostTcpResult(
                                jsonObject.get("address").getAsString(),
                                jsonObject.get("time").getAsDouble(),
                                null
                        ));
                    }
                });
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
