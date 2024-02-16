package com.perinity.manager.controllers;

import com.perinity.manager.models.entities.Task;
import com.perinity.manager.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tarefas")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping()
    public Task createTask(@RequestBody Task task){
        return taskService.createTask(task);
    }

    @PutMapping("/alocar/{id}")
    public Task allocatePersonFromDepartmentToTask(@PathVariable Long id){
        return taskService.allocatePersonFromDepartmentToTask(id);
    }

    @PutMapping("/finalizar/{id}")
    public void realizeTask(@PathVariable Long id){
        taskService.realizeTask(id);
    }

    @GetMapping("/pendentes")
    public List<Task> obtainPendingTasks(){
        return taskService.obtainPendingTasks();
    }

}
