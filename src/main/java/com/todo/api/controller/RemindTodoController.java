package com.todo.api.controller;

import com.todo.api.domain.remind.DTO.DataDetailRemindTodo;
import com.todo.api.domain.remind.DTO.DataRemindTodo;
import com.todo.api.domain.remind.service.RemindToExecuteTodo;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("remind")
public class RemindTodoController {

    @Autowired
    private RemindToExecuteTodo remindToExecuteTodo;

    @PostMapping
    @Transactional

    public ResponseEntity schedule(@RequestBody @Valid DataRemindTodo data)  {
        remindToExecuteTodo.remindMe(data);
        return ResponseEntity.ok(new DataDetailRemindTodo(1232134L, data.idTodo(), data.remindDate()));
    }

}
