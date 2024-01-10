package com.todo.api.domain.remind.jpa.repository;

import com.todo.api.domain.remind.jpa.Remind;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;


public interface RemindRepository extends JpaRepository<Remind, Long> {
    boolean existsByTodoIdAndRemindDate(Long todoId, LocalDateTime remindDate);
}
