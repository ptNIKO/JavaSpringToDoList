package com.todolist.springtodolist.model;

import javax.persistence.*;

@Entity
@Table
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nameTask;
    private boolean status;

    public Task(){}

    public Task(String nameTask, boolean status) {
        this.nameTask = nameTask;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName_task() {
        return nameTask;
    }

    public void setName_task(String nameTask) {
        this.nameTask = nameTask;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
