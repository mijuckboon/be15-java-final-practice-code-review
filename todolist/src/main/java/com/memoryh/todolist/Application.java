package com.memoryh.todolist;

import com.memoryh.todolist.config.AppConfig;
import com.memoryh.todolist.controller.ToDoListController;

public class Application {

    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        ToDoListController toDoListController = appConfig.toDoListController();
        toDoListController.run();
    }

}