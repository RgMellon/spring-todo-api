package com.todo.api.DTO.todo;

import jakarta.validation.constraints.NotBlank;

public record DataRegisterTodo(
        @NotBlank
        String title,
        Boolean isDone,
        String description,

        Boolean is_active
) {
}
