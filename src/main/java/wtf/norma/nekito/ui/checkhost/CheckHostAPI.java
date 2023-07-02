package wtf.norma.nekito.ui.checkhost;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import wtf.norma.nekito.ui.checkhost.results.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class CheckHostAPI {
    public static CheckResult<Map<CheckHostServer, CheckHostPingResult>> createPingRequest(String host, int maxNodes) throws IOException {
        Map.Entry<String, List<CheckHostServer>> entry = CheckHostAPI.sendCheckHostRequest(CheckHostType.PING, host, maxNodes);
        return new CheckResult<Map<CheckHostServer, CheckHostPingResult>>(CheckHostType.PING, entry.getKey(), entry.getValue());
    }

    public static CheckResult<Map<CheckHostServer, CheckHostTcpResult>> createTcpRequest(String host, int maxNodes) throws IOException {
        Map.Entry<String, List<CheckHostServer>> entry = CheckHostAPI.sendCheckHostRequest(CheckHostType.TCP, host, maxNodes);
        return new CheckResult<Map<CheckHostServer, CheckHostTcpResult>>(CheckHostType.TCP, entry.getKey(), entry.getValue());
    }

    public static CheckResult<Map<CheckHostServer, CheckHostUdpResult>> createUdpRequest(String host, int maxNodes) throws IOException {
        Map.Entry<String, List<CheckHostServer>> entry = CheckHostAPI.sendCheckHostRequest(CheckHostType.UDP, host, maxNodes);
        return new CheckResult<Map<CheckHostServer, CheckHostUdpResult>>(CheckHostType.UDP, entry.getKey(), entry.getValue());
    }

    public static CheckResult<Map<CheckHostServer, CheckHostHttpResult>> createHttpRequest(String host, int maxNodes) throws IOException {
        Map.Entry<String, List<CheckHostServer>> entry = CheckHostAPI.sendCheckHostRequest(CheckHostType.HTTP, host, maxNodes);
        return new CheckResult<Map<CheckHostServer, CheckHostHttpResult>>(CheckHostType.HTTP, entry.getKey(), entry.getValue());
    }

    public static CheckResult<Map<CheckHostServer, CheckHostDnsResult>> createDnsRequest(String host, int maxNodes) throws IOException {
        Map.Entry<String, List<CheckHostServer>> entry = CheckHostAPI.sendCheckHostRequest(CheckHostType.DNS, host, maxNodes);
        return new CheckResult<Map<CheckHostServer, CheckHostDnsResult>>(CheckHostType.DNS, entry.getKey(), entry.getValue());
    }

    private static JsonObject performGetRequest(String url) throws IOException {
        HttpURLConnection con = (HttpURLConnection)new URL(url).openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:71.0) Gecko/20100101 Firefox/71.0");
        con.setRequestProperty("Accept", "application/json");
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String json = "";
        String line = null;
        while ((line = br.readLine()) != null) {
            json = json + line + System.lineSeparator();
        }
        br.close();
        JsonObject main = new JsonParser().parse(json).getAsJsonObject();
        con.disconnect();
        return main;
    }

    private static Map.Entry<String, List<CheckHostServer>> sendCheckHostRequest(CheckHostType type, String host, int maxNodes) throws IOException {
        final JsonObject main = CheckHostAPI.performGetRequest("https://check-host.net/check-" + type.getValue() + "?host=" + URLEncoder.encode(host, "UTF-8") + "&max_nodes=" + maxNodes);
        if (!main.has("nodes")) {
            throw new IOException("Invalid response!");
        }
        final ArrayList<CheckHostServer> servers = new ArrayList<CheckHostServer>();
        JsonObject nodes = main.get("nodes").getAsJsonObject();
        for (Map.Entry entry : nodes.entrySet()) {
            JsonArray list = ((JsonElement)entry.getValue()).getAsJsonArray();
            ArrayList<String> infos = new ArrayList<String>();
            if (list.size() > 3) {
                for (int i = 3; i < list.size(); ++i) {
                    infos.add(list.get(i).getAsString());
                }
            }
            servers.add(new CheckHostServer((String)entry.getKey(), list.get(1).getAsString(), list.get(0).getAsString(), list.get(2).getAsString(), infos));
        }
        return new Map.Entry<String, List<CheckHostServer>>(){

            @Override
            public String getKey() {
                return main.get("request_id").getAsString();
            }

            @Override
            public List<CheckHostServer> getValue() {
                return servers;
            }

            @Override
            public List<CheckHostServer> setValue(List<CheckHostServer> value) {
                return servers;
            }
        };
    }

    static Map<CheckHostServer, CheckHostPingResult> ping(Map.Entry<String, List<CheckHostServer>> input) throws IOException {
        String id = input.getKey();
        List<CheckHostServer> servers = input.getValue();
        JsonObject main = CheckHostAPI.performGetRequest("https://check-host.net/check-result/" + URLEncoder.encode(id, "UTF-8"));
        HashMap<CheckHostServer, CheckHostPingResult> result = new HashMap<CheckHostServer, CheckHostPingResult>();
        for (int i = 0; i < servers.size(); ++i) {
            CheckHostServer server = servers.get(i);
            if (!main.has(server.getName()) || main.get(server.getName()).isJsonNull()) continue;
            JsonArray ja = main.get(server.getName()).getAsJsonArray();
            for (int k = 0; k < ja.size(); ++k) {
                JsonElement elmt = ja.get(k);
                if (!elmt.isJsonArray()) continue;
                JsonArray ja2 = elmt.getAsJsonArray();
                ArrayList<CheckHostPingResult.PingEntry> pEntries = new ArrayList<CheckHostPingResult.PingEntry>();
                for (int j = 0; j < ja2.size(); ++j) {
                    if (!ja2.get(j).isJsonArray()) continue;
                    JsonArray ja3 = ja2.get(j).getAsJsonArray();
                    if (ja3.size() != 2 && ja3.size() != 3) {
                        pEntries.add(new CheckHostPingResult.PingEntry("Unable to resolve domain name.", -1.0, null));
                        continue;
                    }
                    String status = ja3.get(0).getAsString();
                    double ping = ja3.get(1).getAsDouble();
                    String addr = null;
                    if (ja3.size() > 2) {
                        addr = ja3.get(2).getAsString();
                    }
                    CheckHostPingResult.PingEntry pEntry = new CheckHostPingResult.PingEntry(status, ping, addr);
                    pEntries.add(pEntry);
                }
                result.put(server, new CheckHostPingResult(pEntries));
            }
        }
        return result;
    }

    static Map<CheckHostServer, CheckHostTcpResult> tcp(Map.Entry<String, List<CheckHostServer>> input) throws IOException {
        String id = input.getKey();
        List<CheckHostServer> servers = input.getValue();
        JsonObject main = CheckHostAPI.performGetRequest("https://check-host.net/check-result/" + URLEncoder.encode(id, "UTF-8"));
        HashMap<CheckHostServer, CheckHostTcpResult> result = new HashMap<CheckHostServer, CheckHostTcpResult>();
        for (int i = 0; i < servers.size(); ++i) {
            CheckHostServer server = servers.get(i);
            JsonArray ja = null;
            if (!main.has(server.getName()) || main.get(server.getName()).isJsonNull() || (ja = main.get(server.getName()).getAsJsonArray()).size() != 1) continue;
            JsonObject obj = ja.get(0).getAsJsonObject();
            String error = null;
            if (obj.has("error")) {
                error = obj.get("error").getAsString();
            }
            String addr = null;
            if (obj.has("address")) {
                addr = obj.get("address").getAsString();
            }
            double ping = 0.0;
            if (obj.has("time")) {
                ping = obj.get("time").getAsDouble();
            }
            CheckHostTcpResult res = new CheckHostTcpResult(ping, addr, error);
            result.put(server, res);
        }
        return result;
    }

    static Map<CheckHostServer, CheckHostUdpResult> udp(Map.Entry<String, List<CheckHostServer>> input) throws IOException {
        String id = input.getKey();
        List<CheckHostServer> servers = input.getValue();
        JsonObject main = CheckHostAPI.performGetRequest("https://check-host.net/check-result/" + URLEncoder.encode(id, "UTF-8"));
        HashMap<CheckHostServer, CheckHostUdpResult> result = new HashMap<CheckHostServer, CheckHostUdpResult>();
        for (int i = 0; i < servers.size(); ++i) {
            CheckHostServer server = servers.get(i);
            JsonArray ja = null;
            if (!main.has(server.getName()) || main.get(server.getName()).isJsonNull() || (ja = main.get(server.getName()).getAsJsonArray()).size() != 1) continue;
            JsonObject obj = ja.get(0).getAsJsonObject();
            String error = null;
            if (obj.has("error")) {
                error = obj.get("error").getAsString();
            }
            String addr = null;
            if (obj.has("address")) {
                addr = obj.get("address").getAsString();
            }
            double ping = 0.0;
            if (obj.has("time")) {
                ping = obj.get("time").getAsDouble();
            }
            double timeout = 0.0;
            if (obj.has("timeout")) {
                timeout = obj.get("timeout").getAsDouble();
            }
            CheckHostUdpResult res = new CheckHostUdpResult(timeout, ping, addr, error);
            result.put(server, res);
        }
        return result;
    }

    static Map<CheckHostServer, CheckHostHttpResult> http(Map.Entry<String, List<CheckHostServer>> input) throws IOException {
        String id = input.getKey();
        List<CheckHostServer> servers = input.getValue();
        JsonObject main = CheckHostAPI.performGetRequest("https://check-host.net/check-result/" + URLEncoder.encode(id, "UTF-8"));
        HashMap<CheckHostServer, CheckHostHttpResult> result = new HashMap<CheckHostServer, CheckHostHttpResult>();
        for (int i = 0; i < servers.size(); ++i) {
            int error;
            CheckHostServer server = servers.get(i);
            JsonArray ja = null;
            if (!main.has(server.getName()) || main.get(server.getName()).isJsonNull() || (ja = main.get(server.getName()).getAsJsonArray()).size() != 1) continue;
            ja = ja.get(0).getAsJsonArray();
            double ping = ja.get(1).getAsDouble();
            String status = ja.get(2).getAsString();
            int n = error = ja.size() > 3 && ja.get(3).isJsonPrimitive() ? ja.get(3).getAsInt() : -1;
            if (error == -1) continue;
            String addr = ja.size() > 4 && ja.get(4).isJsonPrimitive() ? ja.get(4).getAsString() : null;
            CheckHostHttpResult res = new CheckHostHttpResult(status, ping, addr, error);
            result.put(server, res);
        }
        return result;
    }

    static Map<CheckHostServer, CheckHostDnsResult> dns(Map.Entry<String, List<CheckHostServer>> input) throws IOException {
        String id = input.getKey();
        List<CheckHostServer> servers = input.getValue();
        JsonObject main = CheckHostAPI.performGetRequest("https://check-host.net/check-result/" + URLEncoder.encode(id, "UTF-8"));
        HashMap<CheckHostServer, CheckHostDnsResult> result = new HashMap<CheckHostServer, CheckHostDnsResult>();
        for (int i = 0; i < servers.size(); ++i) {
            CheckHostServer server = servers.get(i);
            JsonArray ja = null;
            if (!main.has(server.getName()) || main.get(server.getName()).isJsonNull() || (ja = main.get(server.getName()).getAsJsonArray()).size() != 1) continue;
            JsonObject obj = ja.get(0).getAsJsonObject();
            HashMap<String, String[]> domainInfos = new HashMap<String, String[]>();
            for (Map.Entry entry : obj.entrySet()) {
                if (((String)entry.getKey()).equals("TTL") || !((JsonElement)entry.getValue()).isJsonArray()) continue;
                JsonArray ja2 = ((JsonElement)entry.getValue()).getAsJsonArray();
                String[] values = new String[ja2.size()];
                for (int k = 0; k < ja2.size(); ++k) {
                    if (!ja2.get(k).isJsonPrimitive()) continue;
                    values[k] = ja2.get(k).getAsString();
                }
                domainInfos.put((String)entry.getKey(), values);
            }
            CheckHostDnsResult res = new CheckHostDnsResult(obj.has("TTL") && obj.get("TTL").isJsonPrimitive() ? obj.get("TTL").getAsInt() : -1, domainInfos);
            result.put(server, res);
        }
        return result;
    }
}
