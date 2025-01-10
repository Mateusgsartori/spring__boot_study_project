package rest_with_spring_boot_and_java.spring.util;

public class Utils {
    public static boolean isEmpty (String txt) {
        return txt == null || txt.isEmpty() || txt.isBlank();
    }
}
