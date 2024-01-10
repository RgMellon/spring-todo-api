package com.todo.api.domain.remind.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DataRemindTodo(
        @NotNull
        Long idTodo,

        @NotNull
        @Future
        @Column(name = "remind_date")
        LocalDateTime remindDate
        ) {

}
