package com.memoryh.todolist.validator;

import com.memoryh.todolist.common.constants.CommandType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class UserInputValidator {

    private static final String NATURAL_NUMBER_PATTERN = "^[1-9]\\d*$";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private UserInputValidator() {}

    public static void validateDateFromInput(final String userInput) {
        validateUserInput(userInput);
        try {
            LocalDate.parse(userInput, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("[ERROR] 날짜 형식에 맞지 않습니다.");
        }
    }

    public static void isNaturalNumber(final String userInput) {
        validateUserInput(userInput);
        if (!userInput.matches(NATURAL_NUMBER_PATTERN)) {
            throw new IllegalArgumentException("[ERROR] 숫자를 입력하세요.");
        }
    }

    public static void validateCommand(final String command) {
        validateUserInput(command);
        boolean isValid = Arrays.stream(CommandType.values())
                .anyMatch(cmd -> cmd.getCommand().equals(command));

        if (!isValid) {
            throw new IllegalArgumentException("[ERROR] 추가, 수정, 삭제, 조회, 종료 중 1개만 입력 가능합니다.");
        }
    }

    public static void validateUserInput(final String userInput) {
        if (userInput == null || userInput.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 입력값은 비어있을 수 없습니다.");
        }
    }

}