package wtf.norma.nekito.util.other;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

//author bettercraft



public class GeoUtils {

    private static GeoUtils instance;


    public JsonObject object;

    public String server = "";



    public static GeoUtils getInstance() {
        return instance;
    }




    public GeoUtils(String ip) {
        instance = this;
        this.server = "http://ip-api.com/json/" + ip
                + "?fields=status,message,continent,continentCode,country,countryCode,region,regionName,city,district,zip,lat,lon,timezone,currency,isp,org,as,asname,reverse,mobile,proxy,query";
        this.object = new JsonParser().parse(this.websitedata(this.server)).getAsJsonObject();
    }







    public String getAS() {
        return this.getObjectString("as");
    }

    public String getASNAME() {
        return this.getObjectString("asname");
    }

    public String getCITY() {
        return this.getObjectString("city");
    }

    public String getCONTINENT() {
        return this.getObjectString("continent");
    }

    public String getCONTINENTCODE() {
        return this.getObjectString("continentCode");
    }

    public String getCOUNTRY() {
        return this.getObjectString("country");
    }

    public String getCOUNTRYCODE() {
        return this.getObjectString("countryCode");
    }

    public String getDISTRICT() {
        return this.getObjectString("district");
    }

    public String getISP() {
        return this.getObjectString("isp");
    }

    public String getORG() {
        return this.getObjectString("org");
    }

    public String getQUERY() {
        return this.getObjectString("query");
    }

    public String getREGIONNAME() {
        return this.getObjectString("regionName");
    }

    public String getREVERSE() {
        return this.getObjectString("reverse");
    }

    public String getTIMEZONE() {
        return this.getObjectString("timezone");
    }

    public String getPROXY() {
        return this.getObjectString("proxy");
    }







    public boolean isSuccess() {
        return this.getObjectString("status").equals("success");
    }

    public boolean isRight(String obj) {
        return !obj.isEmpty() && !obj.contains("Unknown");
    }





    public String getObjectString(String obj) {
        try {
            return this.object.get(obj).getAsString();
        } catch (Exception e) {
            return "Pinging...";
        }
    }

    public String websitedata(String website) {
        try {
            String content;
            URL url = new URL(website);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            content = format(bufferedReader.lines().collect(Collectors.toList()));
            bufferedReader.close();
            return content;
        } catch (Exception e) {
            return null;
        }
    }

    private String format(List<String> arrayList) {
        String out = "";
        for (String entry : arrayList) {
            out += entry + "\n";
        }
        return out;
    }
}


