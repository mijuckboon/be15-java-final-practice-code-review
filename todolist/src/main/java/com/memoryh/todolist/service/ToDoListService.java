package com.memoryh.todolist.service;

import com.memoryh.todolist.domain.Task;
import com.memoryh.todolist.dto.TaskListDTO;

import java.util.List;

public interface ToDoListService {

    List<Task> addTask(final List<Task> taskList, final String title);
    void convertToTaskListDTO(final List<Task> taskList);
    void markAsCompleted(final List<Task> taskList, final long taskId);
    void deleteTask(final List<Task> taskList, final long taskId);
    TaskListDTO findTasksByDate(final List<Task> taskList, final String date);

}