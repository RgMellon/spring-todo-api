package com.todo.api.domain.todo.DTO;

import jakarta.validation.constraints.NotNull;

public record DataUpdateTodo(
        @NotNull
        Long id,

        @NotNull
        String title,

        String description) { }
