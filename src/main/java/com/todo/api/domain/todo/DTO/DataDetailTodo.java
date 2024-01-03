package com.todo.api.domain.todo.DTO;

import com.todo.api.domain.todo.jpa.Todo;

public record DataDetailTodo(Long id, String title, String description) {
    public DataDetailTodo(Todo todo) {
        this(todo.getId(), todo.getTitle(), todo.getDescription());
    }
}
