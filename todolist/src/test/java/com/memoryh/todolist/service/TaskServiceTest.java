package com.memoryh.todolist.service;

import com.memoryh.todolist.domain.TaskList;
import com.memoryh.todolist.repository.TaskRepository;
import com.memoryh.todolist.repository.TaskRepositoryImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TaskServiceTest {

    private final TaskRepository taskRepositoryImpl = new TaskRepositoryImpl();
    private final TaskServiceImpl taskService = new TaskServiceImpl(taskRepositoryImpl);

    @Test
    @DisplayName("불러온 TO-DO LIST를 TaskList에 저장한다.")
    void loadTodoListAndStoreInTaskList() {
        TaskList taskList = taskService.findAllTasks();

        assertThat(taskList).isNotNull();
        assertThat(taskList.getTaskList()).isNotEmpty();
        assertThat(taskList.getTaskList()).hasSize(2);
    }

}