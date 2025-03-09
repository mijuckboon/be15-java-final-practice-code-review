package com.memoryh.todolist.service;

import com.memoryh.todolist.domain.Task;
import com.memoryh.todolist.domain.TaskList;
import com.memoryh.todolist.dto.TaskListDTO;
import com.memoryh.todolist.service.mapper.TaskMapper;
import com.memoryh.todolist.repository.TaskRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepositoryImpl;

    @Override
    public TaskList findAllTasks() {
        TaskListDTO taskListDTO = taskRepositoryImpl.readTaskListFromFile();
        List<Task> taskList = TaskMapper.toDomainList(taskListDTO.getTasks());
        return TaskList.of(taskList);
    }

}