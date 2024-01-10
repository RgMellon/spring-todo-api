package com.todo.api.domain.remind.DTO;

import java.time.LocalDateTime;

public record DataDetailRemindTodo(
        Long id,
        Long idTodo,
        LocalDateTime remindDate) { }
