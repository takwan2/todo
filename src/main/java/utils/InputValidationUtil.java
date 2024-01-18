package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidationUtil {

    public static boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }


    public static boolean isValidDateFormat(String dateStr) {
        String regex = "\\d{4}-\\d{2}-\\d{2}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(dateStr);

        return matcher.matches();
    }
}
