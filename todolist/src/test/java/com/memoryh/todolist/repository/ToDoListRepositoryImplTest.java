package com.memoryh.todolist.repository;

import com.memoryh.todolist.dto.TaskDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ToDoListRepositoryImplTest {

    private static final String TASK_FILE_PATH = "src/test/resources/tasks.json";
    private ToDoListRepository toDoListRepository;
    private List<TaskDTO> taskList;

    @BeforeEach
    void setUp() {
        toDoListRepository = new ToDoListRepositoryImpl(TASK_FILE_PATH);
        taskList = new ArrayList<>();

        taskList.add(TaskDTO.of(1L, "Java 프로젝트 완료하기", LocalDate.parse("2025-03-10"), true));
        taskList.add(TaskDTO.of(2L, "운동하기", LocalDate.parse("2025-03-05"), false));
        taskList.add(TaskDTO.of(3L, "알고리즘 공부하기", LocalDate.parse("2025-03-06"), false));
    }

    @Test
    @DisplayName("json 파일을 지정 경로에 저장했는지 확인한다.")
    void checkSavedJsonFile() {
        toDoListRepository.writeTaskListToFile(taskList);
        File file = new File(TASK_FILE_PATH);

        assertThat(file.exists()).isTrue();
    }

}