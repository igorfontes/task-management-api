package com.perinity.manager.controllers;

import com.perinity.manager.models.entities.Person;
import com.perinity.manager.models.entities.Task;
import com.perinity.manager.services.PersonService;
import com.perinity.manager.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/post/tarefas")
    public Task createTask(@RequestBody Task task){
        return taskService.createTask(task);
    }

    @PutMapping("/put/tarefas/alocar/{id}")
    public Task allocatePersonFromDepartmentToTask(@PathVariable Long id){
        return taskService.allocatePersonFromDepartmentToTask(id);
    }

    @PutMapping("/put/tarefas/finalizar/{id}")
    public void realizeTask(@PathVariable Long id){
        taskService.realizeTask(id);
    }

}
