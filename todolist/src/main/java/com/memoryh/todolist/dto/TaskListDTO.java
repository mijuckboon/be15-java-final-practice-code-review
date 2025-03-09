package com.memoryh.todolist.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TaskListDTO {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<TaskDTO> tasks = new ArrayList<>();

}