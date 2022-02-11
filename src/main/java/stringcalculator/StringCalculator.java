package stringcalculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    private static final String DELIMITER = ",|:";
    private static final String CUSTOM_DELIMITER_EXTRACT_REGEX = "//(.)\n(.*)";
    private static final String NO_INTEGER_ERROR = "[ERROR] 숫자가 아닌 문자가 입력되었습니다.";
    private static final String NEGATIVE_INTEGER_ERROR = "[ERROR] 음수가 입력되었습니다.";
    private static final String BLANK = "";
    private static final String REGEX_DELIMITER = "|";
    private static final int CUSTOM_DELIMITER_INDEX = 1;
    private static final int TEXT_INDEX = 2;
    private static final int DEFAULT_VALUE = 0;

    public static boolean isBlank(String text) {
        return text == null || text.isEmpty();
    }

    public static int calculate(String text) {
        if (isBlank(text)) {
            return DEFAULT_VALUE;
        }
        String delimiter = sumOfDelimiter(text);
        String target = extractText(text);
        String[] splitStrings = target.split(delimiter);
        int[] splitNumbers = toIntArray(splitStrings);
        return sumOfList(splitNumbers);
    }

    public static String sumOfDelimiter(String text) {
        String delimiter = DELIMITER;
        if (extractCustomDelimiter(text) != BLANK) {
            delimiter = delimiter + REGEX_DELIMITER + extractCustomDelimiter(text);
        }
        return delimiter;
    }

    public static void validateInteger(String text) {
        try {
            Integer.parseInt(text);
        } catch (NumberFormatException e) {
            throw new RuntimeException(NO_INTEGER_ERROR);
        }
    }

    public static void validatePositiveInteger(String text) {
        int target = Integer.parseInt(text);
        if (target < 0) {
            throw new RuntimeException(NEGATIVE_INTEGER_ERROR);
        }
    }

    public static int[] toIntArray(String[] numbers) {
        int[] result = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            validateInteger(numbers[i]);
            validatePositiveInteger(numbers[i]);
            result[i] = Integer.parseInt(numbers[i]);
        }
        return result;
    }

    public static int sumOfList(int[] numbers) {
        int total = 0;
        for (int number : numbers) {
            total += number;
        }
        return total;
    }

    public static Matcher getExtractMatcher(String text) {
        return Pattern.compile(CUSTOM_DELIMITER_EXTRACT_REGEX).matcher(text);
    }

    public static String extractCustomDelimiter(String text) {
        Matcher m = getExtractMatcher(text);
        if (m.find()) {
            return m.group(CUSTOM_DELIMITER_INDEX);
        }
        return BLANK;
    }

    public static String extractText(String text) {
        Matcher m = getExtractMatcher(text);
        if (m.find()) {
            return m.group(TEXT_INDEX);
        }
        return text;
    }
}
