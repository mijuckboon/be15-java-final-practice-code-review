package com.memoryh.todolist.view;

import com.memoryh.todolist.dto.TaskDTO;
import com.memoryh.todolist.dto.TaskListDTO;

public class OutputView {

    private OutputView() {}

    public static void printWelcomeMessage() {
        System.out.println("TO-DO LIST 프로그램을 시작합니다.");
    }

    public static void printTaskList(final TaskListDTO taskListDTO) {
        StringBuilder sb = new StringBuilder();

        for (TaskDTO taskDTO : taskListDTO.getTasks()) {
            sb.append(formatTask(taskDTO))
                    .append("\n");
        }
        System.out.println(sb);
    }

    public static void printNoTasksMessage() {
        System.out.println("조회할 TO-DO LIST가 없습니다.");
    }

    private static String formatTask(TaskDTO taskDTO) {
        return String.format("ID: %d | 제목: %s | 날짜: %s | 완료 여부: %s",
                taskDTO.getId(), taskDTO.getTitle(), taskDTO.getLocalDate(),
                taskDTO.isCompleted() ? "완료" : "미완료");
    }

}