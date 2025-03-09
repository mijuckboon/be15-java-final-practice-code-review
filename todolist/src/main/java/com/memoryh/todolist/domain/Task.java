package com.memoryh.todolist.domain;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Task {

    private Long id;
    private String title;
    private LocalDate localDate;
    private boolean completed;

    private Task (final Long id, final String title, final LocalDate localDate, final boolean completed) {
        this.id = id;
        this.title = title;
        this.localDate = localDate;
        this.completed = completed;
    }

    public static Task of(final Long id, final String title, final LocalDate localDate, final boolean completed) {
        return new Task(id, title, localDate, completed);
    }

    public void updateCompleted(final boolean completed) {
        this.completed = completed;
    }

}