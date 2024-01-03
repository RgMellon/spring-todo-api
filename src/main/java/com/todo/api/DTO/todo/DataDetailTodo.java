package com.todo.api.DTO.todo;

import com.todo.api.jpa.Todo;

public record DataDetailTodo(Long id, String title, String description) {
    public DataDetailTodo(Todo todo) {
        this(todo.getId(), todo.getTitle(), todo.getDescription());
    }
}
