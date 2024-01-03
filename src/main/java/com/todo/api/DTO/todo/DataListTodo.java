package com.todo.api.DTO.todo;

import com.todo.api.jpa.Todo;


public record DataListTodo(
        Long id,
        String title,
        String description) {

    public DataListTodo(Todo todo) {
        this(todo.getId(), todo.getTitle(), todo.getDescription());
    }
}
