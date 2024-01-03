package com.todo.api.DTO.todo;

import jakarta.validation.constraints.NotNull;

public record DataUpdateTodo(
        @NotNull
        Long id,

        @NotNull
        String title,

        String description) { }
