package com.perinity.manager.services;

import com.perinity.manager.models.DTOs.DepartmentInfoDTO;
import com.perinity.manager.models.entities.Department;
import com.perinity.manager.repositories.DepartmentRepository;
import com.perinity.manager.repositories.PersonRepository;
import com.perinity.manager.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private PersonRepository personRepository;

    public Long createDepartment(Department department){
        return departmentRepository.save(department).getId();
    }

    public List<DepartmentInfoDTO> listDepartmentInfo() {
        return departmentRepository.findAll().stream().map(department -> {
            var peopleAmount = personRepository.findByDepartment(department).stream().count();
            var tasksAmount = taskRepository.findByDepartment(department).stream().count();

            return new DepartmentInfoDTO(department, peopleAmount, tasksAmount);
        }).toList();
    }
}
