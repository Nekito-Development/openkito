package org.json.data;

import java.net.URL;
import java.util.Scanner;

public class JSON {

    public static String getZipCode(String content_url, String code, String content) throws Exception {
        URL url = new URL(content_url);
        Scanner scanner = new Scanner(url.openStream());
        StringBuffer stringBuffer = new StringBuffer();
        while (scanner.hasNext()) stringBuffer.append(scanner.nextLine());
        JSONObject jsonObject = JSONObject.parse(stringBuffer.toString());

        switch (code.toLowerCase()) {
            case "string":
                return jsonObject.getString(content);

            case "int":
                return "" + jsonObject.getInt(content);

            case "newobject":
                String[] contents = content.split(";");
                JSONObject jsonObject1 = jsonObject.getJSONObject(contents[0]);
                return jsonObject1.getString(contents[1]);

            case "boolean":
                return "" + jsonObject.getBoolean(content);

            case "long":
                try {
                    return "" + jsonObject.getLong(content);
                } catch (Exception e) {
                    return "Ping is Null, please try again later";
                }
        }
        return null;
    }
}
