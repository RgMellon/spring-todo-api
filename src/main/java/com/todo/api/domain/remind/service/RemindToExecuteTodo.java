package com.todo.api.domain.remind.service;

import com.todo.api.domain.ValidateException;
import com.todo.api.domain.remind.DTO.DataRemindTodo;
import com.todo.api.domain.remind.jpa.Remind;
import com.todo.api.domain.remind.jpa.repository.RemindRepository;

import com.todo.api.domain.todo.jpa.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemindToExecuteTodo {

    @Autowired
    private RemindRepository remindRepository;

    @Autowired
    private TodoRepository todoRepository;

    public void remindMe(DataRemindTodo data) {

        if(data.idTodo() != null && !todoRepository.existsById(data.idTodo()))  {
            throw  new ValidateException("Id do todo não existe");
        }

        var todo = todoRepository.getReferenceById(data.idTodo());

        //TODO criar validadores
//        if(Boolean.TRUE.equals(todo.getIsDone())) {
//            throw new ValidateException("Esse todo ja foi executado, por isso não é possivel adicionar um lembrete.");
//        }

        var remind = new Remind(null, todo, data.remindDate());
        remindRepository.save(remind);
    }
}
