package wtf.norma.nekito.ui.checkhost.results;

import java.util.List;

public class CheckHostPingResult {
    private final List<PingEntry> pingEntries;

    public CheckHostPingResult(List<PingEntry> pingEntries) {
        this.pingEntries = pingEntries;
    }

    public List<PingEntry> getPingEntries() {
        return this.pingEntries;
    }

    public boolean isSuccessful() {
        return this.pingEntries != null && !this.pingEntries.isEmpty();
    }

    public static class PingEntry {
        private final String status;
        private final double ping;
        private final String address;

        public PingEntry(String status, double ping, String address) {
            this.status = status;
            this.ping = ping;
            this.address = address;
        }

        public String getAddress() {
            return this.address;
        }

        public double getPing() {
            return this.ping;
        }

        public String getStatus() {
            return this.status;
        }

        public boolean isSuccessful() {
            return this.status != null && this.status.equalsIgnoreCase("OK") && this.ping >= 0.0;
        }
    }
}

