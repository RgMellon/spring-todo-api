package com.todo.api.controller;

import com.todo.api.domain.todo.DTO.DataDetailTodo;
import com.todo.api.domain.todo.DTO.DataListTodo;
import com.todo.api.domain.todo.DTO.DataRegisterTodo;
import com.todo.api.domain.todo.DTO.DataUpdateTodo;
import com.todo.api.domain.todo.jpa.Todo;
import com.todo.api.domain.todo.jpa.repository.TodoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/todo")

public class TodoController {

    @Autowired
    private TodoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid DataRegisterTodo data, UriComponentsBuilder uriBuilder) {
        try {

            var todo = new Todo(data);
            repository.save(todo);

            var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(todo.getId()).toUri();

            return ResponseEntity.created(uri).body(new DataDetailTodo(todo));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }


    @GetMapping
    public ResponseEntity<Page<DataListTodo>> list(@PageableDefault(size = 10, sort = {"title"}) Pageable pagination) {
        var page =  repository.findAllByIsActiveTrue(pagination).map(DataListTodo::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid DataUpdateTodo data) {
        var todoList = repository.getReferenceById(data.id());
        todoList.updateInfo(data);

        return ResponseEntity.ok(new DataDetailTodo(todoList));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {

        var todo = repository.getReferenceById(id);
        todo.softDelete(id);


        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable Long id) {
        var todo = repository.getReferenceById(id);
        return ResponseEntity.ok(new DataDetailTodo(todo));
    }
}
