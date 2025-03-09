package com.memoryh.todolist.repository;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.memoryh.todolist.dto.TaskDTO;
import com.memoryh.todolist.dto.TaskListDTO;
import lombok.RequiredArgsConstructor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class ToDoListRepositoryImpl implements ToDoListRepository {

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .enable(SerializationFeature.INDENT_OUTPUT);
    private final String taskFilePath;

    @Override
    public void writeTaskListToFile(final List<TaskDTO> taskDTOList) {
        TaskListDTO taskListDTO = new TaskListDTO(taskDTOList);
        String convertedTaskListToJson = convertTaskListToJson(taskListDTO);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(taskFilePath))) {
            writer.write(convertedTaskListToJson);
            writer.flush();
        } catch (IOException e) {
            System.out.println("[ERROR] JSON 파일 저장 중 오류 발생: " + e.getMessage());
        }
    }

    private String convertTaskListToJson(final TaskListDTO taskDTO) {
        String jsonString;

        try {
            jsonString = objectMapper.writer(new CustomPrettyPrinter()).writeValueAsString(taskDTO);
        } catch (IOException e) {
            System.out.println("[ERROR] JSON 변환 중 오류 발생: " + e.getMessage());
            return "{}";
        }
        return jsonString;
    }

    private static class CustomPrettyPrinter extends DefaultPrettyPrinter {

        public CustomPrettyPrinter() {
            this._arrayIndenter = DefaultIndenter.SYSTEM_LINEFEED_INSTANCE;
            this._objectIndenter = DefaultIndenter.SYSTEM_LINEFEED_INSTANCE;
        }

        @Override
        public DefaultPrettyPrinter createInstance() {
            return new CustomPrettyPrinter();
        }

        @Override
        public void writeObjectFieldValueSeparator(final JsonGenerator jg) throws IOException {
            jg.writeRaw(": ");
        }

    }

}