package com.todolist.springtodolist;

import com.todolist.springtodolist.model.Status;
import com.todolist.springtodolist.model.Task;
import com.todolist.springtodolist.repos.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private TaskRepo taskRepo;

    @GetMapping
    public String main(Map<String, Object> model) {
        Iterable<Task> tasks = taskRepo.findAll();
        model.put("tasks", tasks);
        return "main";
    }

    @PostMapping
    public String addNewTask(@RequestParam String nameTask, Map<String, Object> objectMap) {
        Task task;
        if (nameTask != ""){
            task = new Task(nameTask, Status.ACTIVE);
            taskRepo.save(task);
        }

        Iterable<Task> tasks = taskRepo.findAll();
        objectMap.put("tasks", tasks);
        return "main";
    }
}
