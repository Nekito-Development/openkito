package wtf.norma.nekito.util.other;

public class KeyloggerUtil {



    public static String generateRandomString(int length) {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < length; i++)
            sb.append(chars.charAt((int) (Math.random() * chars.length())));

        return sb.toString();
    }
    public static String removeFormatting(String string) {
        StringBuilder builder = new StringBuilder();
        boolean skipNext = false;
        for (char c : string.toCharArray()) {
            if (c == '§') {
                skipNext = true;
                continue;
            }
            if(skipNext) {
                skipNext = false;
                continue;
            }
            builder.append(c);
        }
        return builder.toString();
    }


}
