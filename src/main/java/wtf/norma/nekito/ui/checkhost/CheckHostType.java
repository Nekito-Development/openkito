package wtf.norma.nekito.ui.checkhost;

public enum CheckHostType {
    PING("ping"),
    TCP("tcp"),
    UDP("udp"),
    HTTP("http"),
    DNS("dns");

    private final String value;

    private CheckHostType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}

