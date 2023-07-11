package wtf.norma.nekito.helper.network.checkhost.result;

public class CheckHostHttpResult {

    private final int result;
    private final double time;
    private final String message;
    private final String code;
    private final String ipAddress;

    public CheckHostHttpResult(int result, double time, String message, String code, String ipAddress) {
        this.result = result;
        this.time = time;
        this.message = message;
        this.code = code;
        this.ipAddress = ipAddress;
    }

    public int getResult() {
        return result;
    }

    public double getTime() {
        return time;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    @Override
    public String toString() {
        return "CheckHostHttpResult{" +
                "result='" + result + '\'' +
                ", time=" + time +
                ", message='" + message + '\'' +
                ", code='" + code + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                '}';
    }
}
