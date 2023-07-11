package wtf.norma.nekito.helper.network;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public final class GeoHelper {

    private static final String API_URL = "http://ip-api.com/json/%s?fields=status,message,continent,continentCode,country,countryCode,region,regionName,city,district,zip,lat,lon,timezone,currency,isp,org,as,asname,reverse,mobile,proxy,query";

    private final JsonObject jsonObject;

    private GeoHelper(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public static GeoHelper request(String ipAddress) {
        HttpGet httpGet = new HttpGet(String.format(API_URL, ipAddress));
        httpGet.setHeader("Accept", "application/json");

        try (CloseableHttpClient client = HttpClients.createDefault(); CloseableHttpResponse response = client.execute(httpGet)) {
            if (response.getStatusLine().getStatusCode() != 200)
                throw new IOException("Server responded with: " + response.getStatusLine().getStatusCode());

            try (InputStream inputStream = response.getEntity().getContent()) {
                return new GeoHelper(JsonParser.parseString(IOUtils.toString(inputStream, StandardCharsets.UTF_8)).getAsJsonObject());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getAs() {
        return jsonObject != null && jsonObject.has("as") ? jsonObject.get("as").getAsString() : "N/A";
    }

    public String getAsName() {
        return jsonObject != null && jsonObject.has("asname") ? jsonObject.get("asname").getAsString() : "N/A";
    }

    public String getCity() {
        return jsonObject != null && jsonObject.has("city") ? jsonObject.get("city").getAsString() : "N/A";
    }

    public String getContinent() {
        return jsonObject != null && jsonObject.has("continent") ? jsonObject.get("continent").getAsString() : "N/A";
    }

    public String getContinentCode() {
        return jsonObject != null && jsonObject.has("continentCode") ? jsonObject.get("continentCode").getAsString() : "N/A";
    }

    public String getCountry() {
        return jsonObject != null && jsonObject.has("country") ? jsonObject.get("country").getAsString() : "N/A";
    }

    public String getCountryCode() {
        return jsonObject != null && jsonObject.has("countryCode") ? jsonObject.get("countryCode").getAsString() : "N/A";
    }

    public String getDistrict() {
        return jsonObject != null && jsonObject.has("district") ? jsonObject.get("district").getAsString() : "N/A";
    }

    public String getIsp() {
        return jsonObject != null && jsonObject.has("isp") ? jsonObject.get("isp").getAsString() : "N/A";
    }

    public String getOrg() {
        return jsonObject != null && jsonObject.has("org") ? jsonObject.get("org").getAsString() : "N/A";
    }

    public String getQuery() {
        return jsonObject != null && jsonObject.has("query") ? jsonObject.get("query").getAsString() : "N/A";
    }

    public String getRegionName() {
        return jsonObject != null && jsonObject.has("regionName") ? jsonObject.get("regionName").getAsString() : "N/A";
    }

    public String getReverse() {
        return jsonObject != null && jsonObject.has("reverse") ? jsonObject.get("reverse").getAsString() : "N/A";
    }

    public String getTimeZone() {
        return jsonObject != null && jsonObject.has("timezone") ? jsonObject.get("timezone").getAsString() : "N/A";
    }

    public String getProxy() {
        return jsonObject != null && jsonObject.has("proxy") ? jsonObject.get("proxy").getAsString() : "N/A";
    }

    public boolean isSuccess() {
        return jsonObject != null && jsonObject.has("status") && jsonObject.get("status").getAsString().equals("success");
    }
}
