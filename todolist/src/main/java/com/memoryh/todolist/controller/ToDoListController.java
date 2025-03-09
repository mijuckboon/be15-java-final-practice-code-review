package com.memoryh.todolist.controller;

import com.memoryh.todolist.domain.Task;
import com.memoryh.todolist.dto.TaskListDTO;
import com.memoryh.todolist.service.TaskService;
import com.memoryh.todolist.service.ToDoListService;
import com.memoryh.todolist.view.InputView;
import com.memoryh.todolist.view.OutputView;
import com.memoryh.todolist.common.constants.CommandType;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class ToDoListController {

    private final TaskService taskService;
    private final ToDoListService toDoListService;

    public void run() {
        List<Task> taskList = taskService.findAllTasks().getTaskList();
        OutputView.printWelcomeMessage();

        while (true) {
            String commandInput = receiveUserCommand();
            CommandType commandType = getCommandType(commandInput);

            if (commandType == CommandType.EXIT) {
                break;
            }
            handleCommand(taskList, commandType);
        }
    }

    private void handleCommand(final List<Task> taskList, final CommandType commandType) {
        switch (commandType) {
            case ADD:
                handleAddCommand(taskList);
                break;
            case UPDATE:
                handleUpdateCommand(taskList);
                break;
            case DELETE:
                handleDeleteCommand(taskList);
                break;
            case LIST:
                handleListCommand(taskList);
                break;
        }
    }

    private void handleAddCommand(final List<Task> taskList) {
        String title = InputView.getTitle();
        List<Task> addedTaskList = toDoListService.addTask(taskList, title);
        toDoListService.convertToTaskListDTO(addedTaskList);
    }

    private void handleUpdateCommand(final List<Task> taskList) {
        long taskId = Long.parseLong(InputView.getUpdateId());
        String completed = InputView.getCompleted();

        if (completed.equals("y")) {
            toDoListService.markAsCompleted(taskList, taskId);
        }
    }

    private void handleDeleteCommand(final List<Task> taskList) {
        long taskId = Long.parseLong(InputView.getDeleteId());
        toDoListService.deleteTask(taskList, taskId);
    }

    private void handleListCommand(final List<Task> taskList) {
        String dateFromInput = InputView.getDateFromInput();
        TaskListDTO matchedTasksByDate = toDoListService.findTasksByDate(taskList, dateFromInput);

        if (matchedTasksByDate.getTasks().isEmpty()) {
            OutputView.printNoTasksMessage();
        } else {
            OutputView.printTaskList(matchedTasksByDate);
        }
    }

    private String receiveUserCommand() {
        return InputView.getCommandInput();
    }

    private CommandType getCommandType(final String commandInput) {
        return Arrays.stream(CommandType.values())
                .filter(commandType -> commandType.getCommand().equals(commandInput))
                .findFirst()
                .orElse(null);
    }

}