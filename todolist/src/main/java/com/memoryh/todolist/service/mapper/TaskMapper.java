package com.memoryh.todolist.service.mapper;

import com.memoryh.todolist.domain.Task;
import com.memoryh.todolist.dto.TaskDTO;

import java.util.List;
import java.util.stream.Collectors;

public class TaskMapper {

    private TaskMapper() {}

    public static List<Task> toDomainList(final List<TaskDTO> dtoList) {
        return dtoList.stream()
                .map(TaskMapper::toDomain)
                .collect(Collectors.toList());
    }

    private static Task toDomain(final TaskDTO dto) {
        return Task.of(dto.getId(), dto.getTitle(), dto.getLocalDate(), dto.isCompleted());
    }

}