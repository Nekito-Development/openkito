package wtf.norma.nekito.helper.network.checkhost.request;

public enum CheckHostRequestType {

    HTTP("http"), TCP("tcp");

    private final String type;

    CheckHostRequestType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
