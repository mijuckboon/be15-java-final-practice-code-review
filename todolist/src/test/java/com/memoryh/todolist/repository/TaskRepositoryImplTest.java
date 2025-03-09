package com.memoryh.todolist.repository;

import com.memoryh.todolist.dto.TaskDTO;
import com.memoryh.todolist.dto.TaskListDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TaskRepositoryImplTest {

    TaskRepository taskRepository = new TaskRepositoryImpl();

    @Test
    @DisplayName("JSON 파일에서 TO-DO LIST를 정상적으로 불러온다.")
    void shouldLoadTaskListFromJsonFile() {
        TaskListDTO taskByTaskFilePath = taskRepository.readTaskListFromFile();

        assertThat(taskByTaskFilePath).isNotNull();
        assertThat(taskByTaskFilePath.getTasks()).isNotEmpty();
        assertThat(taskByTaskFilePath).isInstanceOf(TaskListDTO.class);

        TaskDTO firstTask = taskByTaskFilePath.getTasks().get(0);
        assertThat(firstTask.getId()).isEqualTo(1L);
        assertThat(firstTask.getTitle()).isEqualTo("Java 프로젝트 완료하기");
        assertThat(firstTask.getLocalDate()).isEqualTo("2025-03-10");
        assertThat(firstTask.isCompleted()).isTrue();

        TaskDTO secondTask = taskByTaskFilePath.getTasks().get(1);
        assertThat(secondTask.getId()).isEqualTo(2L);
        assertThat(secondTask.getTitle()).isEqualTo("운동하기");
        assertThat(secondTask.getLocalDate()).isEqualTo("2025-03-05");
        assertThat(secondTask.isCompleted()).isFalse();
    }

}