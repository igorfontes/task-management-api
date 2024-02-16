package com.perinity.manager.services;

import com.perinity.manager.models.entities.Department;
import com.perinity.manager.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public Long createDepartment(Department department){
        return departmentRepository.save(department).getId();
    }
}
