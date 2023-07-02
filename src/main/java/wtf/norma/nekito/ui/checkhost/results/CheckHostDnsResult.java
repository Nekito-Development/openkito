package wtf.norma.nekito.ui.checkhost.results;


import java.util.Map;

public class CheckHostDnsResult {
    private final int ttl;
    private final Map<String, String[]> result;

    public CheckHostDnsResult(int ttl, Map<String, String[]> result) {
        this.ttl = ttl;
        this.result = result;
    }

    public Map<String, String[]> getResult() {
        return this.result;
    }

    public int getTTL() {
        return this.ttl;
    }

    public boolean isSuccessful() {
        return this.ttl >= 0;
    }
}

