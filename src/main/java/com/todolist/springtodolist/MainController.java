package com.todolist.springtodolist;

import com.todolist.springtodolist.model.Status;
import com.todolist.springtodolist.model.Task;
import com.todolist.springtodolist.repos.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = {"/addNewTask"})
    public String addNewTask(@RequestParam String nameTask, Map<String, Object> objectMap) {
        Task task;
        if (nameTask != ""){
            task = new Task(nameTask, true);
            taskRepo.save(task);
        }

        Iterable<Task> tasks = taskRepo.findAll();
        objectMap.put("tasks", tasks);
        return "main";
    }

    @PostMapping("submitTask")
    public String submitTask(@RequestParam(value = "checkboxName", required = false) String checkboxValue, Map<String, Object> model) {

        if(checkboxValue != null) {
            System.out.println("checkbox is checked");
        }
        else {
            System.out.println("checkbox is not checked");
        }
        Iterable<Task> tasks = taskRepo.findAll();
        model.put("tasks", tasks);
        return "main";
    }
}
