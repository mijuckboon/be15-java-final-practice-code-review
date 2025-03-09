package com.memoryh.todolist.common.constants;

public class TaskFilePath {

    private static final String TASK_FILE_PATH = "src/main/resources/tasks.json";

    private TaskFilePath() {}

    public static String getTaskFilePath() {
        return TASK_FILE_PATH;
    }

}