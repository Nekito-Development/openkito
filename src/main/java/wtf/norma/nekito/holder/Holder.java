package wtf.norma.nekito.holder;

import wtf.norma.nekito.helper.TimeHelper;

import java.util.ArrayList;
import java.util.List;

public class Holder {

    private static final TimeHelper TIME_HELPER = new TimeHelper(); //for tps calculation
    private static final List<Long> TPS_TIMES = new ArrayList<>();
    private static long LAST_PACKET_MS = -1;
    private static double TPS = -1;

    public static TimeHelper getTimeHelper() {
        return TIME_HELPER;
    }

    public static List<Long> getTpsTimes() {
        return TPS_TIMES;
    }

    public static long getLastPacketMS() {
        return LAST_PACKET_MS;
    }

    public static void setLastPacketMS(long lastPacketMS) {
        Holder.LAST_PACKET_MS = lastPacketMS;
    }

    public static double getTPS() {
        return TPS;
    }

    public static void setTPS(double TPS) {
        Holder.TPS = TPS;
    }
}
