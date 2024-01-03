package com.todo.api.domain.todo.DTO;

import com.todo.api.domain.todo.jpa.Todo;


public record DataListTodo(
        Long id,
        String title,
        String description) {

    public DataListTodo(Todo todo) {
        this(todo.getId(), todo.getTitle(), todo.getDescription());
    }
}
