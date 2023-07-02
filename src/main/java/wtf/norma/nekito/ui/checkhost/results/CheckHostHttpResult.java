package wtf.norma.nekito.ui.checkhost.results;

public class CheckHostHttpResult {
    private final String status;
    private final String address;
    private final double ping;
    private final int errorCode;

    public CheckHostHttpResult(String status, double ping, String address, int errorCode) {
        this.status = status;
        this.ping = ping;
        this.address = address;
        this.errorCode = errorCode;
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

    public int getErrorCode() {
        return this.errorCode;
    }

    public boolean isSuccessful() {
        return this.status != null && this.status.equalsIgnoreCase("OK");
    }
}

