package wtf.norma.nekito.helper.network.checkhost.server;

public class CheckHostServer {

    private final String code;
    private final String country;
    private final String city;
    private final String ip;
    private final String info;

    public CheckHostServer(String code, String country, String city, String ip, String info) {
        this.code = code;
        this.country = country;
        this.city = city;
        this.ip = ip;
        this.info = info;
    }

    public String getCountry() {
        return country;
    }

    public String getCode() {
        return code;
    }

    public String getCity() {
        return city;
    }

    public String getIp() {
        return ip;
    }

    public String getInfo() {
        return info;
    }


    @Override
    public String toString() {
        return "CheckHostServer{" +
                "code='" + code + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", ip='" + ip + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
