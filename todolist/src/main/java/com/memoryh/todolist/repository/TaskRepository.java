package com.memoryh.todolist.repository;

import com.memoryh.todolist.dto.TaskListDTO;

public interface TaskRepository {

    TaskListDTO readTaskListFromFile();

}