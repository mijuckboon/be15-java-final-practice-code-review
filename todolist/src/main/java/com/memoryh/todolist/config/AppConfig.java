package com.memoryh.todolist.config;

import com.memoryh.todolist.common.constants.TaskFilePath;
import com.memoryh.todolist.controller.ToDoListController;
import com.memoryh.todolist.repository.TaskRepository;
import com.memoryh.todolist.repository.TaskRepositoryImpl;
import com.memoryh.todolist.repository.ToDoListRepository;
import com.memoryh.todolist.repository.ToDoListRepositoryImpl;
import com.memoryh.todolist.service.TaskService;
import com.memoryh.todolist.service.TaskServiceImpl;
import com.memoryh.todolist.service.ToDoListService;
import com.memoryh.todolist.service.ToDoListServiceImpl;

public class AppConfig {

    public ToDoListController toDoListController() {
        return new ToDoListController(taskService(), toDoListService());
    }

    public ToDoListService toDoListService() {
        return new ToDoListServiceImpl(toDoListRepository());
    }

    public ToDoListRepository toDoListRepository() {
        return new ToDoListRepositoryImpl(TaskFilePath.getTaskFilePath());
    }

    public TaskService taskService() {
        return new TaskServiceImpl(taskRepository());
    }

    public TaskRepository taskRepository() {
        return new TaskRepositoryImpl();
    }

}