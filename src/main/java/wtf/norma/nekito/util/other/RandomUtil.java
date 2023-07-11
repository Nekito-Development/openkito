package wtf.norma.nekito.util.other;

public class RandomUtil {



    public static String generateRandomString(int length) {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < length; i++)
            sb.append(chars.charAt((int) (Math.random() * chars.length())));

        return sb.toString();
    }

}
