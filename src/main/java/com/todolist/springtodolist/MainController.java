package com.todolist.springtodolist;

import com.todolist.springtodolist.model.Status;
import com.todolist.springtodolist.model.Task;
import com.todolist.springtodolist.repos.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    private TaskRepo taskRepo;


    @GetMapping
    public String main(Model model) {
        Iterable<Task> tasks = taskRepo.findAll();
        model.addAttribute("tasks", tasks);
        return "main";
    }


    @PostMapping(value = {"/addNewTask"})
    public String addNewTask(@RequestParam String nameTask, Model model) {
        Task task;
        if (nameTask != ""){
            task = new Task(nameTask, true);
            taskRepo.save(task);
        }

        Iterable<Task> tasks = taskRepo.findAll();
        model.addAttribute("tasks", tasks);
        return "main";
    }

    @PostMapping("separat")
    public String separationMethod(@RequestParam(value = "checkboxName", required = false) String checkboxValue,
                                 @ModelAttribute("idTask") String idTask,
                                 @ModelAttribute("deleteButton") String deleteButton,
                                 Model model) {

        if (deleteButton.equals("deleteButton"))
            deleteTask(idTask, model);
        else if (deleteButton.equals("finishUpButton"))
            finishUpTask(checkboxValue, idTask, model);
        else
            submitTask(checkboxValue, idTask, model);


        return "main";
    }

    @PostMapping("submitTask")
    public String submitTask(String checkboxValue,
                             String idTask,
                             Model model) {


        Iterable<Task> tasks = taskRepo.findAll();
        Task task = taskRepo.findById(Integer.parseInt(idTask)).get();

            if (checkboxValue != null && taskRepo.findById(Integer.parseInt(idTask)).get().isStatus()){
                task.setStatus(false);
                taskRepo.save(task);
            }
            else{
                task.setStatus(true);
                taskRepo.save(task);
            }

            model.addAttribute("tasks", tasks);
            System.out.println(idTask);


        return "main";
    }

    @PostMapping("finishUpTask")
    public String finishUpTask(String checkboxValue,
                             String idTask,
                             Model model) {


        Iterable<Task> tasks = taskRepo.findAll();
        Task task = taskRepo.findById(Integer.parseInt(idTask)).get();


        if (checkboxValue != null && taskRepo.findById(Integer.parseInt(idTask)).get().isStatus()){
            task.setStatus(false);
            taskRepo.save(task);
        }
        else{
            task.setStatus(true);
            taskRepo.save(task);
        }

        model.addAttribute("tasks", tasks);
        System.out.println(idTask);


        return "main";
    }

    @PostMapping("deleteTask")
    public String deleteTask(String idTask,
                             Model model) {

        taskRepo.deleteById(Integer.parseInt(idTask));
        Iterable<Task> tasks = taskRepo.findAll();
        model.addAttribute("tasks", tasks);
        //System.out.println("Delete: " + task.getName_task());
        return "main";
    }
}
