package wtf.norma.nekito.ui.botting;

import net.minecraft.util.EnumChatFormatting;

public class Botter extends Thread {
    private final String ip;
    private final String PORT;
    private String status;

    public Botter(String ip, String PORT) {
        super("Alt Login Thread");
        this.ip = ip;
        this.PORT = PORT;
        status = EnumChatFormatting.GRAY + "Waiting";
    }

    public static void start(String IP, String port) throws Exception {
        Runtime.getRuntime().exec("cmd.exe /c java -jar bots.jar -s  " + IP + ":" + port + "  -p NEKI_ -d 4000 5000 -c 30 -r");
        Runtime.getRuntime().exec("cmd.exe /c java -jar bots.jar -s  " + IP + ":" + port + "  -p NEKI_ -d 4000 5000 -c 30 -r");
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
// java -jar bots.jar -s 192.168.0.189:25565 -p NEKITO_ -d 4000 5000 -c 30 -r