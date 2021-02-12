package racingcar.view.input.racingtrytime.validator;

import racingcar.view.input.racingtrytime.validator.exception.NotNaturalNumberRacingTimeException;

public class RacingTryTimeValidator {
    private static final String ERROR_MESSAGE = "경주 시도 횟수는 자연수여야 합니다.";
    private static final int MIN_NATURAL_NUMBER = 1;

    public static void validate(String racingTryTime) {
        validateNaturalNumberRacingTime(racingTryTime);
    }

    private static void validateNaturalNumberRacingTime(String racingTryTime) {
        int convertedRacingTryTime = validateIntegerRacingTime(racingTryTime);
        validateOneOrMoreRacingTime(convertedRacingTryTime);
    }

    private static int validateIntegerRacingTime(String racingTryTime) {
        try {
            return Integer.parseInt(racingTryTime);
        } catch (Exception e) {
            throw new NotNaturalNumberRacingTimeException(ERROR_MESSAGE);
        }
    }

    private static void validateOneOrMoreRacingTime(int integerConvertedRacingTime) {
        if (integerConvertedRacingTime < MIN_NATURAL_NUMBER) {
            throw new NotNaturalNumberRacingTimeException(ERROR_MESSAGE);
        }
    }
}