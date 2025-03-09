package com.memoryh.todolist.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class TaskList {

    private List<Task> taskList;

    private TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public static TaskList of(final List<Task> taskList) {
        return new TaskList(taskList);
    }

}