package com.memoryh.todolist.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonPropertyOrder({"id", "title", "dueDate", "completed"})
public class TaskDTO {

    private Long id;
    private String title;
    @JsonProperty("dueDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate localDate;
    private boolean completed;

    private TaskDTO(final Long id, final String title, final LocalDate localDate, final boolean completed) {
        this.id = id;
        this.title = title;
        this.localDate = localDate;
        this.completed = completed;
    }

    public static TaskDTO of(final Long id, final String title, final LocalDate localDate, final boolean completed) {
        return new TaskDTO(id, title, localDate, completed);
    }

}