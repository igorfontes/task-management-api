package com.perinity.manager.services;

import com.perinity.manager.models.entities.Task;
import com.perinity.manager.repositories.DepartmentRepository;
import com.perinity.manager.repositories.PersonRepository;
import com.perinity.manager.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    public Task createTask(Task task){
        return taskRepository.save(task);
    }

    public Task allocatePersonFromDepartmentToTask(Long id) {
        return taskRepository.findById(id)
                .map(task -> {
                    var department = task.getDepartment();
                    return personRepository.findByDepartment(department)
                            .stream()
                            .findFirst()
                            .map(person -> {
                                task.setPerson(person);
                                return taskRepository.save(task);
                            })
                            .orElse(null);
                })
                .orElse(null);
    }

    public void realizeTask(Long id) {
        taskRepository.findById(id).ifPresent(task -> {
            task.setDone(true);
            taskRepository.save(task);
        });
    }

    public List<Task> obtainPendingTasks() {
        return taskRepository.findByOrderByDeadlineAsc()
                    .stream()
                    .filter(it -> it.getPerson() == null)
                    .toList().subList(0, 3);
    }

}
