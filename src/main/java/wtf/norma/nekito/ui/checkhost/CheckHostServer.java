package wtf.norma.nekito.ui.checkhost;

import java.util.List;

public class CheckHostServer {
    private final String name;
    private final String country;
    private final String countryCode;
    private final String city;
    private final List<String> infos;

    public CheckHostServer(String name, String country, String countryCode, String city, List<String> infos) {
        this.name = name;
        this.country = country;
        this.countryCode = countryCode;
        this.city = city;
        this.infos = infos;
    }

    public String getCity() {
        return this.city;
    }

    public String getCountry() {
        return this.country;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public String getName() {
        return this.name;
    }

    public List<String> getInfos() {
        return this.infos;
    }
}

