package com.todo.api.domain.todo.jpa.repository;

import com.todo.api.domain.todo.jpa.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    Page<Todo> findAllByIsDoneTrue(Pageable pagination);

    Page<Todo> findAllByIsActiveTrue(Pageable pagination);



}
