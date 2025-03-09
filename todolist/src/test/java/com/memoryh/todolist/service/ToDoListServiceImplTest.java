package com.memoryh.todolist.service;

import com.memoryh.todolist.domain.Task;
import com.memoryh.todolist.repository.ToDoListRepository;
import com.memoryh.todolist.repository.ToDoListRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ToDoListServiceImplTest {

    private static final String TASK_FILE_PATH = "src/test/resources/tasks.json";
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private ToDoListRepository toDoListRepository;
    private ToDoListService toDoListService;
    private List<Task> taskList;

    @BeforeEach
    void init() {
        toDoListService = new ToDoListServiceImpl(toDoListRepository);
        toDoListRepository = new ToDoListRepositoryImpl(TASK_FILE_PATH);
        taskList = new ArrayList<>();
        taskList.add(Task.of(1L, "Java 프로젝트 완료하기", LocalDate.parse("2025-03-10"), true));
        taskList.add(Task.of(2L, "운동하기", LocalDate.parse("2025-03-05"), false));
    }

    @Test
    @DisplayName("taskList의 마지막 id값을 가져와 id 값을 1 증가 시켜 Task를 만든다.")
    void addTaskByTaskLastId() {
        String title = "알고리즘 공부하기";

        toDoListService.addTask(taskList, title);

        assertThat(taskList.size()).isEqualTo(3);
        assertThat(taskList.get(taskList.size() - 1).getId()).isEqualTo(3);
        assertThat(taskList.get(taskList.size() - 1).getTitle()).isEqualTo(title);
        assertThat(taskList.get(taskList.size() - 1).getLocalDate()).isEqualTo(LocalDate.parse(LocalDate.now().format(dateTimeFormatter)));
        assertThat(taskList.get(taskList.size() - 1).isCompleted()).isFalse();
    }

}