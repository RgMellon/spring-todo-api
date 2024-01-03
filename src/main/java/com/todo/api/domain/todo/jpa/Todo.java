package com.todo.api.domain.todo.jpa;


import com.todo.api.domain.todo.DTO.DataRegisterTodo;
import com.todo.api.domain.todo.DTO.DataUpdateTodo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "todos")
@Entity(name = "TODO")

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String title;

    @Column(name = "is_done")
    private Boolean isDone;

    private String description;

    @Column(name = "is_active")
    private  Boolean isActive;



    public Todo(DataRegisterTodo data) {
        this.title = data.title();
        this.isDone = data.isDone();
        this.description = data.description();
        this.isActive = true;
    }

    public void updateInfo(DataUpdateTodo data) {
        if (data.title() != null) {
            this.title = data.title();
        }

        if (data.description() != null) {
            this.description = data.description();
        }
    }


    public void softDelete(Long id) {
        this.isActive = false;
    }


}
