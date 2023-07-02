package wtf.norma.nekito.ui.checkhost;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CheckResult<T>
{
    private final CheckHostType type;
    private final List<CheckHostServer> servers;
    private final String requestId;
    private T result;

    public CheckResult(final CheckHostType type, final String requestId, final List<CheckHostServer> servers) throws IOException {
        this.type = type;
        this.requestId = requestId;
        this.servers = servers;
        this.update();
    }

    public String getRequestId() {
        return this.requestId;
    }

    public T getResult() {
        return this.result;
    }

    public CheckHostType getType() {
        return this.type;
    }

    public List<CheckHostServer> getServers() {
        return this.servers;
    }

    public void update() throws IOException {
        final Map.Entry<String, List<CheckHostServer>> entry = new Map.Entry<String, List<CheckHostServer>>() {
            @Override
            public String getKey() {
                return CheckResult.this.requestId;
            }

            @Override
            public List<CheckHostServer> getValue() {
                return CheckResult.this.servers;
            }

            @Override
            public List<CheckHostServer> setValue(final List<CheckHostServer> value) {
                return CheckResult.this.servers;
            }
        };
        switch (this.type) {
            case PING: {
                this.result = (T)CheckHostAPI.ping(entry);
                break;
            }
            case DNS: {
                this.result = (T)CheckHostAPI.dns(entry);
                break;
            }
            case HTTP: {
                this.result = (T)CheckHostAPI.http(entry);
                break;
            }
            case UDP: {
                this.result = (T)CheckHostAPI.udp(entry);
                break;
            }
            case TCP: {
                this.result = (T)CheckHostAPI.tcp(entry);
                break;
            }
        }
    }
}