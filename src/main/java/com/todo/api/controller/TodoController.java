package com.todo.api.controller;

import com.todo.api.DTO.todo.DataListTodo;
import com.todo.api.DTO.todo.DataRegisterTodo;
import com.todo.api.DTO.todo.DataUpdateTodo;
import com.todo.api.jpa.Todo;
import com.todo.api.jpa.repository.TodoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/todo")

public class TodoController {

    @Autowired
    private TodoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<String> register(@RequestBody @Valid DataRegisterTodo data) {
        try {
            repository.save(new Todo(data));
            return ResponseEntity.ok("Solicitação processada com sucesso.");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }


    @GetMapping
    public Page<DataListTodo> list(@PageableDefault(size = 10, sort = {"title"}) Pageable pagination) {
        return repository.findAllByIsActiveTrue(pagination).map(DataListTodo::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid DataUpdateTodo data) {
        var todoList = repository.getReferenceById(data.id());
        todoList.updateInfo(data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {

        var todo = repository.getReferenceById(id);
        todo.softDelete(id);
    }
}
