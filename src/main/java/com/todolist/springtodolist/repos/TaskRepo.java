package com.todolist.springtodolist.repos;

import com.todolist.springtodolist.model.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepo extends CrudRepository<Task, Integer> {

}
