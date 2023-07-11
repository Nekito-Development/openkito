package wtf.norma.nekito.helper.network.checkhost.result;

public class CheckHostTcpResult {

    private final String ipAddress;
    private final double time;
    private final String error;

    public CheckHostTcpResult(String ipAddress, double time, String error) {
        this.ipAddress = ipAddress;
        this.time = time;
        this.error = error;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public double getTime() {
        return time;
    }

    public String getError() {
        return error;
    }

    @Override
    public String toString() {
        return "CheckHostTcpResult{" +
                "ipAddress='" + ipAddress + '\'' +
                ", time=" + time +
                ", error='" + error + '\'' +
                '}';
    }
}
