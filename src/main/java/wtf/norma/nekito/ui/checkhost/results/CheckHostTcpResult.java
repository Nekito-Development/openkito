package wtf.norma.nekito.ui.checkhost.results;

public class CheckHostTcpResult {
    private final double ping;
    private final String address;
    private final String error;

    public CheckHostTcpResult(double ping, String address, String error) {
        this.ping = ping;
        this.address = address;
        this.error = error;
    }

    public String getAddress() {
        return this.address;
    }

    public double getPing() {
        return this.ping;
    }

    public String getError() {
        return this.error;
    }

    public boolean isSuccessful() {
        return this.error == null;
    }
}

