package com.memoryh.todolist.service;

import com.memoryh.todolist.domain.Task;
import com.memoryh.todolist.dto.TaskDTO;
import com.memoryh.todolist.dto.TaskListDTO;
import com.memoryh.todolist.repository.ToDoListRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ToDoListServiceImpl implements ToDoListService {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final ToDoListRepository toDoListRepository;

    @Override
    public List<Task> addTask(final List<Task> taskList, final String title) {
        Task crateTask = createTask(taskList, title);
        return addTaskList(taskList, crateTask);
    }

    @Override
    public void convertToTaskListDTO(final List<Task> taskList) {
        List<TaskDTO> taskDTOList = new ArrayList<>();
        mapTasksToDTOs(taskList, taskDTOList);
        toDoListRepository.writeTaskListToFile(taskDTOList);
    }

    @Override
    public void markAsCompleted(final List<Task> taskList, final long taskId) {
        markTaskAsCompleted(taskList, taskId);
        List<TaskDTO> taskDTOList = new ArrayList<>();
        mapTasksToDTOs(taskList, taskDTOList);
        toDoListRepository.writeTaskListToFile(taskDTOList);
    }

    @Override
    public void deleteTask(final List<Task> taskList, final long taskId) {
        removeTaskById(taskList, taskId);
        List<TaskDTO> taskDTOList = new ArrayList<>();
        mapTasksToDTOs(taskList, taskDTOList);
        toDoListRepository.writeTaskListToFile(taskDTOList);
    }

    @Override
    public TaskListDTO findTasksByDate(final List<Task> taskList, final String date) {
        TaskListDTO taskListDTO = new TaskListDTO();
        List<TaskDTO> taskDTOList = taskListDTO.getTasks();
        LocalDate userInputLocalDate = LocalDate.parse(date);
        filterTasksByDate(taskList, userInputLocalDate, taskDTOList);
        return taskListDTO;
    }

    private void filterTasksByDate(List<Task> taskList, LocalDate userInputLocalDate, List<TaskDTO> taskDTOList) {
        for (Task task : taskList) {
            if (task.getLocalDate().equals(userInputLocalDate)) {
                taskDTOList.add(TaskDTO.of(task.getId(), task.getTitle(), task.getLocalDate(), task.isCompleted()));
            }
        }
    }

    private void removeTaskById(final List<Task> taskList, final long taskId) {
        for (Task task : taskList) {
            if (task.getId() == taskId) {
                taskList.remove(task);
                break;
            }
        }
    }

    private void markTaskAsCompleted(final List<Task> taskList, final long taskId) {
        for (Task task : taskList) {
            if (task.getId() == taskId) {
                task.updateCompleted(true);
                break;
            }
        }
    }

    private void mapTasksToDTOs(final List<Task> taskList, final List<TaskDTO> taskDTOList) {
        for (Task task : taskList) {
            taskDTOList.add(TaskDTO.of(task.getId(), task.getTitle(), task.getLocalDate(), task.isCompleted()));
        }
    }

    private List<Task> addTaskList(final List<Task> taskList, final Task task) {
        taskList.add(task);
        return taskList;
    }

    private long getLastId(final List<Task> taskList) {
        if (taskList.isEmpty()) {
            return 1L;
        }
        return taskList.get(taskList.size() - 1).getId();
    }

    private Task createTask(final List<Task> taskList, final String title) {
        long lastId = getLastId(taskList);
        return Task.of(++lastId, title, LocalDate.parse(LocalDate.now().format(dateTimeFormatter)), false);
    }

}