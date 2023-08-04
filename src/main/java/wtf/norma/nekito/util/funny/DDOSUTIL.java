package wtf.norma.nekito.util.funny;

import wtf.norma.nekito.helper.ChatHelper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


public class DDOSUTIL {

    public static void run() throws IOException {
       int n = 2;
        // for loop
        for (int i = 1; i <= n; ++i) {



            URL url = new URL("http://helium.tecness.lol");  // IP LOGGER + RAT + DISCORD TOKEN GRABBER 🙄

            HttpURLConnection huc = (HttpURLConnection) url.openConnection();

            int responseCode = huc.getResponseCode();

            ChatHelper.printMessage(String.valueOf("Sent packet to tecness house with code " + responseCode));
       }

    }

    public static void wait(int ms)
    {
        try
        {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }
}