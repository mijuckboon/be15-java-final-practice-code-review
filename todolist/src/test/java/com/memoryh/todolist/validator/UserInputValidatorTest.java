package com.memoryh.todolist.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserInputValidatorTest {

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"\n", "\t"})
    @DisplayName("입력값이 null 또는 빈 문자열일 경우 예외 발생")
    void testInvalidUserInput(final String input) {
        assertThatThrownBy(() -> UserInputValidator.validateUserInput(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 입력값은 비어있을 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"추가", "수정", "삭제", "조회", "종료"})
    @DisplayName("올바른 명령어 입력 시 정상적으로 반환")
    void testValidCommandInput(final String input) {
        assertThatCode(() -> UserInputValidator.validateCommand(input))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"추가하기", "수", "ada", "1"})
    @DisplayName("잘못된 명령어 입력 시 예외 발생")
    void testInvalidCommandInput(final String input) {
        assertThatThrownBy(() -> UserInputValidator.validateCommand(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 추가, 수정, 삭제, 조회, 종료 중 1개만 입력 가능합니다.");
    }

}