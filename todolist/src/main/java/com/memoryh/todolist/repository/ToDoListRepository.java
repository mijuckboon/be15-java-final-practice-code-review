package com.memoryh.todolist.repository;

import com.memoryh.todolist.dto.TaskDTO;

import java.util.List;

public interface ToDoListRepository {

    void writeTaskListToFile(final List<TaskDTO> taskDTOList);

}