package com.memoryh.todolist.view;

import com.memoryh.todolist.validator.UserInputValidator;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {}

    public static String getDateFromInput() {
        System.out.print("조회하고 싶은 날짜를 입력하세요. (예: 2025-03-07) : ");
        String userInput = readUserInput();

        try {
            UserInputValidator.validateDateFromInput(userInput);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getDateFromInput();
        }
        return userInput;
    }

    public static String getDeleteId() {
        System.out.print("삭제할 id를 입력하세요. : ");
        String userInput;
        try {
            userInput = getTaskId();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getDeleteId();
        }
        return userInput;
    }

    public static String getCompleted() {
        System.out.print("완료 여부를 입력하세요. (예: y) : ");
        String userInput = readUserInput().toLowerCase();
        try {
            UserInputValidator.validateUserInput(userInput);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getCompleted();
        }
        return userInput;
    }

    public static String getUpdateId() {
        System.out.print("수정할 id를 입력하세요. : ");
        String userInput;
        try {
            userInput = getTaskId();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getUpdateId();
        }
        return userInput;
    }

    public static String getTitle() {
        System.out.print("제목을 입력하세요. : ");
        String userInput = readUserInput();

        try {
            UserInputValidator.validateUserInput(userInput);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getTitle();
        }
        return userInput;
    }

    public static String getCommandInput() {
        System.out.print("추가, 수정, 삭제, 조회, 종료 중 하나를 입력하세요. (예: 추가) : ");
        String userInput = readUserInput();

        try {
            UserInputValidator.validateCommand(userInput);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getCommandInput();
        }
        return userInput;
    }

    private static String getTaskId() throws IllegalArgumentException {
        String userInput = readUserInput();
        UserInputValidator.isNaturalNumber(userInput);
        return userInput;
    }

    private static String readUserInput() {
        return scanner.nextLine();
    }

}