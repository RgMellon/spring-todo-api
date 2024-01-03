package com.todo.api.jpa.repository;

import com.todo.api.jpa.Todo;
import io.micrometer.observation.ObservationFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    Page<Todo> findAllByIsDoneTrue(Pageable pagination);

    Page<Todo> findAllByIsActiveTrue(Pageable pagination);
}
