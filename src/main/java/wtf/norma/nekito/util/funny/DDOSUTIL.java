package wtf.norma.nekito.util.funny;

import wtf.norma.nekito.helper.ChatHelper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class DDOSUTIL {

    public static void run(String pizda) throws IOException {
            URL url = new URL(pizda);  // IP LOGGER + RAT + DISCORD TOKEN GRABBER 🙄
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            int responseCode = huc.getResponseCode();
            ChatHelper.printMessage("Sent packet to " + pizda + " Response: " + responseCode);
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